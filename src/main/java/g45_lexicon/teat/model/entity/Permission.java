package g45_lexicon.teat.model.entity;

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
    @Column(name = "permission_id",nullable = false, updatable = false)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String permissionName;
    @Column(nullable = false)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_permissions",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    //constructors
    public Permission(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    //methods
    public void addRole(Role role) {
        if (role != null && !roles.contains(role)) roles.add(role);
    }

    public void removeRole(Role role) {
        if (role != null && roles.contains(role)) roles.remove(role);
    }
}
