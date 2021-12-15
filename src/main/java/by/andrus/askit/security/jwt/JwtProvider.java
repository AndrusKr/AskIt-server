package by.andrus.askit.security.jwt;

import by.andrus.askit.model.User;
import by.andrus.askit.model.enums.Status;
import by.andrus.askit.security.SecurityUser;
import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {
    public static final long EXPIRATION_TIME = 86_400_000;
    public static final String HEADER_STRING = "Authorization";

    public String createJwt(User user) {
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("role", user.getRole());
        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, user.getJwtSecret().toString().getBytes()).compact();
    }

    public Optional<String> resolveJwt(HttpServletRequest request) {
        String jwt = request.getHeader(HEADER_STRING);
        return JwtUtils.getToken(jwt);
    }

    public Long getUserId(String jwt) {
        String[] split_string = jwt.split("\\."); // split the JWT on 3 parts
        String jwtEncodedBody = split_string[1]; // take the body of JWT
        String jwtDecodedBody = new String(BaseEncoding.base64().decode(jwtEncodedBody));
        JSONObject jsonObject = new JSONObject(jwtDecodedBody);
        return jsonObject.getLong("sub"); // get 'subject' filed where ID
    }

    public boolean validateJwt(String jwt, User claimingUser) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(claimingUser.getJwtSecret().toString().getBytes()).parseClaimsJws(jwt);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT is expired or invalid.", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(User checkedUser) {
        var userDetails = new SecurityUser(
                checkedUser.getId(),
                checkedUser.getNickname(),
                checkedUser.getJwtSecret(),
                checkedUser.getRole(),
                checkedUser.getStatus().equals(Status.ACTIVE)
        );
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
