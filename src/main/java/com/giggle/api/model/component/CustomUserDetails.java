package com.giggle.api.model.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//@Entity
//@Table(name = "users")
public class CustomUserDetails implements UserDetails {

    private static long userCount = 1;
    //    @Column(name = "name")
    private String username;
    //    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    //    @Column(name = "password")
    private String password;
    //    @Column(name = "email")
    private String email;
    //    @Column(name = "role")
    private String role;

    public CustomUserDetails() {
        System.out.println(this);
    }

    public CustomUserDetails(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        System.out.println(this);
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
        return String.format("User = [ %S, %S, %S, %S ] ", username, password, userid, role);
    }
}
