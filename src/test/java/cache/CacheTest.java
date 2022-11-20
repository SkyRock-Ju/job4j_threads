package cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {

    @Test
    void shouldAddModel() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 1)));
        assertTrue(cache.add(new Base(2, 2)));
        assertTrue(cache.add(new Base(3, 3)));
        assertTrue(cache.add(new Base(4, 4)));
    }

    @Test
    void shouldUpdateCreatedModels() {
        Cache cache = new Cache();
        Base firstModel = new Base(1, 1);
        firstModel.setName("first");
        Base secondModel = new Base(2, 2);
        secondModel.setName("second");
        Base thirdModel = new Base(3, 3);
        thirdModel.setName("third");
        Base fourthModel = new Base(4, 4);
        fourthModel.setName("fourth");

        cache.add(firstModel);
        cache.add(secondModel);
        cache.add(thirdModel);
        cache.add(fourthModel);

        Base firstModelUpdate = new Base(1, 1);
        firstModelUpdate.setName("firstUpdate");
        Base secondModelUpdate = new Base(2, 2);
        secondModelUpdate.setName("secondUpdate");
        Base thirdModelUpdate = new Base(3, 3);
        thirdModelUpdate.setName("thirdUpdate");
        Base fourthModelUpdate = new Base(4, 4);
        fourthModelUpdate.setName("fourthUpdate");

        assertTrue(cache.update(firstModelUpdate));
        assertTrue(cache.update(secondModelUpdate));
        assertTrue(cache.update(thirdModelUpdate));
        assertTrue(cache.update(fourthModelUpdate));
    }

    @Test
    void shouldThrowException() {
        Cache cache = new Cache();
        Base firstModel = new Base(1, 1);
        firstModel.setName("first");
        Base secondModel = new Base(1, 2);
        secondModel.setName("second");

        cache.add(firstModel);

        assertThrows(OptimisticException.class, () -> cache.update(secondModel));
    }
}
