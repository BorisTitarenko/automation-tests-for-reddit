import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CreatePostPage extends BasePage {

    private By communitySelectLocator = By.xpath("//input[@placeholder='Choose a community']");
    private By titleFieldLocator = By.xpath("//textarea[@placeholder='Title']");
    private By bodyTextBoxLocator = By.xpath("//div[@role='textbox']");
    private By submitButtonLocator = By.xpath("//button[text()='Post' and not(*)]");
    private By addImageButtonLocator = By.xpath("//div[@aria-label='Add an image']/input");

    public CreatePostPage(WebDriver driver){
        super(driver);
    }


    private void fillTextPost(String community, String title, String body){
        findElementByLocator(communitySelectLocator).sendKeys(community);
        findElementByLocator(titleFieldLocator).sendKeys(title);
        findElementByLocator(bodyTextBoxLocator).sendKeys(body);
    }


    private void uploadImage(String imagePath){
        driver.findElement(addImageButtonLocator).sendKeys(imagePath);
    }


    private void subbmitPost(){
        waitForClickability(submitButtonLocator).click();
    }


    public PostPanel createTextPost(String community, String title, String body){
        fillTextPost(community, title, body);
        subbmitPost();
        return new PostPanel(driver);
    }


    public PostPanel createTextAndImagePost(String community, String title, String body, String imagePath){
        fillTextPost(community, title, body);
        uploadImage(imagePath);
        subbmitPost();
        return new PostPanel(driver);
    }



}
