package com.springboot.data.dao.impl;

import com.springboot.data.dao.ProductDAO;
import com.springboot.data.entity.Product;
import com.springboot.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectProduct = productRepository.getById(number);

        return selectProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else{
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
