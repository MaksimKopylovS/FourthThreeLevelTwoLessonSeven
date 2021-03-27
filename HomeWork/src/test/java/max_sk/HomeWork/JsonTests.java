package max_sk.HomeWork;

import max_sk.HomeWork.model.Product;
import max_sk.HomeWork.model.Role;
import max_sk.HomeWork.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;




@org.springframework.boot.test.autoconfigure.json.JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jacksonTester;

    @Autowired
    private JacksonTester<Product> jacksonProduct;

    @Autowired
    private JacksonTester<User> jacksonUser;

    @Test
    public void jsonSerializationTest() throws Exception{
        Role role = new Role();
        role.setId(1L);
        role.setName("user");
        assertThat(jacksonTester.write(role))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.name").isEqualTo("user");
    }

    @Test
    void jsonMyTest() throws Exception{
        Product product = new Product();
        product.setId(15L);
        product.setTitle("Pelmeni");
        product.setCost(100500);

        assertThat(jacksonProduct.write(product))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathNumberValue("$.cost").isEqualTo(100500);

        assertThat(jacksonProduct.write(product))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("Pelmeni");
    }

    @Test
    void jsonTestUserJson() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("Pety");
        user.setPassword("111");
        user.setEmail("pyty@best.qq");

        assertThat(jacksonUser.write(user))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.username").isEqualTo("Pety");
        assertThat(jacksonUser.write(user))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.password").isEqualTo("111");
        assertThat(jacksonUser.write(user))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.email").isEqualTo("pyty@best.qq");

        Assertions.assertEquals("111", user.getPassword());
        Assertions.assertEquals("Pety", user.getUsername());
        Assertions.assertEquals("pyty@best.qq", user.getEmail());
    }
}
