package zara.org.Repository;

import zara.org.Entities.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements  IRepository<Customer> {
    DbContext context;
    public CustomerRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Customer> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return new ArrayList<>((Collection) dbSet.getCustomers());
    }

    @Override
    public Customer GetById(int id) {
        List<Customer> customers = GetAll();
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public void Add(Customer customer) {
        List<Customer> customers = GetAll();
        customers.add(customer);
        SaveChanges(customers);

    }

    @Override
    public void Update(Customer customer) {
        List<Customer> customers = GetAll();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customer.getCustomerId()) {
                customers.set(i, customer);
                break;
            }
        }

        SaveChanges(customers);
    }

    @Override
    public void Remove(int id) {
        List<Customer> customers = GetAll();
        customers.removeIf(customer -> customer.getCustomerId() == id);
        SaveChanges(customers);
    }

    private void SaveChanges(List<Customer> customers) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setCustomers((Map<Integer, Customer>) customers);
        context.SaveChanges(dbSet);
    }

}
