package com.tkgroupbd.pusti.api.data.models.entity.depot;


import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "depots")
public class Depot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int depotId;
    @Column(nullable = false)
    private String depotName;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Department department;

}
