package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.product.FullProductDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.dto.product.UpdateProductDTO;
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

    public void add(FullProductDTO newProductDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(newProductDTO.getNameWarehouse()));
        productDAO.insert(new Product(idWarehouse, newProductDTO.getProductDTO()));
    }

    public void update(UpdateProductDTO updateProductDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(updateProductDTO.getNameWarehouse()));
        long idProduct = productDAO.getId(new Product(idWarehouse, updateProductDTO.getProductDTO()));
        productDAO.update(idProduct, new Product(idWarehouse, updateProductDTO.getNewProductDTO()));
    }

    public void delete(FullProductDTO newProductDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(newProductDTO.getNameWarehouse()));
        long idProduct = productDAO.getId(new Product(idWarehouse, newProductDTO.getProductDTO()));
        productDAO.delete(idProduct);
    }

    public List<ProductDTO> get(String name) {
        return productDAO.select(name).stream().map(ProductDTO::new).toList();

    }

    public List<ProductDTO> getAll() {
        return productDAO.select().stream().map(ProductDTO::new).toList();
    }
}
