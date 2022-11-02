package my.warehouse.dao;

import my.warehouse.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> select() {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("select p from Product p", Product.class).getResultList();
        session.close();
        return products;
    }

    public Product select(long id) {
        Session session = sessionFactory.openSession();
        Product product = session.createQuery("select p from Product p where id =: value", Product.class)
                .setParameter("value", id).getResultList().stream().findFirst().orElse(null);
        session.close();
        return product;
    }

    public List<Product> selectAll(long idWarehouse) {
        Session session = sessionFactory.openSession();
        List<Product> product = session.createQuery("select p from Product p where idWarehouse =: value", Product.class)
                .setParameter("value", idWarehouse).getResultList();
        session.close();
        return product;
    }

    public long getId(Product product) {
        Session session = sessionFactory.openSession();
        Long id = session.createQuery("select p.id from Product p where idWarehouse =: warehouseV and article =: articleV and name =: nameV and priceLastPurchase =: priceLastPurchaseV and priceLastSale =: priceLastSale", Long.class)
                .setParameter("warehouseV", product.getIdWarehouse())
                .setParameter("articleV", product.getArticle())
                .setParameter("nameV", product.getName())
                .setParameter("priceLastPurchaseV", product.getPriceLastPurchase())
                .setParameter("priceLastSale", product.getPriceLastSale())
                .getResultList().stream().findFirst().orElse(null);
        session.close();
        if (id != null)
            return id;
        else return 0;
    }

    public void insert(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public void update(long id, Product newProduct) {
        Session session = sessionFactory.openSession();
        Product product = select(id);
        product.setIdWarehouse(newProduct.getIdWarehouse());
        product.setName(newProduct.getName());
        product.setArticle(newProduct.getArticle());
        product.setPriceLastPurchase(newProduct.getPriceLastPurchase());
        product.setPriceLastSale(newProduct.getPriceLastSale());
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Product.class, id));
        session.getTransaction().commit();
        session.close();
    }

}
