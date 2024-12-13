package com.example.product_crud.services;

import com.example.product_crud.data.ProductRepository;
import com.example.product_crud.model.Product;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class ProductService implements ProductServiceLocal, ProductServiceRemote {

    @Inject
    private ProductRepository productRepository;

    @Override
    public void create(Product product) {
        productRepository.create(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}