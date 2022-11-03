package my.warehouse.dao;

import my.warehouse.dto.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class WarehouseDAO extends AbstractDAO<Warehouse> {

    public WarehouseDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Warehouse.class);
    }

    public long getId(WarehouseDTO warehouse) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("select w.id from Warehouse w where name =: value", Long.class)
                    .setParameter("value", warehouse.getName())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new DataNotFoundException("There is no warehouse data with data in the database :\n" + warehouse.toString());
        }
    }

    public void update(long id, Warehouse newWarehouse) throws DataNotFoundException {
        Warehouse warehouse = select(id);
        warehouse.setName(newWarehouse.getName());
        super.update(id, warehouse);
    }
}
