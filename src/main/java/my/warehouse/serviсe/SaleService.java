package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.SaleDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService extends AbstractService{

    @Autowired
    public SaleService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        super(productDAO, warehouseDAO);
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
