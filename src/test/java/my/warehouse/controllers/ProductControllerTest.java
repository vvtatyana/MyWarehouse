package my.warehouse.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPost() throws Exception {
        String JSON = "{\n" +
                "  \"nameWarehouse\": \"Склад 1\",\n" +
                "  \"productDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "  }\n" +
                "}";

        mvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)

        ).andExpect(status().isOk());
    }

    @Test
    public void testPut() throws Exception {
        String JSON = "{\n" +
                "  \"nameWarehouse\": \"Склад 1\",\n" +
                "  \"productDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "  },\n" +
                "  \"newProductDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"100000\",\n" +
                "    \"priceLastSale\": \"100000\"\n" +
                "  }\n" +
                "}";

        mvc.perform(put("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        String JSON = "{\n" +
                "  \"nameWarehouse\": \"Склад 1\",\n" +
                "  \"productDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "  }\n" +
                "}";

        mvc.perform(delete("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        mvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Холодильник")
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetEmpty() throws Exception {
        mvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(status().isOk());
    }
}
