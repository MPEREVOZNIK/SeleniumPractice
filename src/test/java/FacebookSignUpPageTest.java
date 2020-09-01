import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacebookSignUpPageTest {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;
    private static final String DAVIK_HOMEPAGE_URL = "https://www.daviktapes.com/";
    //    private static final WebElement YEAR_LIST = driver.findElement(By.xpath("//*[@id=\\\"year\\\"]"));
//    private static final WebElement OPTION_YEAR_START = driver.findElement(By.xpath("//*[@id=\"year\"]/option[117]"));
//    private static final WebElement OPTION_END = driver.findElement(By.xpath("//*[@id=\\\"year\\\"]/option[2]"));
//    private static final WebElement SIGN_UP_BUTTON = driver.findElement(By.xpath("//*[@id=\"u_0_17\"]"));
    private static final String YEAR_LIST = "//*[@name='birthday_year']";
    private static final String SIGN_UP_BUTTON = "//*[text()='Sign Up']";
    private static final String YEAR = ("//*[text()='%s']");
    private static final String MONTH_LIST= "//*[@name='birthday_month']";
    private static final String MONTH = ("//*[text()='%s']");

    String name = "Maria";
    String emptyName = " ";
    String lastName = "Perev";
    String emptyLastName = " ";
    String email = "m.perevoznik3@gmail.com";
    String emptyEmail = " ";
    String reenterEmail = "m.perevoznik3@gmail.com";
    String emptyReenterEmail = " ";
    String password = "QWERTYUIIOi123!";
    String emptyPassword = " ";


    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void homePageUrlTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs do not match");
    }

    @Test
    public void nameTextBoxTest() {
        String name = "Maria";
        String value = driver.findElement(By.id("u_0_m")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        value = driver.findElement(By.id("u_0_m")).getAttribute("value");
        assertEquals(name, value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"@#$#%", "ToooooooooooooooooooooLongnameeeeeeeeeeeeeee", "1", " "})
    public void NameEdgeTest(String name) {
        String value = driver.findElement(By.id("u_0_m")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        value = driver.findElement(By.id("u_0_m")).getAttribute("value");
        assertEquals(name, value);
        driver.findElement(By.xpath("//*[@id=\"u_0_12\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void lastNameTextBoxTest() {
        String lastName = "Perev";
        String value = driver.findElement(By.id("u_0_o")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        value = driver.findElement(By.id("u_0_o")).getAttribute("value");
        assertEquals(lastName, value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"@#$#%", "ToooooooooooooooooooooLonglastnameeeeeeeeeeeeeee", "123", "p", " "})
    public void lastNameEdgeTest(String lastname) {
        String value = driver.findElement(By.id("u_0_o")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_o")).sendKeys(lastname);
        value = driver.findElement(By.id("u_0_o")).getAttribute("value");
        assertEquals(lastname, value);
        driver.findElement(By.xpath("//*[@id=\"u_0_12\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void emailTextBoxTest() {
        String email = "m.perev@gmail.com";
        String value = driver.findElement(By.id("u_0_r")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        value = driver.findElement(By.id("u_0_r")).getAttribute("value");
        assertEquals(email, value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"@#$#%", "m.perev@", "123", "m.perevoznik@gmail.", " "})
    public void invalidEmailTest(String email) {
        String value = driver.findElement(By.id("u_0_r")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        value = driver.findElement(By.id("u_0_r")).getAttribute("value");
        assertEquals(email, value);
        driver.findElement(By.xpath("//*[@id=\"u_0_12\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @ParameterizedTest
    @ValueSource(strings = {"@#$#%", "m.perev@", "123", " "})
    public void reenterInvalidEmailTest(String email) {
        String validEmail = "m.perevoznik@gmail.com";
        driver.findElement(By.id("u_0_r")).sendKeys(validEmail);
        String value = driver.findElement(By.id("u_0_u")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_u")).sendKeys(email);
        value = driver.findElement(By.id("u_0_u")).getAttribute("value");
        assertEquals(email, value);
        driver.findElement(By.xpath("//*[@id=\"u_0_12\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void newPasswordTextBoxTest() {
        String password = "Password!";
        String value = driver.findElement(By.id("password_step_input")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("password_step_input")).sendKeys(password);
        value = driver.findElement(By.id("password_step_input")).getAttribute("value");
        assertEquals(password, value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "pppppppppppppppppppppppppppppp", "abc", " "})
    public void invalidPasswordTest(String password) {
        String value = driver.findElement(By.id("password_step_input")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("password_step_input")).sendKeys(password);
        value = driver.findElement(By.id("password_step_input")).getAttribute("value");
        assertEquals(password, value);
        driver.findElement(By.xpath("//*[@id=\"u_0_12\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void birthdayMonthButtonTest() {
        driver.findElement(By.id("month")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);

        driver.findElement(By.xpath("//*[@id=\"month\"]/option[9]")).click();
        String value = driver.findElement(By.xpath("//*[@id=\"month\"]/option[9]")).getAttribute("value");
        String month = "8";
        assertEquals(month, value);
    }

    @Test
    public void birthdayDateButtonTest() {
        driver.findElement(By.id("day")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);

        driver.findElement(By.xpath("//*[@id=\"day\"]/option[5]")).click();
        String value = driver.findElement(By.xpath("//*[@id=\"day\"]/option[5]")).getAttribute("value");
        String day = "4";
        assertEquals(day, value);
    }

    @Test
    public void birthdayYearButtonTest() {
        driver.findElement(By.id("year")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);

        driver.findElement(By.xpath("//*[@id=\"year\"]/option[32]")).click();
        String value = driver.findElement(By.xpath("//*[@id=\"year\"]/option[32]")).getAttribute("value");
        String year = "1990";
        assertEquals(year, value);
    }

    @Test
    public void birthdayInfoButtonTest() {
        driver.findElement(By.xpath("//*[@id=\"birthday-help\"]")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"u_0_8\"]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void femaleRadioButtonTest() {
        driver.findElement(By.xpath("//label[text() = 'Female']")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void maleRadioButtonTest() {
        driver.findElement(By.xpath("//label[text() = 'Male']")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void customRadioButtonTest() {
        driver.findElement(By.xpath("//label[text() = 'Custom']")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
    }

    @Test
    public void customPronounButtonTest() {
        driver.findElement(By.xpath("//label[text() = 'Custom']")).click();
        driver.findElement(By.xpath("//*[@id=\"u_0_z\"]/select")).click();
        driver.findElement(By.xpath("//*[@id=\"u_0_z\"]/select/option[4]")).click();
        String pronoun = "6";
        String value = driver.findElement(By.xpath("//*[@id=\"u_0_z\"]/select/option[4]")).getAttribute("value");
        assertEquals(pronoun, value);
    }

    @Test
    public void customGenderTextBoxTest() {
        driver.findElement(By.xpath("//label[text() = 'Custom']")).click();
        String gender = "Alien";
        String value = driver.findElement(By.id("u_0_10")).getAttribute("value");
        assertEquals("", value);
        driver.findElement(By.id("u_0_10")).sendKeys(gender);
        value = driver.findElement(By.id("u_0_10")).getAttribute("value");
        assertEquals(gender, value);
    }

    @Test
    public void customInfoButtonTest() {
        driver.findElement(By.id("gender-help")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL);
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"globalContainer\"]/div[3]/div")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void signUpTest() {
        driver.findElement(By.xpath("//button[text() = 'Sign Up']")).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "Home page should be displayed");
    }

    @Test
    public void createPageTest() {
        String expectedURL = "https://www.facebook.com/pages/create/?ref_type=registration_form";
//        driver.findElement(By.linkText("Create a Page")).click();
        driver.findElement(By.partialLinkText("Create")).click();
        assertEquals(expectedURL, driver.getCurrentUrl());

        WebElement bannerSubtitle = driver.findElement(By.xpath("//div[@data-testid = 'pageCreationHeaderBannerSubtitle']"));
        assertNotNull(bannerSubtitle);
    }

    @Test
    public void chooseMonthTest() {
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
        driver.findElement(By.xpath("//option[text()='Oct']")).click();
        assertEquals("10", driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value"));
    }

    @Test
    public void actionsTest() {
        driver.get("https://www.daviktapes.com/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
//        waits 5 seconds and then looks for element "Company"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Company']")));
//        waits 5 seconds to check that the element is not there
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='Company']")));
//        pause();
        WebElement element = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void pause() {
        try {
            Thread.sleep(10000);
        } catch (Exception error) {
            System.out.println("Smth went wrong");
        }
    }


    //    @BeforeAll
//    public static void classSetup() {
//        driver = SharedDriver.getWebDriver();
//        driver.get(DAVIK_HOMEPAGE_URL);
    WebDriverWait wait = new WebDriverWait(driver, 4);
//    }
//
//    @AfterAll
//    public static void classTearDown() {
//        SharedDriver.closeDriver();
//    }
//
//    @AfterEach
//    public void testTearDown() {
//        driver.get(DAVIK_HOMEPAGE_URL);
//    }

    @Test
    public void companyDropListTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Company']")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-2997\"]/ul")));
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"menu-item-2997\"]/ul")).isDisplayed();
        assertTrue(actualResult);

    }

    @Test
    public void productsDropListTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Products']")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Products']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-134\"]/ul")));
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"menu-item-134\"]/ul")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void industriesDropListTest() {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Industries']")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Industries']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-132\"]/ul")));
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"menu-item-132\"]/ul")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void knowledgeCenterDropListTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Knowledge Center']")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Knowledge Center']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-2552\"]/ul")));
        Boolean actualResult = driver.findElement(By.xpath("//*[@id=\"menu-item-2552\"]/ul")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void contactsMenuItemTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Contacts']")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Contacts']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void nameErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(emptyName);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'your name?')]")).isDisplayed();
        assertTrue(actualResult);

    }

    @Test
    public void lastNameErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(emptyLastName);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'your name?')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void emailErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        driver.findElement(By.id("u_0_r")).sendKeys(emptyEmail);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'reset your password')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void reenterEmailErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        driver.findElement(By.id("u_0_u")).sendKeys(emptyReenterEmail);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'re-enter your email')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void passwordErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        driver.findElement(By.id("u_0_u")).sendKeys(reenterEmail);
        driver.findElement(By.id("password_step_input")).sendKeys(emptyPassword);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'Enter a combination')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void birthdayErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        driver.findElement(By.id("u_0_u")).sendKeys(reenterEmail);
        driver.findElement(By.id("password_step_input")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'real birthday')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void genderErrorMessageTest() {
        driver.findElement(By.id("u_0_m")).sendKeys(name);
        driver.findElement(By.id("u_0_o")).sendKeys(lastName);
        driver.findElement(By.id("u_0_r")).sendKeys(email);
        driver.findElement(By.id("u_0_u")).sendKeys(reenterEmail);
        driver.findElement(By.id("password_step_input")).sendKeys(password);
        driver.findElement(By.id("year")).click();
        driver.findElement(By.xpath("//*[@id=\"year\"]/option[32]")).click();
        driver.findElement(By.xpath("//*[@id=\"u_0_13\"]")).click();
        Boolean actualResult = driver.findElement(By.xpath("//*[contains(text(),'choose a gender')]")).isDisplayed();
        assertTrue(actualResult);
    }

    @Test
    public void monthDroplistTest() {
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
        driver.findElement(By.xpath("//option[text()='Jan']")).click();
        String actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("1", actualResult);

        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
        driver.findElement(By.xpath("//option[text()='Feb']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("2", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Mar']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("3", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Apr']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("4", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='May']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("5", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Jun']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("6", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Jul']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("7", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Aug']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("8", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Sep']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("9", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Oct']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("10", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Nov']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("11", actualResult);
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();

        driver.findElement(By.xpath("//option[text()='Dec']")).click();
        actualResult = driver.findElement(By.xpath("//select[@name='birthday_month']")).getAttribute("value");
        assertEquals("12", actualResult);

    }

    @ParameterizedTest
    @ValueSource(ints = {1905, 1950, 2020})
    public void yearListTest(int year) {
        driver.findElement(By.xpath(YEAR_LIST)).click();
        String yearXpath = String.format(YEAR, year);
        driver.findElement(By.xpath(yearXpath)).click();
        driver.findElement(By.xpath(SIGN_UP_BUTTON)).click();
        assertEquals(Integer.toString(year), driver.findElement(By.xpath(YEAR_LIST)).getAttribute("value"), "the year was not selected");
    }
    @ParameterizedTest
    @ValueSource(strings = {"Jan","Feb","Mar","Apr","May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec"})

    public void monthListTest(String month) {
        driver.findElement(By.xpath(MONTH_LIST)).click();
        String monthXpath = String.format(MONTH, month);
        driver.findElement(By.xpath(monthXpath)).click();
        driver.findElement(By.xpath(SIGN_UP_BUTTON)).click();
        Boolean actualResult = driver.findElement(By.xpath(monthXpath)).isSelected();
        assertTrue(actualResult,"the month was not selected");


    }

    @Test
    public void genderRadioButtonsTest() {
        driver.findElement(By.xpath("//input[@type='radio']//following-sibling::label[text()='Female']")).click();
        Boolean actualResult = driver.findElement(By.xpath("//input [@name='sex'] [@value = '1']")).isSelected();
        assertTrue(actualResult);
        driver.findElement(By.xpath("//input[@type='radio']//following-sibling::label[text()='Male']")).click();
        actualResult = driver.findElement(By.xpath("//input [@name='sex'] [@value = '2']")).isSelected();
        assertTrue(actualResult);
        driver.findElement(By.xpath("//input[@type='radio']//following-sibling::label[text()='Custom']")).click();
        actualResult = driver.findElement(By.xpath("//input [@name='sex'] [@value = '-1']")).isSelected();
        assertTrue(actualResult);
    }

    @Test
    public void termsLinkTest() {
        String expectedURL = "https://www.facebook.com/legal/terms/update";
        driver.findElement(By.partialLinkText("Terms")).click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
        String actualResult = driver.getCurrentUrl();
        assertEquals(expectedURL, actualResult);
        WebElement bannerText = driver.findElement(By.xpath("//*[contains(text(),'Terms of Service')]"));
        assertNotNull(bannerText);

    }

    @Test
    public void dataPolicyLinkTest() {
        String expectedURL = "https://www.facebook.com/about/privacy/update";
        driver.findElement(By.partialLinkText("Data Policy")).click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
        String actualResult = driver.getCurrentUrl();
        assertEquals(expectedURL, actualResult);
        WebElement bannerText = driver.findElement(By.xpath("//*[contains(text(),'This policy')]"));
        assertNotNull(bannerText);


    }

    @Test
    public void cookiesPolicyLinkTest() {
        String expectedURL = "https://www.facebook.com/policies/cookies/";
        driver.findElement(By.partialLinkText("Cookies Policy")).click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
        String actualResult = driver.getCurrentUrl();
        assertEquals(expectedURL, actualResult);
        WebElement bannerText = driver.findElement(By.xpath("//*[contains(text(),'Cookies & Other Storage')]"));
        assertNotNull(bannerText);

    }

}