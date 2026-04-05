package base;

import driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://practice-automation.com/form-fields/");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("submit-btn")
        ));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
