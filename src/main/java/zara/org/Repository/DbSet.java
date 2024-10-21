package zara.org.Repository;

import zara.org.Entities.*;

import java.io.Serializable;
import java.util.*;

public class DbSet  implements Serializable {
    private Map<Integer, Customer> customers;
    private Map<Integer, Invoice> invoices;
    private Map<Integer, Order> orders;
    private Map<Integer, Payment> payments;
    private Map<Integer, Product> products;
    // secondary indexes
    private Map<Integer, Set<Integer>> invoiceIndex = new TreeMap<>();

    //[{id: 0, "1234556"}, {id:5, "567888"}]
    public DbSet() {
        customers = new HashMap<>(); //vs ArrayList<Customer> customers
        invoices = new HashMap<>(); // id(integer) is clustered index
        orders = new HashMap<>();
        payments = new HashMap<>();
        products = new HashMap<>();
    }

 //to not lose ownership on private lists we send a copy of the lists
    public Map<Integer, Customer> getCustomers() {
        return new HashMap<>(customers);
    }


    public void setCustomers(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }
    public Customer customer(Integer id) {
        return customers.get(id);
    }

    public Map<Integer, Invoice> getInvoices() {
        return new HashMap<>(invoices);
    }

    public void setInvoices(Map<Integer, Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addAcademyGroup(Invoice newInvoice) {
       invoices.put(newInvoice.getInvoiceId(), newInvoice);
        Set<Integer> indices = invoiceIndex.getOrDefault(newInvoice.getInvoiceId(), new HashSet<>());
        indices.add(newInvoice.getInvoiceId());
        invoiceIndex.put(newInvoice.getInvoiceId(), indices);
    }
    public void removeInvoice(int invoiceId) {
        invoices.remove(invoiceId);
        Set<Integer> indices = invoiceIndex.get(invoices.get(invoiceId).getInvoiceId());
        if (indices != null) {
            indices.remove(Integer.valueOf(invoiceId));
            if (indices.isEmpty()) {
                invoiceIndex.remove(invoices.get(invoiceId).getInvoiceId()));
            }
        }
    }

    public Map<Integer, Order> getOrders() {
        return new HashMap<>(orders);
    }

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
    }

    public Map<Integer, Payment> getPayments() {
        return new HashMap<> (payments);
    }

    public void setPayments(Map<Integer, Payment> payments) {
        this.payments = payments;
    }

    public Map<Integer, Product> getProducts() {
        return new HashMap<>(products);
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }


  }
