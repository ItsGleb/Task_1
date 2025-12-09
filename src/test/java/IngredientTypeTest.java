import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {


    @ParameterizedTest
    @CsvSource(value = {"SAUCE", "FILLING"})
    public void valueOfTest(String expectedValue) {

        if (expectedValue.equals("SAUCE")) {
            assertEquals(IngredientType.valueOf(expectedValue), IngredientType.SAUCE, "В enum" +
                    "отсутствует параметр SAUCE");
        } else if (expectedValue.equals("FILLING")) {
            assertEquals(IngredientType.valueOf(expectedValue), IngredientType.FILLING, "В enum" +
                    "отсутствует параметр FILLING");
        }


    }
}
