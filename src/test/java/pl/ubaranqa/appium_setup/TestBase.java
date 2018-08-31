package pl.ubaranqa.appium_setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    private AppiumDriver driver;
    private CalculatorMainScreen calculatorMainScreen;

    /* Sets the test name to the name of the test method. */
    @Rule
    public TestName testName = new TestName();

    /* Takes care of sending the result of the tests over to TestObject. */
    @Rule
    public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

    private static final String APPIUM_REMOTE_URL = "https://eu1.appium.testobject.com/wd/hub";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String API_KEY = "3B677A8960FD427E824BCE378F643677";

    private final static String EXPECTED_RESULT_FOUR = "4";

    @Before
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", API_KEY);

        driver = new AndroidDriver(new URL(APPIUM_REMOTE_URL), capabilities);

        calculatorMainScreen = new CalculatorMainScreen(driver);

        System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
        System.out.println(driver.getCapabilities().getCapability("testobject_test_live_view_url"));

        resultWatcher.setRemoteWebDriver(driver);
    }

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

        /* Add two and two. */
        calculatorMainScreen.clickTwo();
        calculatorMainScreen.clickPlus();
        calculatorMainScreen.clickTwo();
        calculatorMainScreen.clickEquals();

		/* Check if within given time the correct result appears in the designated field. */
        Assert.assertTrue(calculatorMainScreen.checkResult(EXPECTED_RESULT_FOUR));

    }

}
