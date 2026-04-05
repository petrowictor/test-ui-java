package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormFieldsPage extends BasePage {

    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(id = "drink2")
    private WebElement milkCheckbox;

    @FindBy(id = "drink3")
    private WebElement coffeeCheckbox;

    @FindBy(css = "input[id='color3']")
    private WebElement yellowRadio;

    @FindBy(css = "select#automation")
    private WebElement automationToolsSelect;

    @FindBy(id = "email")
    private  WebElement emailField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(xpath = "//label[contains(text(),'Automation tools')]/following-sibling::ul/li")
    private List<WebElement> automationToolsList;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    public FormFieldsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username: {username}")
    public FormFieldsPage enterUsername(String username) {
        waitForVisible(nameField).sendKeys(username);
        return this;
    }
    @Step("Enter password")
    public FormFieldsPage enterPassword(String password) {
        waitForVisible(passwordField).sendKeys(password);
        return this;
    }

    @Step("Select Milk checkbox")
    public FormFieldsPage selectMilk() {
        waitForClickable(milkCheckbox).click();
        return this;
    }

    @Step("Select Coffee checkbox")
    public FormFieldsPage selectCoffee() {
        waitForClickable(coffeeCheckbox).click();
        return this;
    }

    @Step("Select Yellow radio button")
    public FormFieldsPage selectYellow() {
        waitForClickable(yellowRadio).click();
        return this;
    }

    @Step("Select Automation Tool option: {option}")
    public FormFieldsPage selectAutomationTools(String option) {
        WebElement selectElement = waitForVisible(automationToolsSelect);
        Select select = new Select(selectElement);
        select.selectByValue(option);
        return this;
    }

    @Step("Enter email: {email}")
    public FormFieldsPage enterEmail(String email){
        waitForVisible(emailField).sendKeys(email);
        return this;
    }

    @Step("Generate message based on automation tools list")
    public String automationToolsMessage(){
        waitForAllVisible(automationToolsList);
        int toolsCount = automationToolsList.size();
        String longestTool = "";
        for (WebElement tool : automationToolsList) {
            String text = tool.getText().trim();
            if (text.length() > longestTool.length()) {
                longestTool = text;
            }
        }
        return toolsCount + " " + longestTool;
    }

    @Step("Enter generated message into message field")
    public FormFieldsPage enterMessage(){
        String messageText = automationToolsMessage();
        waitForVisible(messageField).sendKeys(messageText);
        return this;
    }

    @Step("Click Submit button")
    public FormFieldsPage clickSubmitBtn() {
        WebElement element = waitForClickable(submitButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.click();
        return this;
    }

    @Step("Get alert text")
    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    @Step("Check if alert is present")
    public boolean isAlertPresent() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
