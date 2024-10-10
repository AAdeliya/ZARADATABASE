package zara.org.Repository;

import zara.org.Entities.Payment;
import zara.org.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements  IRepository<Payment> {
    DbContext context;
    public PaymentRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Payment> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return new ArrayList<>(dbSet.getPayments());
    }

    @Override
    public Payment GetById(int id) {
        List<Payment> payments = GetAll();
        for (Payment payment : payments) {
            if (payment.getPaymentId() == id) {
                return payment;
            }
        }
        return null;
    }

    @Override
    public void Add(Payment payment) {
        List<Payment> payments = GetAll();
        payments.add(payment);
        SaveChanges(payments);

    }

    @Override
    public void Update(Payment payment) {
        List<Payment> payments= GetAll();
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId() == payment.getPaymentId()) {
                payments.set(i, payment);
                break;
            }
        }

        SaveChanges(payments);
    }

    @Override
    public void Remove(int id) {
        List<Payment> payments = GetAll();
        payments.removeIf(payment -> payment.getPaymentId() == id);
        SaveChanges(payments);
    }

    private void SaveChanges(List<Payment> payments) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setPayments(payments);
        context.SaveChanges(dbSet);
    }

}
