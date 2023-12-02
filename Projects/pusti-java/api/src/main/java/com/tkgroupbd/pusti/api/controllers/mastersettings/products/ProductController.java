package com.tkgroupbd.pusti.api.controllers.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.ProductRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.products.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Product Manager")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addNewProduct")
    public ResponseEntity<MessageResponse> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        MessageResponse newProduct = productService.addProduct(productRequest);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Optional<Product>> updateProduct(
            @PathVariable int productId,
            @RequestBody ProductRequest updatedProductRequest) {
        Optional<Product> product = productService.updateProduct(productId, updatedProductRequest);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/changeProductStatus/{pId}")
    public ResponseEntity<Optional<Product>> changeProductsStatus(@PathVariable Integer pId,
            @RequestBody ProductRequest productRequest) {
        Optional<Product> updateProductStatus = productService.statusChangeAPI(pId, productRequest);
        return new ResponseEntity<Optional<Product>>(updateProductStatus, HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(productList.size(), productList);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllProductsByStatus")
    public ResponseEntity<List<Product>> getAllProductByStatus(@RequestParam("status") boolean status) {
        List<Product> product = productService.getAllProductsByStatus(status);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAllProductsByName")
    public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam("name") String name) {
        List<Product> product = productService.getAllProductsByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/csv-upload")
    public ResponseEntity<MessageResponse> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        MessageResponse newRoute = productService.uploadCSV(file);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);

    }

    @GetMapping("/exel-download")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        ByteArrayInputStream stream = productService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=products.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(stream));
    }


    @GetMapping("/getPaginatedProducts")
    private Page<Product> getPaginatedBrands(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Product> products = productService.findProductByPagination(offset, pageSize);
        return products;
    }

}
