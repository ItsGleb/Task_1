import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    Burger burgerTest;
    @Mock
    Ingredient ingredientMock;
    @Mock
    Bun bunMock;
    @Mock
    IngredientType typeMock;

    @BeforeEach
    public void setup() {
        burgerTest = new Burger();
    }

    @Test
    public void setBunTest() {
        burgerTest.setBuns(bunMock);
        // Проверяем что поле bun не пустое
        assertNotNull(burgerTest.bun, "Поле bun класса burger пустое");
    }

    @Test
    public void addIngredientTest() {
        burgerTest.ingredients.add(ingredientMock);
        int actualResult = burgerTest.ingredients.size();
        // Проверяем что метод addIngredient добавляет объект класса Ingredient в список
        assertEquals(1, actualResult, "Метод addIngredient должен увеличить размер списка ingredients на 1");
    }

    @Test
    public void removeIngredientTest() {
        Ingredient firstIngredient = new Ingredient(typeMock, "firstIngredient", 100f);
        Ingredient secondIngredient = new Ingredient(typeMock, "secondIngredient", 200f);
        burgerTest.addIngredient(firstIngredient);
        burgerTest.addIngredient(secondIngredient);
        // Воспользуюсь генератором случайных чисел для выбора индекса списка 0 или 1
        Random random = new Random();
        int randomIndex = random.nextInt(2);
        // Удаляем элемент списка
        burgerTest.removeIngredient(randomIndex);
        assertEquals(1, burgerTest.ingredients.size(), "Метод remove должен уменьшить длину списка" +
                "с 2 до 1");
    }

    @Test
    public void moveIngredientTest() {
        Ingredient firstIngredient = new Ingredient(typeMock, "firstIngredient", 100f);
        Ingredient secondIngredient = new Ingredient(typeMock, "secondIngredient", 200f);
        burgerTest.addIngredient(firstIngredient);
        burgerTest.addIngredient(secondIngredient);
        burgerTest.moveIngredient(1, 0);
        assertEquals(secondIngredient.getName(), burgerTest.ingredients.get(0).getName(), "Метод" +
                "moveIngredient не поменял местами firstIngredient и secondIngredient");
    }
}
