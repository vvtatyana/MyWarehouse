package my.warehouse.dao;

import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WarehouseDAO extends AbstractDAO<Warehouse> {

    public WarehouseDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Warehouse.class);
    }

    public long getId(WarehouseDTO warehouse) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();

        List<Long> result = session.createQuery("select w.id from Warehouse w where name =: value", Long.class)
                    .setParameter("value", warehouse.getName())
                    .getResultList();

        if (result.isEmpty())
            throw new DataNotFoundException("There is no warehouse data with data in the database :\n" + warehouse);
        return result.get(0);
    }

    public void update(long id, WarehouseDTO newWarehouse) throws DataNotFoundException {
        Warehouse warehouse = select(id);
        warehouse.setName(newWarehouse.getName());
        super.update(id, warehouse);
    }

    public long insert(Warehouse value) {
        List<Warehouse> warehouses = select(value.getName());
        if (warehouses.isEmpty()) return super.insert(value);
        else return warehouses.get(0).getId();
    }
}
