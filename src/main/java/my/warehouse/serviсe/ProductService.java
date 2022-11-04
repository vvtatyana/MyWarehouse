package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.product.FullProductDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public Product add(FullProductDTO newProductDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(newProductDTO.getNameWarehouse()));
        long id = productDAO.insert(new Product(idWarehouse, newProductDTO.getProductDTO()));
        return productDAO.select(id);
    }

    public void update(long id, FullProductDTO updateProductDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(updateProductDTO.getNameWarehouse()));
        productDAO.update(id, new Product(idWarehouse, updateProductDTO.getProductDTO()));
    }

    public void delete(long id) throws DataNotFoundException {
        productDAO.delete(id);
    }

    public ProductDTO get(long id) throws DataNotFoundException {
        return new ProductDTO(productDAO.select(id));

    }
}
