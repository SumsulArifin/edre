package com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductMeta")
public class ProductMeta extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proMetaId;

    private String key;
    private String imageUrl;
    private String content;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
