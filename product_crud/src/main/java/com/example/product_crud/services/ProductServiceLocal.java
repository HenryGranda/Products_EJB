package com.example.product_crud.services;

import com.example.product_crud.model.Product;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface ProductServiceLocal {
    void create(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void update(Product product);
    void delete(Long id);
}
