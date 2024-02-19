package com.example.JPATest.contoller;


import com.example.JPATest.beans.ResponseHandler;
import com.example.JPATest.model.Product;
import com.example.JPATest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/read")
    public List<Product> getAll() {
        List<Product> product = productService.get();
        return product;
    }

    @GetMapping("/read/{id}")
    public Optional<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        return product;
    }

    @PostMapping("/create")
    public Object create(@RequestBody Product product) {
        Product productOptional = productService.create(product);
        if (productOptional != null) {
            return ResponseHandler.createResponse("Product added", HttpStatus.CREATED, productOptional);
        }
        return ResponseHandler.createResponse("Product already exist ", HttpStatus.CONFLICT, productOptional);
    }

    @PutMapping("/update")
    public Object update(@RequestBody Product product) {
        Object product1 = productService.updateProduct(product);
        return ResponseHandler.createResponse("Customer name updated", HttpStatus.OK, product1);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "Delete successfully";
    }

}
