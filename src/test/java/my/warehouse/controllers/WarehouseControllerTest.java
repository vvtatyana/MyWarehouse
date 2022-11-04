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
public class WarehouseControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPost() throws Exception {
        String JSON = "{\n" +
                "  \"name\": \"Склад 5\"\n" +
                "}";

        mvc.perform(post("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testPut() throws Exception {
        String JSON = "{\n" +
                "  \"warehouseDTO\": {\n" +
                "    \"name\": \"Склад 5\"\n" +
                "  },\n" +
                "  \"newWarehouseDTO\": {\n" +
                "    \"name\": \"Склад 6\"\n" +
                "  }\n" +
                "}";

        mvc.perform(put("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        String JSON = "{\n" +
                "  \"name\": \"Склад 10\"\n" +
                "}";
        mvc.perform(post("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());

        mvc.perform(delete("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        mvc.perform(get("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Склад 1")
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetEmpty() throws Exception {
        mvc.perform(get("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(status().isOk());
    }
}
