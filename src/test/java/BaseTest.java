import okio.Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;

    @BeforeTest
    public void setupDriver() {
        properties = new Properties();
        try(InputStream inputStream = new FileInputStream("E:\\course_java\\studying\\4\\redditTestModification\\config.properties")) {
            properties.load(inputStream);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        System.setProperty("webdriver.gecko.driver", "E:\\java\\geckodriver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\java\\chromedriver\\chromedriver.exe");

    }

    @BeforeMethod
    public void setup() {
        String browser = properties.getProperty("browser");
        if(browser.contains("chrome")){
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        }
        else{
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("permissions.default.desktop-notification", 1);
            DesiredCapabilities capabilities=DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            driver = new FirefoxDriver(capabilities);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
