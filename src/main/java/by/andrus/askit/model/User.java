package by.andrus.askit.model;

import by.andrus.askit.model.abstracts.Auditable;
import by.andrus.askit.model.enums.Roles;
import by.andrus.askit.model.enums.Status;
import by.andrus.askit.model.types.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class User extends Auditable<Long> {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "jwt_secret", nullable = false)
    private UUID jwtSecret;
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "user_status")
    private Status status;
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "user_role")
    private Roles roles;

    @OneToMany(mappedBy = "author",fetch=FetchType.LAZY)
    private List<Question> questions;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UUID getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(UUID jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Roles getRole() {
        return roles;
    }

    public void setRole(Roles roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(nickname, user.nickname) && jwtSecret.equals(user.jwtSecret) && status == user.status && roles == user.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, jwtSecret, status, roles);
    }
}
