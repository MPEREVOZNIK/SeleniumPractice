import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

@BeforeAll
public static void classSetup(){
    driver = SharedDriver.getWebDriver();
    driver.get(HOME_PAGE_URL);
}

@AfterAll
public static void classTearDown(){
    SharedDriver.closeDriver();
}

@AfterEach
public void testTearDown() {
    driver.get(HOME_PAGE_URL);
}

@Test
public void homePageUrlTest(){
    String actualURL = driver.getCurrentUrl();
    assertEquals(HOME_PAGE_URL,actualURL,"URLs do not match");

}
@Test
    public void nameTextBoxTest(){
    String name = "Maria";
    String value = driver.findElement(By.id("u_0_m")).getAttribute("value");
    assertEquals("", value);
    driver.findElement(By.id("u_0_m")).sendKeys(name);
    value = driver.findElement(By.id("u_0_m")).getAttribute("value");
    assertEquals(name, value);

}
@Test
//check that the home page is displayed when clicking Sign Up with empty fields.
    public void signUpTest() {
    driver.findElement(By.xpath("//button[text() = 'Sign Up']")).click();
    String actualURL = driver.getCurrentUrl();
    assertEquals(HOME_PAGE_URL, actualURL, "Home page should be displayed");
}
    @Test
    public void FemaleRadioButtonTest() {
        driver.findElement(By.xpath("//label[text() = 'Female']")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }
}

