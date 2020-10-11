package service.product;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Iterable<Product> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("SELECT c FROM Product AS c",Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("SELECT c FROM Product AS c WHERE c.id = :id",Product.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (product.getId() > 0){
                session.merge(product);
            }else {
                session.persist(product);
            }
            transaction.commit();
            return product;
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            Product product = findById(id);
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            return product;
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return null;
    }
}
