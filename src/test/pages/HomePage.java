import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {
    By createPostLinkLocator = By.xpath("//button[@aria-label='Create Post']");

    public HomePage(WebDriver driver){
        super(driver);
    }


    public CreatePostPage goToCreatePostPage(){
        waitForClickability(createPostLinkLocator).sendKeys(Keys.ENTER);
        return new CreatePostPage(driver);
    }
}
