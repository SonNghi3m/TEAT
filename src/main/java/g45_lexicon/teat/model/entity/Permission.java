package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


    //constructors
    public Permission(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    //methods
}
