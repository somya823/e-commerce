package org.example.ecommerce.controller;

import org.example.ecommerce.entity.Product;
import org.example.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/products")
    public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @PostMapping
        public Product addProduct(@RequestBody Product product) {
            return productService.addProduct(product);
        }

        @GetMapping
        public List<Product> getAllProducts() {
            return productService.getAllProducts();
        }

        @GetMapping("/{id}")
        public Product getProductById(@PathVariable Long id) {
            return productService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }

        @PutMapping("/{id}")
        public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());

            return productService.addProduct(product);
        }

        @DeleteMapping("/{id}")
        public String deleteProduct(@PathVariable Long id) {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            productService.deleteProduct(id);
            return "Product deleted successfully";
        }
    }
