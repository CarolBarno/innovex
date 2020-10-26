package com.innovex.Innovex.UserManager.domain;

import com.innovex.Innovex.UserManager.domain.enums.UserTypeEnum;
import com.innovex.Innovex.utilities.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import lombok.Data;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "System_Users")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstName;
    private String lastName;
    private String surName;
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userTypeEnum;
    private String phoneNumber;
    private String dob;
    private String idNumber;
    private LocalDateTime lastLogin;
    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    private boolean verified;

    private boolean OTP;

    /**
     * Roles are being eagerly loaded here because they are a fairly small
     * collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
    }

    public User(String username, String email, String password, String firstName, String lastName, String surName, UserTypeEnum userTypeEnum, String phoneNumber, String dob, String idNumber, LocalDateTime lastLogin, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired, boolean verified, boolean OTP, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.userTypeEnum = userTypeEnum;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.idNumber = idNumber;
        this.lastLogin = lastLogin;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.verified = verified;
        this.OTP = OTP;
        this.roles = roles;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    /*
	 * Get roles and permissions and add them as a Set of GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            r.getPermissions().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });

        return authorities;
    }

}
