package my.warehouse.servise;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.MovingDTO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.models.Product;
import my.warehouse.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovingService {
    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public MovingService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public void moving(MovingDTO movingDTO) {
        long idWarehouseOne = warehouseDAO.getId(movingDTO.getWarehouseOne());
        long idWarehouseTwo = warehouseDAO.getId(movingDTO.getWarehouseTwo());
        if (idWarehouseTwo == 0)
            idWarehouseTwo = warehouseDAO.insert(new Warehouse(movingDTO.getWarehouseTwo().getName()));

        List<ProductDTO> products = movingDTO.getProducts();
        for (ProductDTO productDTO : products) {
            long idProduct = productDAO.getId(
                    new Product(
                            idWarehouseOne,
                            productDTO.getArticle(),
                            productDTO.getName(),
                            productDTO.getPriceLastPurchase(),
                            productDTO.getPriceLastSale()
                    ));
            productDAO.update(
                    idProduct,
                    new Product(
                            idWarehouseTwo,
                            productDTO.getArticle(),
                            productDTO.getName(),
                            productDTO.getPriceLastPurchase(),
                            productDTO.getPriceLastSale()
                    ));
        }
    }

    public List<ProductDTO> products(WarehouseDTO warehouseDTO){
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
