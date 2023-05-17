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
public class Permission {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", updatable = false)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String permissionName;
    @Column(nullable = false)
    private String description;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "roles_permissions",
//            joinColumns = {@JoinColumn(name = "permission_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")}
//    )
//    private Set<Role> roles;

    //constructors
    public Permission(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    //methods
//    public void addRole(Role role) throws DataDuplicateException {
//        if (role == null) throw new IllegalArgumentException("role was null");
//        if (roles.contains(role)) throw new DataDuplicateException("role exists!");
//        roles.add(role);
//    }
//
//    public void removeRole(Role role) throws DataNotFoundException {
//        if (role == null) throw new IllegalArgumentException("role was null");
//        if (!roles.contains(role)) throw new DataNotFoundException("role does not exist!");
//        roles.remove(role);
//    }
}
