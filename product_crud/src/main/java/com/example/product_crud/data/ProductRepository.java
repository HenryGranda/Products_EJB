package com.example.product_crud.data;

import com.example.product_crud.model.Product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @PersistenceContext(unitName = "product_crudPersistenceUnit")
    private EntityManager entityManager;

    public void create(Product product) {
        entityManager.persist(product);
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        System.out.println("Productos encontrados: " + products.size());
        return products;
    }

    public void update(Product product) {
        entityManager.merge(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
} 