package by.andrus.askit.model;

import by.andrus.askit.model.abstracts.Auditable;
import by.andrus.askit.model.enums.Roles;
import by.andrus.askit.model.enums.Status;
import by.andrus.askit.model.types.PostgreSQLEnumType;
import lombok.Builder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Builder
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

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Like> likes;

    public User() {
    }

    public User(Long id, String nickname, UUID jwtSecret, Status status, Roles roles, List<Question> questions, List<Like> likes) {
        this.id = id;
        this.nickname = nickname;
        this.jwtSecret = jwtSecret;
        this.status = status;
        this.roles = roles;
        this.questions = questions;
        this.likes = likes;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
