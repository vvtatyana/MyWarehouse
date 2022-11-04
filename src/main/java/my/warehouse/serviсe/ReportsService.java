package my.warehouse.serviсe;

import my.warehouse.dao.ProductDAO;
import my.warehouse.dao.WarehouseDAO;
import my.warehouse.dto.documents.ReportsDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {

    protected final ProductDAO productDAO;
    protected final WarehouseDAO warehouseDAO;

    @Autowired
    public ReportsService(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    public List<ProductDTO> generalListProducts() {
        return generalListProducts(productDAO.select());
    }

    public List<ProductDTO> generalListProducts(String name) throws DataNotFoundException {
        List<ProductDTO> productDTOList = generalListProducts(productDAO.select(name));
        if (productDTOList.isEmpty())
            throw new DataNotFoundException("There is no record of a product with name = " + name + " in the database");
        return productDTOList;
    }

    public List<ProductDTO> generalListProducts(List<Product> products) {
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

    public List<ReportsDTO> remnantsGoodsInWarehouses() throws DataNotFoundException {
        return remnantsGoodsInWarehouses(productDAO.select());
    }

    public List<ReportsDTO> remnantsGoodsInWarehouses(String name) throws DataNotFoundException {
        long idWarehouse = warehouseDAO.getId(new WarehouseDTO(name));
        List<Product> products = productDAO.selectAll(idWarehouse);
        return remnantsGoodsInWarehouses(products);
    }

    private List<ReportsDTO> remnantsGoodsInWarehouses(List<Product> products) {
        List<ReportsDTO> reportsDTO = new ArrayList<>();
        for (Product product : products) {
            if (!isContains(reportsDTO, product)) {
                reportsDTO.add(
                        new ReportsDTO(
                                product.getArticle(),
                                product.getName(),
                                count(products, product)
                        )
                );
            }
        }
        return reportsDTO;
    }

    private boolean isContains(List<ReportsDTO> reportsDTO, Product product) {
        return reportsDTO.stream().anyMatch(it ->
                it.getName().equals(product.getName())
                        && it.getArticle().equals(product.getArticle()));
    }

    private int count(List<Product> products, Product product) {
        return Math.toIntExact(products.stream().filter(it ->
                it.getName().equals(product.getName())
                        && it.getArticle().equals(product.getArticle())).count());
    }
}
