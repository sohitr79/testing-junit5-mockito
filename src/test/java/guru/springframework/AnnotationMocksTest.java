package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

/**
 * A much cleaner way to implement mock is using JunitExtension then using this AnnotationMocksTest
 */
public class AnnotationMocksTest {
    /**
     * In contrast to InlineMocKTests class in this we are using annotations to mock
     */

    /**
     * @Mock will tell that this is the mock object to be used
     */
    @Mock
    Map<String,Object> mapMock;

    /**
     * initMocks is used to initialize mock object in this case mapMock
     * otherwise we would have seen NPE
     */

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("Name","Naruto");
    }
}
