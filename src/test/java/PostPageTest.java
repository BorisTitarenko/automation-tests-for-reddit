import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.testng.Assert.*;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostPageTest extends PredefinedLoginTest{


    @Test(dataProvider = "postWithImageDataProvider", dataProviderClass = PostDataProvider.class)
    void shouldCreatePostWithImage( String title, String body, String image) throws InterruptedException {
        CreatePostPage createPostPage = homePage.goToCreatePostPage();
        PostPanel postPanel = createPostPage.createTextAndImagePost(userCommunity, title, body, image);
        boolean isTextPresented = postPanel.getPostText().contains(body);
        boolean isImagePresented = postPanel.isImageDisplayed();

        assertTrue(isTextPresented && isImagePresented);
    }

    @Test(dataProvider = "commentChainDataProvider", dataProviderClass = PostDataProvider.class)
    void shouldCreateCommentsThread(String postUrl, String[] comments) throws InterruptedException {
        driver.get(postUrl);
        CommentsPanel commentsPanel = new CommentsPanel(driver);

        commentsPanel.createComment(comments[0])
                .replyComment(comments[0], comments[1])
                .replyComment(comments[1], comments[2]);
        List<String> allComments = commentsPanel.getCommentsText();
        int indexOfSubList = Collections.indexOfSubList(allComments, Arrays.asList(comments));

        assertTrue(indexOfSubList > -1);
    }
}
