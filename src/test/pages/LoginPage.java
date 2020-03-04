import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By userNameFieldLocator = By.id("loginUsername");
    private By passwordFieldLocator = By.id("loginPassword");
    private By submitButtonLocator = By.cssSelector("button[type = 'submit']");

    private String checkoutStringFormat = "//span[text()='%s']";


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public HomePage login(String userName, String password) {
        findElementByLocator(userNameFieldLocator).sendKeys(userName);
        findElementByLocator(passwordFieldLocator).sendKeys(password);
        findElementByLocator(submitButtonLocator).click();

        waitForVisibility(By.xpath(String.format(checkoutStringFormat, userName)));

        return new HomePage(driver);
    }

}