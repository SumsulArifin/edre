package com.tkgroupbd.pusti.api.services.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.ProductRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    public MessageResponse addProduct(ProductRequest productRequest);

    public Optional<Product> statusChangeAPI(Integer pId, ProductRequest productRequest);

    public Product getProductById(Integer pId);

    public Optional<Product> updateProductDetailsAndPrice(String sku, ProductRequest productsRequest);

    public Optional<Product> updateProduct(int productId, ProductRequest productRequest);

    public List<Product> getAllProducts();

    public List<Product> getAllProductsByStatus(boolean status);

    public List<Product> getAllProductsByName(String status);

    public MessageResponse uploadCSV(MultipartFile file);

    public ByteArrayInputStream generateExcel() throws IOException;

    Page<Product> findProductByPagination(int offset, int pageSize);
}