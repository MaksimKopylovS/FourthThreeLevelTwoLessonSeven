package max_sk.HomeWork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityAccessAllowedTest() throws Exception {
        String jsonRequset = "{\n" +
                "\t\"username\": \"user1\",\n" +
                "\t\"password\": \"777\"\n" +
                "}";

        MvcResult result = mockMvc.perform(post("/auth")
                .content(jsonRequset)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        String token = result.getResponse().getContentAsString();
        token = token.replace("{\"token\":\"", "").replace("\"}", "");

        mockMvc.perform(get("/api/v1/products").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/products/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/basket/inc/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

}
