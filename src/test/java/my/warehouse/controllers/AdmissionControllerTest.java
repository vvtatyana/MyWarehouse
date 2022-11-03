package my.warehouse.controllers;

import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.WarehouseDTO;
import my.warehouse.serviсe.AdmissionService;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(AdmissionController.class)
@AutoConfigureMockMvc
public class AdmissionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdmissionService service;

    @Test
    public void testReturn200() throws Exception {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(new ProductDTO("article", "name", "priceLastPurchase", "priceLastSale"));
        given(service.products(new WarehouseDTO("warehouse"))).willReturn(productDTOList);
        ResultActions resultActions = mockMvc.perform(get("/api/admission").contentType(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
