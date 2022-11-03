package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractService {

    protected final ProductDAO productDAO;
    protected final WarehouseDAO warehouseDAO;

    public AbstractService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public List<ProductDTO> products(WarehouseDTO warehouseDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(warehouseDTO);
        List<Product> products = productDAO.selectAll(idWarehouse);
        List<ProductDTO> productDTO = new ArrayList<>();
        for (Product product : products) {
            productDTO.add(
                    new ProductDTO(
                            product.getArticle(),
                            product.getName(),
                            product.getPriceLastPurchase(),
                            product.getPriceLastSale()
                    )
            );
        }
        return productDTO;
    }
}
