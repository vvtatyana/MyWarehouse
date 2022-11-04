package my.warehouse.serviсe;

import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.warehouse.UpdateWarehouseDTO;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    @Autowired
    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public void add(WarehouseDTO warehouseDTO){
        warehouseDAO.insert(new Warehouse(warehouseDTO));
    }

    public void update(UpdateWarehouseDTO updateWarehouseDTO) throws DataNotFoundException {
        long id = warehouseDAO.getId(updateWarehouseDTO.getWarehouseDTO());
        warehouseDAO.update(id, updateWarehouseDTO.getNewWarehouseDTO());
    }

    public void delete(WarehouseDTO warehouseDTO) throws DataNotFoundException {
        warehouseDAO.delete(warehouseDAO.getId(warehouseDTO));
    }

    public List<WarehouseDTO> get(String name) {
        return warehouseDAO.select(name).stream().map(WarehouseDTO::new).toList();

    }

    public List<WarehouseDTO> getAll() {
        return warehouseDAO.select().stream().map(WarehouseDTO::new).toList();
    }
}
