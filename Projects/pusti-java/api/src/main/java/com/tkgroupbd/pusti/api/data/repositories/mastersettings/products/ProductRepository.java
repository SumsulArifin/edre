package com.tkgroupbd.pusti.api.data.repositories.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String productName);

    public List<Product> findByStatus(boolean status);

    public List<Product> findByNameContaining(String name);



}
