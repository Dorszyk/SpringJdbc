package org.project.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;


    @Transactional
    public Product create(Product product) {
        return productRepository.createProduct(product);
    }

    public Product find(final Long productId) {
        return productRepository.find(productId)
                .orElseThrow(() -> new RuntimeException(String.format("Product with id: [%s] doesn't exist", productId)));
    }

    public Product find(final String productCode) {
        return productRepository.find(productCode)
                .orElseThrow(() -> new RuntimeException(String.format("Product with productCode: [%s] doesn't exist", productCode)));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void remove(String productCode) {
        productRepository.remove(productCode);
    }

    @Transactional
    public void removeAll() {
        productRepository.removeAll();
    }


}
