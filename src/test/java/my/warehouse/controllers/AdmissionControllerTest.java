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
public class AdmissionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testReturn200() throws Exception {
        String json = getJSON("Склад 1");

        mvc.perform(
                        put("/api/admission")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());

    }

    @Test
    public void testReturn404() throws Exception {
        String json = getJSON("Склад 100");

        mvc.perform(
                        put("/api/admission")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void testReturn400() throws Exception {
        String json = getJSON("");
        mvc.perform(
                        put("/api/admission")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().is4xxClientError());

    }

    private String getJSON(String warehouse){
        return "\t{\n" +
                "  \"number\": \"1\",\n" +
                "  \"warehouse\": {\n" +
                "    \"name\": \"" + warehouse + "\"\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "  {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
