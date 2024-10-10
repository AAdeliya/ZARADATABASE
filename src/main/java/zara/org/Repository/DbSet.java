package zara.org.Repository;

import zara.org.Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DbSet  implements Serializable {
    private List<Customer> customers;
    private List<Invoice> invoices;
    private List<Order> orders;
    private List <Payment> payments;
    private List <Product> products;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public DbSet() {
        customers = new ArrayList<>();
        invoices = new ArrayList<>();
        orders = new ArrayList<>();
        payments = new ArrayList<>();
        products = new ArrayList<>();
    }
  }
