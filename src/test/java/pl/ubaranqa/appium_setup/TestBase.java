package pl.ubaranqa.appium_setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    private AppiumDriver driver;

    /* Sets the test name to the name of the test method. */
    @Rule
    public TestName testName = new TestName();

    private static final String APPIUM_REMOTE_URL = "https://eu1.appium.testobject.com/wd/hub";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String API_KEY = "3B677A8960FD427E824BCE378F643677";

    private final static String EXPECTED_RESULT_FOUR = "4";

    @Before
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", API_KEY);

        driver = new AndroidDriver(new URL(APPIUM_REMOTE_URL), capabilities);
    }

    /* A simple addition, it expects the correct result to appear in the result field. */
    @Test
    public void twoPlusTwoOperation() {

		/* Get the elements. */
        MobileElement buttonTwo = (MobileElement)(driver.findElement(By.id("com.dalviksoft.calculator:id/button2")));
        MobileElement buttonPlus = (MobileElement)(driver.findElement(By.id("com.dalviksoft.calculator:id/buttonPlus")));
        MobileElement buttonEquals = (MobileElement)(driver.findElement(By.id("com.dalviksoft.calculator:id/buttonEqual")));
        MobileElement resultField = (MobileElement)(driver.findElement(By.xpath("//android.widget.EditText[1]")));

		/* Add two and two. */
        buttonTwo.click();
        buttonPlus.click();
        buttonTwo.click();
        buttonEquals.click();

		/* Check if within given time the correct result appears in the designated field. */
//        (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, EXPECTED_RESULT_FOUR));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
