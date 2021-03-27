package max_sk.HomeWork;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpyTest {
    @Spy
    private List<Integer> testList = new ArrayList<>();

    @Test
    public void spyTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        Mockito.verify(testList).add(1);
        Mockito.verify(testList).add(2);
        Mockito.verify(testList).add(3);
        Mockito.verify(testList).add(4);

        assertEquals(4, testList.size());

        Mockito.doReturn(100).when(testList).size();

        assertEquals(100, testList.size());

        System.out.println(testList.getClass().getName());
    }
}
