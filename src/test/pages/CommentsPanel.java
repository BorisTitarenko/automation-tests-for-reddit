import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CommentsPanel extends BasePage {
    private By commentTextBoxLocator = By.xpath("//div[@role='textbox']");
    private By submitCommentButtonLocator = By.xpath("//button[@type='submit' and text()='comment']");
    private By commentsParagraphsLocator = By.xpath("//div[@data-test-id='comment']//p");

    private By relReplyButtonLocator = By.xpath(".//button[text()='Reply' and not(@type='submit')]");
    private By relReplyTextBoxLocator = By.xpath(".//div[@role='textbox']");
    private By relSubmitReplyLocator = By.xpath(".//button[text()='Reply' and @type='submit']");

    private final String commentPathFormat = "//div[./div[@data-test-id='comment']//p[text()=\'%s\']]";

    public CommentsPanel(WebDriver driver) {
        super(driver);
    }


    private By getCommentLocator(String commentText) {
        String commentPath = String.format(commentPathFormat, commentText);
        By commentLocator = By.xpath(commentPath);
        return commentLocator;
    }

    public CommentsPanel createComment(String commentText) {
        findElementByLocator(commentTextBoxLocator).sendKeys(commentText);
        waitForClickability(submitCommentButtonLocator).click();
        return this;
    }


    public CommentsPanel replyComment(String commentText, String replyText) {
        WebElement commentElem = findElementByLocator(getCommentLocator(commentText));

        commentElem.findElement(relReplyButtonLocator).click();
        waitForVisibility(relReplyTextBoxLocator);

        commentElem.findElement(relReplyTextBoxLocator).sendKeys(replyText);
        waitForClickability(relSubmitReplyLocator).click();

        waitForVisibility(getCommentLocator(replyText));

        return this;
    }


    public List<String> getCommentsText() {
        List<String> strings = new ArrayList<>();
        List<WebElement> commentsParagraphs = findElementsByLocator(commentsParagraphsLocator);
        for (WebElement webElement : commentsParagraphs) {
            strings.add(webElement.getText());
        }
        return strings;
    }
}
