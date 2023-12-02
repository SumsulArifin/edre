package com.tkgroupbd.pusti.api.services.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.*;
import com.tkgroupbd.pusti.api.data.models.enums.UnitId;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.*;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.brands.BrandRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.products.*;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private ProductRepository productRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    ProductPriceRepository productPriceRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    ProductMetaRepository productMetaRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    CategoryRepository categoryRepository;

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    BrandRepository brandRepository;

    @Override
    @Transactional(value = TxType.REQUIRED)
    public MessageResponse addProduct(ProductRequest productRequest) {

        try {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setBengaliName(productRequest.getBengaliName());
            product.setErpId(productRequest.getErpId());
            product.setSkuNumber(productRequest.getSkuNumber());
            product.setUnitId(productRequest.getUnitId());
            product.setDistributionPrice(productRequest.getDistributionPrice());
            product.setTradingPrice(productRequest.getTradingPrice());
            product.setWeightPerUnit(productRequest.getWeightPerUnit());
            product.setSellingPackSize(productRequest.getSellingPackSize());
            product.setSellingCartoonSize(productRequest.getSellingCartoonSize());
            product.setNewProduct(productRequest.isNewProduct());
            product.setOfferRunning(productRequest.isOfferRunning());
            product.setDistributionGiftAvailable(productRequest.isDistributionGiftAvailable());
            product.setSMSActive(productRequest.isSMSActive());
            product.setInStock(productRequest.isInStock());
            product.setOpeningDate(productRequest.getOpeningDate());
            product.setClosingDate(productRequest.getClosingDate());
            product.setActive(productRequest.isActive());
            product.setCategory(productRequest.getCategory());
            product.setBrand(productRequest.getBrand());

            productRepository.save(product);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage() + " The Reason is : " + e.getCause());
        }

    }

    @Override
    public Optional<Product> statusChangeAPI(Integer pId, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(pId);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product", "pId", pId);
        } else
            product.get().setStatus(productRequest.isStatus());
        ;
        productRepository.save(product.get());
        return product;
    }

    @Override
    public Product getProductById(Integer pId) {
        return productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", pId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByStatus(boolean status) {
        return productRepository.findByStatus(status);
    }

    public List<Product> getAllProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Optional<Product> updateProductDetailsAndPrice(String sku, ProductRequest productsRequest) {
        return Optional.empty();
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public Optional<Product> updateProduct(int productId, ProductRequest productRequest) {
        Optional<Product> products = productRepository.findById(productId);

        try {
            // Retrieve the existing product entity
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();

                // Update the product entity with the new values from the request

                product.setName(productRequest.getName());
                product.setBengaliName(productRequest.getBengaliName());
                product.setErpId(productRequest.getErpId());
                product.setSkuNumber(productRequest.getSkuNumber());
                product.setUnitId(productRequest.getUnitId());
                product.setDistributionPrice(productRequest.getDistributionPrice());
                product.setTradingPrice(productRequest.getTradingPrice());
                product.setWeightPerUnit(productRequest.getWeightPerUnit());
                product.setSellingPackSize(productRequest.getSellingPackSize());
                product.setSellingCartoonSize(productRequest.getSellingCartoonSize());
                product.setNewProduct(productRequest.isNewProduct());
                product.setOfferRunning(productRequest.isOfferRunning());
                product.setDistributionGiftAvailable(productRequest.isDistributionGiftAvailable());
                product.setSMSActive(productRequest.isSMSActive());
                product.setInStock(productRequest.isInStock());
                product.setOpeningDate(productRequest.getOpeningDate());
                product.setClosingDate(productRequest.getClosingDate());
                product.setActive(productRequest.isActive());
                product.setStatus(productRequest.isStatus());
                product.setCategory(productRequest.getCategory());
                product.setBrand(productRequest.getBrand());

                // Save the updated product entity
                productRepository.save(product);
            }
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }
        return products;
    }

    @Override
    public MessageResponse uploadCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            DateTimeFormatter[] formatters = {
                    DateTimeFormatter.ofPattern("M/d/yyyy"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                    DateTimeFormatter.ofPattern("yyyy MMM d", Locale.ENGLISH),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd") // Add the new format here
                    // Add more date formats as needed
            };

            List<String> errorMessages = new ArrayList<>(); // Store error messages for detailed feedback

            reader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 21) {
                    Product product = new Product();
                    try {
                        product.setName(data[0].toLowerCase().trim());
                        product.setBengaliName(data[1]);
                        product.setErpId(data[2]);
                        product.setSkuNumber(data[3]);
                        product.setUnitId(UnitId.valueOf(data[4]));
                        product.setDistributionPrice(Double.parseDouble(data[5]));
                        product.setTradingPrice(Double.parseDouble(data[6]));
                        product.setWeightPerUnit(Double.parseDouble(data[7]));
                        product.setSellingPackSize(Integer.parseInt(data[8]));
                        product.setSellingCartoonSize(Integer.parseInt(data[9]));
                        product.setNewProduct(Boolean.parseBoolean(data[10]));
                        product.setOfferRunning(Boolean.parseBoolean(data[11]));
                        product.setDistributionGiftAvailable(Boolean.parseBoolean(data[12]));
                        product.setSMSActive(Boolean.parseBoolean(data[13]));
                        product.setInStock(Boolean.parseBoolean(data[14]));

                        LocalDate openingDate = null;
                        LocalDate closingDate = null;
                        boolean dateParsed = false;

                        // Try parsing date with different formats
                        for (DateTimeFormatter formatter : formatters) {
                            try {
                                openingDate = LocalDate.parse(data[15], formatter);
                                closingDate = LocalDate.parse(data[16], formatter);
                                dateParsed = true;
                                break; // If successfully parsed, exit the loop
                            } catch (Exception e) {
                                // Continue to the next format if parsing fails
                            }
                        }

                        if (!dateParsed) {
                            errorMessages.add("Error parsing date at line: " + line);
                        } else {
                            // Set the parsed dates to the product object
                            product.setOpeningDate(openingDate);
                            product.setClosingDate(closingDate);
                        }

                        product.setActive(Boolean.parseBoolean(data[17]));
                        product.setStatus(Boolean.parseBoolean(data[18]));

                        // Fetch Category and Brand IDs
                        int categoryId = Integer.parseInt(data[19]);
                        int brandId = Integer.parseInt(data[20]);

                        // Fetch Category and Brand from repository
                        Optional<Category> category = categoryRepository.findById(categoryId);
                        Optional<Brand> brand = brandRepository.findById(brandId);

                        if (category.isPresent() && brand.isPresent()) {
                            product.setCategory(category.get());
                            product.setBrand(brand.get());
                            productRepository.save(product);
                        } else {
                            errorMessages.add("Invalid category ID or brand ID at line: " + line);
                        }
                    } catch (Exception e) {
                        errorMessages.add("Error processing line: " + line + ". Reason: " + e.getMessage());
                    }
                } else {
                    errorMessages.add("Invalid data at line: " + line);
                }
            });

            if (errorMessages.isEmpty()) {
                return new MessageResponse(Message.SUCCESS_CSV_UPLOAD);
            } else {
                // Construct detailed error message
                StringBuilder errorMessage = new StringBuilder("Errors occurred while processing CSV file:\n");
                for (String error : errorMessages) {
                    errorMessage.append(error).append("\n");
                }
                return new MessageResponse(errorMessage.toString());
            }

        } catch (Exception e) {
            return new MessageResponse("Error while processing CSV file: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream generateExcel() throws IOException {
        String[] columns = {"Product Id", "Name", "Bengali Name", "ERP ID", "SKU Number", "Unit ID",
                "Distribution Price",
                "Trading Price", "Weight Per Unit", "Selling Pack Size", "Selling Cartoon Size", "New Product",
                "Offer Running", "Distribution Gift Available", "SMS Active", "In Stock", "Opening Date",
                "Closing Date", "Active", "Status", "Category", "Brand"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Products");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create header row
            Row headerRow = sheet.createRow(0);

            // Populate header cells
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Populate data rows
            List<Product> products = productRepository.findAll();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int rowNum = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(product.getProductId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getBengaliName());
                row.createCell(3).setCellValue(product.getErpId());
                row.createCell(4).setCellValue(product.getSkuNumber());
                row.createCell(5).setCellValue(String.valueOf(product.getUnitId()));
                row.createCell(6).setCellValue(product.getDistributionPrice());
                row.createCell(7).setCellValue(product.getTradingPrice());
                row.createCell(8).setCellValue(product.getWeightPerUnit());
                row.createCell(9).setCellValue(product.getSellingPackSize());
                row.createCell(10).setCellValue(product.getSellingCartoonSize());
                row.createCell(11).setCellValue(product.isNewProduct());
                row.createCell(12).setCellValue(product.isOfferRunning());
                row.createCell(13).setCellValue(product.isDistributionGiftAvailable());
                row.createCell(14).setCellValue(product.isSMSActive());
                row.createCell(15).setCellValue(product.isInStock());
                row.createCell(16).setCellValue(product.getOpeningDate().format(dateFormatter));
                row.createCell(17).setCellValue(product.getClosingDate().format(dateFormatter));
                row.createCell(18).setCellValue(product.isActive());
                row.createCell(19).setCellValue(product.isStatus());
                row.createCell(20).setCellValue(Integer.valueOf(product.getCategory().getId()));
                row.createCell(21).setCellValue(Integer.valueOf(product.getBrand().getBrandId()));
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    @Override
    public Page<Product> findProductByPagination(int offset, int pageSize) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }


}
