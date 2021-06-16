import org.junit.Before;
import org.junit.Test;
import ru.simbirsoft.task.Main;


public class TestFunctionality {

    @Before
    public void setUp() {
    }

    // Ниже предствалены 3 тестовых примера для ознакомления с программой: как она работает, что пишет в консоль и т.д.

    /**
     * Первый пример
     */
    @Test
    public void testLink1() {
        // сайт SimbirSoft
        String link1 = "https://www.simbirsoft.com/";
        Main.run(link1);
    }

    /**
     * Второй пример
     */
    @Test
    public void testLink2() {
        // Ссылка на страницу в википедии про Китай
        String link2 = "https://ru.wikipedia.org/wiki/%D0%9A%D0%B8%D1%82%D0%B0%D0%B9";
        Main.run(link2);
    }

    /**
     * Третий пример
     */
    @Test
    public void testLink3() {
        // Ссылка на главную страницу TechCrunch
        String link3 = "https://techcrunch.com/";
        Main.run(link3);
    }


}
