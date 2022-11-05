package my.warehouse.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testReturn200() throws Exception {
        String json = TestUntil.getJSONMoving("Холодильник");
        mvc.perform(post("/api/admission")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUntil.getJSONAdmission("Склад 1")))
                .andExpect(status().isOk());

        mvc.perform(put("/api/moving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void testReturn404() throws Exception {
        String json = TestUntil.getJSONMoving("Утюг");
        mvc.perform(put("/api/moving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testReturn400() throws Exception {
        String json = TestUntil.getJSONMoving("");
        mvc.perform(put("/api/moving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }
}
