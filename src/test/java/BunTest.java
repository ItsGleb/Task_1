import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    Bun testBun;

    @Test
    public void checkBunGetNameTest(){
        String expectedResult = "Пшеничная";
        testBun = new Bun(expectedResult,100f);
        String actualResult = testBun.getName();
        assertEquals(expectedResult,actualResult,"Возвращаемое значение name не совпадает");
    }

    @Test
    public void checkBunGetPriceTest(){
        float expectedResult = 100f;
        testBun = new Bun("Пшеничная",expectedResult);
        float actualResult = testBun.getPrice();
        assertEquals(expectedResult,actualResult,"Возвращаемое значение price не совпадает");
    }
}
