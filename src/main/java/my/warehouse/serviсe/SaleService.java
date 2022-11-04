package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.documents.SaleDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public SaleService(ProductDAO productDAO, WarehouseDAO warehouseDAO){
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public void sale(SaleDTO saleDTO) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(saleDTO.getWarehouse());
        List<ProductDTO> products = saleDTO.getProducts();
        for (ProductDTO productDTO : products) {
            productDAO.delete(productDAO.getId(
                    new Product(
                            idWarehouse,
                            productDTO.getArticle(),
                            productDTO.getName(),
                            productDTO.getPriceLastPurchase(),
                            productDTO.getPriceLastSale()
                    )
            ));
        }
    }
}
