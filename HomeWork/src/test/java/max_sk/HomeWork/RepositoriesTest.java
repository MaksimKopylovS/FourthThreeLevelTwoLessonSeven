package max_sk.HomeWork;

import max_sk.HomeWork.model.Product;
import max_sk.HomeWork.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDBTest(){
        Product productTest = new Product();
        productTest.setTitle("Картошка");
        productTest.setCost(10);
        productTest.setId(1L);

        List<Product> genresList = productRepository.findAll();
        Assertions.assertEquals(77, genresList.size());
        Assertions.assertEquals("Картошка", productRepository.findById(1L).get().getTitle());
        Assertions.assertEquals(productTest.getCost(), productRepository.getOne(1L).getCost());
    }

}
