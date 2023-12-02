package com.tkgroupbd.pusti.api.data.repositories.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice,Integer> {

    @Query("select r from ProductPrice r  join ProductItem z on r.sku = z.sku where z.sku=?1 ")
    Optional<ProductPrice> findBySku(String sku);
}
