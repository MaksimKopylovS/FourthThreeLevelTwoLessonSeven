package max_sk.HomeWork;

import max_sk.HomeWork.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAuthTokenTest() throws Exception {

        String jsonRequset = "{\n" +
                "\t\"username\": \"user1\",\n" +
                "\t\"password\": \"777\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(post("/auth")
                .content(jsonRequset)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void registrationUserTest() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("pety");
        userDTO.setRegPassword("777");
        userDTO.setMail("pety@zzz.rrr");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mockMvc.perform(post("/reg")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andDo(print());

        String jsonRequset = "{\n" +
                "\t\"username\": \"pety\",\n" +
                "\t\"password\": \"777\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(post("/auth")
                .content(jsonRequset)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


}
