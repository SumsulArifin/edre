package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30, nullable = false)
    private String name;

    @Nullable
    private String description;
    @ManyToOne
    @JoinColumn(name = "categoryTypeId")
    private CategoryType categoryType;
}
