import org.testng.annotations.DataProvider;

public class PostDataProvider {
    @DataProvider(name = "postWithImageDataProvider")
    public Object[][] getTextAndImage(){
        Object[][] objects = {{ "Title", "Body text $",
                "../resources/cat.jpg"}};
        return objects;
    }

    @DataProvider(name = "commentChainDataProvider")
    public Object[][] getCommentsChain(){
        Object[][] objects = {{"https://www.reddit.com/user/molodyy_vovk/comments/fdb6rk/when_u_go_2_present_ur_project_on_the_demo/",
                new String[]{"Somebody", "once", "told me"}}};
        return objects;
    }
}
