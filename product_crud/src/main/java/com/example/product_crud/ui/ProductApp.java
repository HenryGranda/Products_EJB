package com.example.product_crud.ui;

import com.example.product_crud.controller.ProductController;
import com.example.product_crud.model.Product;
import com.example.product_crud.services.EJBClient;

import jakarta.enterprise.inject.spi.CDI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ProductApp {

    private JFrame frame;
    private JTable table;
    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtPrice;
    private ProductController productController;
    private ProductTableModel tableModel;

    public ProductApp() {
    	productController = new ProductController(EJBClient.lookupRemoteEJB());
        List<Product> productList = productController.getProductList();
        tableModel = new ProductTableModel(productList != null ? productList : new ArrayList<>());
        initialize();
        loadProducts();
    }

    private void initialize() {
        frame = new JFrame("Gestión de Productos");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblName = new JLabel("Nombre:");
        panel.add(lblName);

        txtName = new JTextField();
        panel.add(txtName);
        txtName.setColumns(10);

        JLabel lblDescription = new JLabel("Descripción:");
        panel.add(lblDescription);

        txtDescription = new JTextField();
        panel.add(txtDescription);
        txtDescription.setColumns(10);

        JLabel lblPrice = new JLabel("Precio:");
        panel.add(lblPrice);

        txtPrice = new JTextField();
        panel.add(txtPrice);
        txtPrice.setColumns(10);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Agregar Producto");
        btnAdd.addActionListener(e -> addProduct());
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Actualizar Producto");
        btnUpdate.addActionListener(e -> updateProduct());
        buttonPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Eliminar Producto");
        btnDelete.addActionListener(e -> deleteProduct());
        buttonPanel.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedProduct());
        scrollPane.setViewportView(table);
    }

    private void loadProducts() {
        try {
            List<Product> products = productController.getProductList();
            tableModel.setProducts(products != null ? products : new ArrayList<>());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al cargar los productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void addProduct() {
        try {
            Product newProduct = new Product();
            newProduct.setName(txtName.getText());
            newProduct.setDescription(txtDescription.getText());
            newProduct.setPrice(Double.parseDouble(txtPrice.getText()));

            productController.create(newProduct);
            loadProducts();

            txtName.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al agregar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProduct() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Product selectedProduct = tableModel.getProducts().get(selectedRow);
                selectedProduct.setName(txtName.getText());
                selectedProduct.setDescription(txtDescription.getText());
                selectedProduct.setPrice(Double.parseDouble(txtPrice.getText()));

                productController.update(selectedProduct);
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un producto para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Product selectedProduct = tableModel.getProducts().get(selectedRow);
                productController.delete(selectedProduct.getId());
                loadProducts();

                txtName.setText("");
                txtDescription.setText("");
                txtPrice.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSelectedProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Product selectedProduct = tableModel.getProducts().get(selectedRow);
            txtName.setText(selectedProduct.getName());
            txtDescription.setText(selectedProduct.getDescription());
            txtPrice.setText(String.valueOf(selectedProduct.getPrice()));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProductApp window = new ProductApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}   