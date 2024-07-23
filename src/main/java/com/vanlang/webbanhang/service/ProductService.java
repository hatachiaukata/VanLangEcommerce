package com.vanlang.webbanhang.service;

import com.vanlang.webbanhang.model.Product;
import com.vanlang.webbanhang.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a product by its id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Add a new product to the database
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(@NonNull Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + product.getId() + " does not exist."));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    // Delete a product by its id
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)){
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String name, String categoryName) {
        if (name != null && !name.isEmpty() && categoryName != null && !categoryName.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCaseAndCategoryNameIgnoreCase(name, categoryName);
        } else if (name != null && !name.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(name);
        } else if (categoryName != null && !categoryName.isEmpty()) {
            return productRepository.findByCategoryNameIgnoreCase(categoryName);
        } else {
            return productRepository.findAll();
        }
    }

    public List<Product> getBestSellers() {
        // Mô phỏng lấy sản phẩm bán chạy
        // Trong thực tế, bạn sẽ truy vấn cơ sở dữ liệu để lấy sản phẩm bán chạy
        return productRepository.findAll().stream()
                .limit(4)
                .collect(Collectors.toList());
    }
}