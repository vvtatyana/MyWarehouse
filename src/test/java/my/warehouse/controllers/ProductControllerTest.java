package my.warehouse.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    private static long id;

    @Test
    public void test1Post() throws Exception {
        String JSON = "{\n" +
                "  \"nameWarehouse\": \"Склад 1\",\n" +
                "  \"productDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "  }\n" +
                "}";

        String body = mvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        int start = body.indexOf("id") + 4;
        int end = body.indexOf("idWarehouse") - 2;
        id = Long.parseLong(body.substring(start, end));
    }

    @Test
    public void test2Put() throws Exception {
        String JSON = "{\n" +
                "  \"nameWarehouse\": \"Склад 1\",\n" +
                "  \"productDTO\": {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"10000\",\n" +
                "    \"priceLastSale\": \"10000\"\n" +
                "  }\n" +
                "}";

        mvc.perform(put("/api/product/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void test3Get() throws Exception {
        mvc.perform(get("/api/product/" + id)).andExpect(status().isOk());
    }

    @Test
    public void test4Delete() throws Exception {
        mvc.perform(delete("/api/product/" + id)).andExpect(status().isOk());
    }
}
