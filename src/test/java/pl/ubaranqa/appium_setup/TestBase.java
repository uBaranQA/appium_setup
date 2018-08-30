package pl.ubaranqa.appium_setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static pl.ubaranqa.appium_setup.PropertiesReader.loadProperties;

public class TestBase {

    private AppiumServiceBuilder builder;
    private AppiumDriverLocalService service;

    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private AppiumDriver driver;

    private final static String EXPECTED_RESULT_FOUR = "4";

/*    @BeforeClass
    public static void initialSetUp() {

    }*/

    @Before
    public void setUp() throws MalformedURLException {
        startAppiumServer();

        Properties props = loadProperties("desiredCapabilities.properties");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", props.getProperty("platformName"));
        capabilities.setCapability("deviceName", props.getProperty("deviceName"));
        capabilities.setCapability("avd", props.getProperty("avd"));
        capabilities.setCapability("app", props.getProperty("app"));
        capabilities.setCapability("appPackage", props.getProperty("appPackage"));
        capabilities.setCapability("appActivity", "com.dalviksoft.calculator.Main"); // props.getProperty("appActivity"));


        driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
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

    public void startAppiumServer() {

        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }
}
