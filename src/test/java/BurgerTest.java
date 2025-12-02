import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;
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
    Database databaseMock;
    @Spy
    Ingredient ingredientSpy = new Ingredient(IngredientType.FILLING, "sausage", 300);
    @Spy
    Bun bunSpy = new Bun("black bun", 100);


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
        Ingredient firstIngredient = new Ingredient(IngredientType.SAUCE, "firstIngredient", 100f);
        Ingredient secondIngredient = new Ingredient(IngredientType.FILLING, "secondIngredient", 200f);
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
        Ingredient firstIngredient = new Ingredient(IngredientType.SAUCE, "firstIngredient", 100f);
        Ingredient secondIngredient = new Ingredient(IngredientType.FILLING, "secondIngredient", 200f);
        burgerTest.addIngredient(firstIngredient);
        burgerTest.addIngredient(secondIngredient);
        // Меняем местами second с first
        burgerTest.moveIngredient(1, 0);
        assertEquals(secondIngredient.getName(), burgerTest.ingredients.get(0).getName(), "Метод" +
                "moveIngredient не поменял местами firstIngredient и secondIngredient");
    }

    @Test
    public void getPriceTest() {
        /* Допустим у нас БД реальна и не хочется тратить время на коннект к ней
         *  Тогда сделаем стаб для нее, чтобы проверить метод
         * */
        List<Bun> bunMockList = List.of(new Bun("black bun", 100));
        List<Ingredient> ingredientMockList = List.of(new Ingredient(IngredientType.FILLING, "sausage", 300));
        // Возвращаем созданные списки
        Mockito.when(databaseMock.availableBuns()).thenReturn(bunMockList);
        Mockito.when(databaseMock.availableIngredients()).thenReturn(ingredientMockList);
        // Получаем переменные из заглушек
        List<Ingredient> ingredientsListTest = databaseMock.availableIngredients();
        List<Bun> bunListTest = databaseMock.availableBuns();
        // Создаем объект бургера
        burgerTest.setBuns(bunListTest.get(0));
        burgerTest.addIngredient(ingredientsListTest.get(0));
        // Создаем ожидаемое значение = 100 x 2 + 300 = 500. Две булочки и одна начинка
        float expectedValue = 500f;
        // Получаем фактическое значение
        float actualValue = burgerTest.getPrice();
        assertEquals(expectedValue, actualValue, "Метод burger.getPrice() рассчитывает неправильную стоимость");
    }

    @Test
    public void getReceiptTest() {
        // Готовим списки для стаба БД
        List<Bun> bunMockList = List.of(bunSpy);
        List<Ingredient> ingredientMockList = List.of(ingredientSpy);
        // Возвращаем созданные списки
        Mockito.when(databaseMock.availableBuns()).thenReturn(bunMockList);
        Mockito.when(databaseMock.availableIngredients()).thenReturn(ingredientMockList);
        // Получаем переменные из заглушек
        List<Ingredient> ingredientsListTest = databaseMock.availableIngredients();
        List<Bun> bunListTest = databaseMock.availableBuns();
        // Создаем объект бургера
        burgerTest.setBuns(bunListTest.get(0));
        burgerTest.addIngredient(ingredientsListTest.get(0));
        // Получаем рецепт бургера
        String actualReceipt = burgerTest.getReceipt();
        // Проверяем сколько раз был вызван getName у объектов Bun и Ingredients
        Mockito.verify(bunSpy, Mockito.times(2)).getName();
        Mockito.verify(ingredientSpy, Mockito.times(ingredientsListTest.size())).getName();

    }
}
