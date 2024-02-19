package com.example.JPATest.service;


import com.example.JPATest.exceptions.EntityNotFoundException;
import com.example.JPATest.model.Product;
import com.example.JPATest.model.Todo;
import com.example.JPATest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public List<Product> get() {
        List<Product> product = productRepository.findAll();
        return product;
    }

    public Optional<Product> getById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product;
    }


    public Product updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            productOptional.get().setName(product.getName());
            Product updatedProduct = productRepository.save(productOptional.get());
            return updatedProduct;
        } else {
            throw new EntityNotFoundException("Product with the given id is not exist. Please check Products Items");
        }
    }

    public String deleteById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return "Product Deleted";
        } else {
            throw new EntityNotFoundException("Product with the given id is not exist. Please check Products Items");
        }
    }

}
