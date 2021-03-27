package max_sk.HomeWork;


import max_sk.HomeWork.dto.ProductDTO;
import max_sk.HomeWork.model.Product;
import max_sk.HomeWork.repository.ProductRepository;
import max_sk.HomeWork.services.BasketProductsService;
import max_sk.HomeWork.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProduct() {
        Product productTest = new Product();
        productTest.setTitle("Krakodil");
        productTest.setCost(100000);
        productTest.setId(15L);

        Mockito
                .doReturn(Optional.of(productTest))
                .when(productRepository)
                .findById(10L);

        ProductDTO p = productService.findProductById(10L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
        Assertions.assertEquals("Krakodil", p.getTitle());

        Assertions.assertEquals("Krakodil", productTest.getTitle());
        Assertions.assertEquals(15L, productTest.getId());
        Assertions.assertEquals(100000, productTest.getCost());
    }

}
