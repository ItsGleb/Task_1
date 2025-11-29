import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
public class IngredientTest {

    Ingredient ingredientTest;
    // Мокаю для изоляции тестов от объекта IngredientType. Если вдруг измениться ENUM, то не придется исправлять тесты
    @Mock
    IngredientType ingredientTypeMock;

    @Test
    public void ingredientGetPriceTest() {
        float expectedResult = 50f;
        ingredientTest = new Ingredient(ingredientTypeMock, "Tomato", expectedResult);
        float actualResult = ingredientTest.getPrice();
        // Проверям что возвращается значение переданное в конструкторе
        assertEquals(expectedResult, actualResult, "Возвращаемое значение price должно совпадать с переданным");
    }

    @Test
    public void ingredientGetNameTest() {
        String expectedResult = "Tomato";
        ingredientTest = new Ingredient(ingredientTypeMock, expectedResult, 50f);
        String actualResult = ingredientTest.getName();
        // Проверям что возвращается значение переданное в конструкторе
        assertEquals(expectedResult, actualResult, "Возвращаемое значение name должно совпадать с переданным");
    }

    @Test
    public void ingredientGetTypeTest() {
        ingredientTest = new Ingredient(ingredientTypeMock, "Tomato", 50f);
        IngredientType actualType = ingredientTest.getType();
        // Проверяем что метод getType() вернет объект того же типа что был передан в конструкторе
        assertSame(ingredientTypeMock, actualType, "Возвращаемый объект методом getType() " +
                "должен совпадать с переданным в конструкторе");
    }
}
