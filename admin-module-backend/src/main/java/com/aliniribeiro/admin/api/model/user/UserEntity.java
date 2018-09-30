package com.aliniribeiro.admin.api.model.user;

import com.aliniribeiro.admin.api.model.user.enums.UserProfile;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "useraccount")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank(message = "Email required")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6)
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile", nullable = false)
    private UserProfile profile;

    @NotBlank(message = "Password required")

    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "activateddate")
    private LocalDate activatedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(LocalDate activatedDate) {
        this.activatedDate = activatedDate;
    }
}
