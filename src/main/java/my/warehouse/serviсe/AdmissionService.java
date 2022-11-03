package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.AdmissionDTO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionService extends AbstractService{

    @Autowired
    public AdmissionService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        super(productDAO, warehouseDAO);
    }

    public void admission(AdmissionDTO admissionDTO) throws DataNotFoundException {
        WarehouseDTO warehouseDTO = admissionDTO.getWarehouse();
        long idWarehouse = warehouseDAO.getId(warehouseDTO);

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
}
