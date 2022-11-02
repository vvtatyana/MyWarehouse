package my.warehouse.servise;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.AdmissionDTO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.models.Product;
import my.warehouse.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdmissionService {
    private final ProductDAO productDAO;
    private final WarehouseDAO warehouseDAO;

    @Autowired
    public AdmissionService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public void admission(AdmissionDTO admissionDTO) {
        WarehouseDTO warehouseDTO = admissionDTO.getWarehouse();
        long idWarehouse = warehouseDAO.getId(warehouseDTO);
        if (idWarehouse == 0)
            idWarehouse = warehouseDAO.insert(new Warehouse(warehouseDTO.getName()));

        List<ProductDTO> products = admissionDTO.getProducts();
        for (ProductDTO productDTO : products) {
            productDAO.insert(
                    new Product(
                            idWarehouse,
                            productDTO.getArticle(),
                            productDTO.getName(),
                            productDTO.getPriceLastPurchase(),
                            productDTO.getPriceLastSale()
                    )
            );
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
