package com.example.product_crud.services;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBClient {
    public static ProductServiceRemote lookupRemoteEJB() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); // Cambia localhost y el puerto según tu configuración

            InitialContext context = new InitialContext(props);

            return (ProductServiceRemote) context.lookup(
                "ejb:/product-crud/ProductService!com.example.product_crud.service.ProductServiceRemote"
            );
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

