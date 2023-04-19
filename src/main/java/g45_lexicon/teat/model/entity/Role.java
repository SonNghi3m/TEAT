package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String roleTitle;
    @OneToMany(cascade = CascadeType.ALL,
                mappedBy = "role")
    private Set<User> users;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_permissions",
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    //constructors
    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    //methods
    public void addUser(User user) {
        if (user != null && !users.contains(user)) users.add(user);
    }
    public void removeUser(User user) {

        if (user != null && users.contains(user)) users.remove(user);
    }

    public void addPermission(Permission permission) {
        if (permission != null && !permissions.contains(permission)) permissions.add(permission);
    }

    public void removePermission(Permission permission) {
       if (permission != null && permissions.contains(permission)) permissions.remove(permission);
    }
}
