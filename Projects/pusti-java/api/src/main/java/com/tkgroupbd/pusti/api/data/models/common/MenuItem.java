package com.tkgroupbd.pusti.api.data.models.common;
import com.tkgroupbd.pusti.api.data.models.enums.GroupId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "menuItems")
public class MenuItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuItemsId;
    @Enumerated(EnumType.STRING)
    private GroupId groupId;

    private String title;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_permission",
            joinColumns = {
                    @JoinColumn(name = "menuItemsId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "uMPid")
            }
    )
    private Set<UsersMenuPermission> usersMenuPermissions = new HashSet<>();

}
