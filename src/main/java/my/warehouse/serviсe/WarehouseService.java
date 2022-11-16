package my.warehouse.serviсe;

import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    @Autowired
    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Warehouse add(WarehouseDTO warehouseDTO) throws DataNotFoundException {
        long id = warehouseDAO.insert(new Warehouse(warehouseDTO));
        return warehouseDAO.select(id);
    }

    public void update(long id, WarehouseDTO updateWarehouseDTO) throws DataNotFoundException {
        warehouseDAO.update(id, updateWarehouseDTO);
    }

    public void delete(long id) throws DataNotFoundException {
        warehouseDAO.delete(id);
    }

    public WarehouseDTO get(long id) throws DataNotFoundException {
        return new WarehouseDTO(warehouseDAO.select(id));
    }
}
