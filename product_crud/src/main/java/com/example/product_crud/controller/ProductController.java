package com.example.product_crud.controller;

import com.example.product_crud.model.Product;
import com.example.product_crud.services.ProductServiceLocal;
import com.example.product_crud.services.ProductServiceRemote;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ProductController {

    private ProductServiceRemote productService;
    private List<Product> productList;

    public ProductController(ProductServiceRemote productService) {
        this.productService = productService;
        this.productList = productService.findAll();
    }

    public List<Product> getProductList() {
        return productList != null ? productList : new ArrayList<>();
    }

    public void create(Product product) {
        productService.create(product);
        productList = productService.findAll();
    }

    public void update(Product product) {
        productService.update(product);
        productList = productService.findAll();
    }

    public void delete(Long id) {
        productService.delete(id);
        productList = productService.findAll();
    }
}
