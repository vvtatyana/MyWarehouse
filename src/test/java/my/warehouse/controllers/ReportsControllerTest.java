package my.warehouse.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGeneralListProducts() throws Exception {
        mvc.perform(get("/api/report/generalListProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Холодильник")
        ).andExpect(status().isOk());
    }

    @Test
    public void testGeneralListProductsEmpty() throws Exception {
        mvc.perform(get("/api/report/generalListProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(status().isOk());
    }

    @Test
    public void testRemnantsGoodsInWarehouses() throws Exception {
        mvc.perform(get("/api/report/remnantsGoodsInWarehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Склад 1")
        ).andExpect(status().isOk());
    }

    @Test
    public void testRemnantsGoodsInWarehousesEmpty() throws Exception {
        mvc.perform(get("/api/report/remnantsGoodsInWarehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(status().isOk());
    }
}
