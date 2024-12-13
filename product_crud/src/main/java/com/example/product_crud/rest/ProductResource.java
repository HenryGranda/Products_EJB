package com.example.product_crud.rest;

import java.util.List;

import com.example.product_crud.model.Product;
import com.example.product_crud.services.ProductService;
import com.example.product_crud.services.ProductServiceLocal;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    private ProductServiceLocal productService;

    @POST
    public void create(Product product) {
        productService.create(product);
    }

    @GET
    @Path("/{id}")
    public Product findById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PUT
    public void update(Product product) {
        productService.update(product);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        productService.delete(id);
    }
}

