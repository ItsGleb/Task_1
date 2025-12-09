import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
public class IngredientTest {

    Ingredient ingredientTest;

    @Test
    public void ingredientGetPriceTest() {
        float expectedResult = 50f;
        ingredientTest = new Ingredient(IngredientType.SAUCE, "Tomato", expectedResult);
        float actualResult = ingredientTest.getPrice();
        // Проверям что возвращается значение переданное в конструкторе
        assertEquals(expectedResult, actualResult, "Возвращаемое значение price должно совпадать с переданным");
    }

    @Test
    public void ingredientGetNameTest() {
        String expectedResult = "Tomato";
        ingredientTest = new Ingredient(IngredientType.FILLING, expectedResult, 50f);
        String actualResult = ingredientTest.getName();
        // Проверям что возвращается значение переданное в конструкторе
        assertEquals(expectedResult, actualResult, "Возвращаемое значение name должно совпадать с переданным");
    }

    @ParameterizedTest
    // Передаем все значения из ENUM
    @EnumSource(IngredientType.class)
    public void ingredientGetTypeTest(IngredientType type) {
        ingredientTest = new Ingredient(type, "Tomato", 50f);
        IngredientType actualType = ingredientTest.getType();
        // Фактическое имя типа ингредиента
        String actualTypeName = actualType.name();
        // Ожидаемое имя ингредиента
        String expectedTypeName = type.name();
        // Проверяем что метод getType() вернет объект того же типа что был передан в конструкторе
        assertSame(type, actualType, "Возвращаемый объект методом getType() " +
                "должен совпадать с переданным в конструкторе");
        // Проверяем что возвращается правильное имя ингредиента
        assertEquals(expectedTypeName,actualTypeName,"Неверное имя для типа " + type);
    }
}
