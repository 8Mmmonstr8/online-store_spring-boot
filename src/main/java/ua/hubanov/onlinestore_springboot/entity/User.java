package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 30, message = "First Name should be between 2 and 30 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "Email should not be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Builder.Default
//    private List<UserRole> role = new ArrayList<UserRole>(Collections.singleton(UserRole.USER));
    private UserRole role = UserRole.USER;

    @Column(name = "isNonLocked")
    @Builder.Default
    private boolean isNonLocked = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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


    // ToDo
    public void setRole(UserRole role) {
        this.role = role;
    }

    // Todo
    @Override
    public List<UserRole> getAuthorities() {
        return new ArrayList<>(Collections.singleton(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked;
    }

    public void setAccountLocked() {
        isNonLocked = false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
