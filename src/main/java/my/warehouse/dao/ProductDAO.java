package my.warehouse.dao;

import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Product.class);
    }

    public List<Product> selectAll(long idWarehouse) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product where idWarehouse =: value", Product.class)
                .setParameter("value", idWarehouse).getResultList();
    }

    public long getId(Product product) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        List<Long> result = session.createQuery("select p.id from Product p where idWarehouse =: warehouseV " +
                            "and article =: articleV " +
                            "and name =: nameV " +
                            "and priceLastPurchase =: priceLastPurchaseV " +
                            "and priceLastSale =: priceLastSale", Long.class)
                    .setParameter("warehouseV", product.getIdWarehouse())
                    .setParameter("articleV", product.getArticle())
                    .setParameter("nameV", product.getName())
                    .setParameter("priceLastPurchaseV", product.getPriceLastPurchase())
                    .setParameter("priceLastSale", product.getPriceLastSale())
                    .getResultList();
        if (result.isEmpty())
            throw new DataNotFoundException("There is no product data with data in the database :\n" + product);
        return result.get(0);
    }

    public void update(long id, Product newProduct) throws DataNotFoundException {
        Product product = select(id);
        product.setIdWarehouse(newProduct.getIdWarehouse());
        product.setName(newProduct.getName());
        product.setArticle(newProduct.getArticle());
        product.setPriceLastPurchase(newProduct.getPriceLastPurchase());
        product.setPriceLastSale(newProduct.getPriceLastSale());
        super.update(id, product);
    }
}
