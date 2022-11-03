package my.warehouse.dto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdmissionDTOTest {
    @Test
    public void admissionDTOTest() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(new ProductDTO("article", "name", "priceLastPurchase", "priceLastSale"));
        WarehouseDTO warehouseDTO = new WarehouseDTO("warehouse");
        AdmissionDTO admissionDTO = new AdmissionDTO("number", warehouseDTO, productDTOList);

        assertEquals("number", admissionDTO.getNumber());
        assertEquals(warehouseDTO, admissionDTO.getWarehouse());
        assertEquals(productDTOList, admissionDTO.getProducts());
    }
}
