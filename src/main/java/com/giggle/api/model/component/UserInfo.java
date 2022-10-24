package com.giggle.api.model.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "users")
public class UserInfo implements UserDetails {
    @Column(name = "name")
    @Size(min = 5)
    @NotNull
    private String username;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    @Column(name = "password")
    @Size(min = 8)
    @NotNull
    private String password;
    @Column(name = "email")
    @NotNull
    private String email;
    @Column(name = "role")
    @NotNull
    private String role;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public long getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE_PREFIX = "WIDOW_";
        return new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + role)));

    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("User = [ %s, %s, %s, %s ] ", username, password, userid, role);
    }
}
