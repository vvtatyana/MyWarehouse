package my.warehouse.servise;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.SaleDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public SaleService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public void sale(SaleDTO saleDTO) {
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
