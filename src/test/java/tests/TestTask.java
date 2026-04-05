package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pages.FormFieldsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Form Tests")
@Feature("Form Submission")
public class TestTask extends BaseTest {
    @Test
    @Story("Positive submission")
    @Description("TC-01: User fills all fields and submits form successfully")
    public void positive() {
        FormFieldsPage formFieldsPage = new FormFieldsPage(driver);
        formFieldsPage.enterUsername("Alex")
                .enterPassword("secret")
                .selectMilk()
                .selectCoffee()
                .selectYellow()
                .selectAutomationTools("Yes")
                .enterEmail("alex@email.ru")
                .enterMessage()
                .clickSubmitBtn();
        String alertText = formFieldsPage.getAlertText();
        assertEquals("Message received!", alertText, "Alert text mismatch!");
    }

    @Test
    @Story("Negative submission")
    @Description("TC-02: User skips username field and tries to submit the form. " +
            "Alert should not appear and validation should prevent submission.")
    public void negative() {
        FormFieldsPage formFieldsPage = new FormFieldsPage(driver);
        formFieldsPage.enterPassword("secret")
                .selectMilk()
                .selectCoffee()
                .selectYellow()
                .selectAutomationTools("Yes")
                .enterEmail("alex@email.ru")
                .enterMessage()
                .clickSubmitBtn();
        assertEquals(formFieldsPage.isAlertPresent(), false,
                "Alert should NOT appear when required field is missing");
    }
}