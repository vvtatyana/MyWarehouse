package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.documents.MovingDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import my.warehouse.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovingService{

    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public MovingService(ProductDAO productDAO, WarehouseDAO warehouseDAO){
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public void moving(MovingDTO movingDTO) throws DataNotFoundException {
        long idWarehouseOne = warehouseDAO.getId(movingDTO.getWarehouseOne());
        long idWarehouseTwo = warehouseDAO.getId(movingDTO.getWarehouseTwo());
        if (idWarehouseTwo == 0)
            idWarehouseTwo = warehouseDAO.insert(new Warehouse(movingDTO.getWarehouseTwo()));

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
}
