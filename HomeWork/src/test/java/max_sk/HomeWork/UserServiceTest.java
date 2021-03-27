package max_sk.HomeWork;


import lombok.RequiredArgsConstructor;
import max_sk.HomeWork.dto.UserDTO;
import max_sk.HomeWork.repository.UserRepository;
import max_sk.HomeWork.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
@RequiredArgsConstructor
public class UserServiceTest {


    @MockBean
    private UserService userService;

    @Test
    @Transactional
    public void registrationUserTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("Pety");
        userDTO.setRegPassword("777");
        userDTO.setMail("pety@ya.ru");
        userService.registrationUser(userDTO);
    }
}
