package org.launchcode.final_KIM_STAHL.model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 255)
    private static String pwHash;

    @Column(nullable = false, length = 100)
    private String medium;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String name, String email, String password, String medium) {
        this.name = name;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.medium = medium;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPwHash() {
        return pwHash;
    }

    public static void setPwHash(String pwHash) {
        User.pwHash = pwHash;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}