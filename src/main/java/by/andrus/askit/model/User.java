package by.andrus.askit.model;

import by.andrus.askit.model.abstracts.Auditable;
import by.andrus.askit.model.enums.Role;
import by.andrus.askit.model.enums.Status;
import by.andrus.askit.model.types.PostgreSQLEnumType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class User extends Auditable<Long> {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
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
    private Role role;
}
