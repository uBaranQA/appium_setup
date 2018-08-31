package pl.ubaranqa.appium_setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CalculatorMainScreen {

    protected final AppiumDriver driver;

    public CalculatorMainScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    private MobileElement resultField;

    @AndroidFindBy(id = "com.dalviksoft.calculator:id/button2")
    private MobileElement buttonTwo;

    @AndroidFindBy(id = "com.dalviksoft.calculator:id/buttonPlus")
    private MobileElement buttonPlus;

    @AndroidFindBy(id = "com.dalviksoft.calculator:id/buttonEqual")
    private MobileElement buttonEquals;

    public void clickTwo() {
        buttonTwo.click();
    }

    public void clickPlus() {
        buttonPlus.click();
    }

    public void clickEquals() {
        buttonEquals.click();
    }

    public boolean checkResult(String expectedResult) {
        return (new WebDriverWait(driver, 30))
                .until(ExpectedConditions
                        .textToBePresentInElement(resultField, expectedResult));
    }
}
