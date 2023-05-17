package g45_lexicon.teat.model.entity;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
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
//    @OneToMany(cascade = CascadeType.ALL,
//                mappedBy = "role")
//    private Set<User> users;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
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
//    public void addUser(User user) {
//        if (user != null && !users.contains(user)) users.add(user);
//    }
//    public void removeUser(User user) {
//
//        if (user != null && users.contains(user)) users.remove(user);
//    }

    public void addPermission(Permission permission) throws DataDuplicateException {
        if (permission == null) throw new IllegalArgumentException("Permission was null!");
        if (permissions.contains(permission)) throw new DataDuplicateException("Permission exists!");
        permissions.add(permission);
    }

    public void removePermission(Permission permission) throws DataNotFoundException {
        if (permission == null) throw new IllegalArgumentException("Permission was null");
        if (!permissions.contains(permission)) throw new DataNotFoundException("Permission does not exist!");
        permissions.remove(permission);
    }
}
