import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PostPanel extends BasePage {
    private By postParagraphsLocator = By.xpath("//div[@data-click-id='text']/div/p");
    private By postImageLocator = By.xpath("//a/img[@alt='Post image']");

    public PostPanel(WebDriver driver){
        super(driver);
    }


    public String getPostText(){
        String postText = "";
        List<WebElement> postParagraphs = findElementsByLocator(postParagraphsLocator);
        for(WebElement webElement : postParagraphs){
            postText += webElement.getText() + "\n";
        }
        return postText;
    }


    public boolean isImageDisplayed(){
        return findElementByLocator(postImageLocator).getSize().height > 0;
    }
}
