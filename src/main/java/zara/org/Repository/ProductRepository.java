package zara.org.Repository;

import zara.org.Entities.Customer;
import zara.org.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements  IRepository<Product> {
    DbContext context;
    public ProductRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Product> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return new ArrayList<>(dbSet.getProducts());
    }

    @Override
    public Product GetById(int id) {
        List<Product> products = GetAll();
        for (Product product: products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void Add(Product product) {
        List<Product> products = GetAll();
        products.add(product);
        SaveChanges(products);

    }

    @Override
    public void Update(Product product) {
        List<Product> products= GetAll();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                products.set(i, product);
                break;
            }
        }

        SaveChanges(products);
    }

    @Override
    public void Remove(int id) {
        List<Product> products = GetAll();
        products.removeIf(product -> product.getProductId() == id);
        SaveChanges(products);
    }

    private void SaveChanges(List<Product> products) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setProducts(products);
        context.SaveChanges(dbSet);
    }

}
