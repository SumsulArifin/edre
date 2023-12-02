package com.tkgroupbd.pusti.api.data.models.entity.depot;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
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
@Table(name = "receivingEntry")
public class ReceivingEntry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receivingEntryId;
    private int quantity;
    private String inCharge;
    private String receivingTime;

    @ManyToOne
    @JoinColumn(name = "factoryId")
    private Factory factory;

    @ManyToOne
    @JoinColumn(name = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "depotId")
    private Depot depot;
}
