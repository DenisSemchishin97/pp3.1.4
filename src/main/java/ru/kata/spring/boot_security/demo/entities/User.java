package ru.kata.spring.boot_security.demo.entities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name="name")
private String name;
@Column(name="username")
private String username;
@Column(name="password")
private String password;
@Column(name="Age")
private Long Age;
@Column(name="Email")
private String Email;

    public Long getAge() {
        return Age;
    }

    public void setAge(Long age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "users_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
private Set<Role> roles;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setRoles(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            if (role != null) {
                if (role.equals("ROLE_ADMIN")) {
                    roleSet.add(new Role(role,1));
                }
                if (role.equals("ROLE_USER")) {
                    roleSet.add(new Role(role,2));
                }
            }
        }
        this.roles = roleSet;
    }



    public User(String name, String username, String password, Long age, String email, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        Age = age;
        Email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
    public String userRole() {
       String s=new String(roles.toString())
               .replace("[", "")
               .replace("]", "");

        return s;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
