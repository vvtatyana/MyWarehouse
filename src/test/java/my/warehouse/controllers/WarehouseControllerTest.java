package my.warehouse.controllers;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WarehouseControllerTest {
    @Autowired
    private MockMvc mvc;

    private static long id;
    @Test
    public void test1Post() throws Exception {
        String JSON = "{\n" +
                "  \"name\": \"Склад 5\"\n" +
                "}";

        String body = mvc.perform(post("/api/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        int start = body.indexOf("id") + 4;
        int end = body.indexOf("name") - 2;
        id = Long.parseLong(body.substring(start, end));
    }

    @Test
    public void test2Put() throws Exception {
        String JSON = "{\n" +
                "  \"name\": \"Склад 6\"\n" +
                "}";

        mvc.perform(put("/api/warehouse/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void test3Get() throws Exception {
        mvc.perform(get("/api/warehouse/" + id)).andExpect(status().isOk());
    }

    @Test
    public void test4Delete() throws Exception {
        mvc.perform(delete("/api/warehouse/" + id)).andExpect(status().isOk());
    }
}
