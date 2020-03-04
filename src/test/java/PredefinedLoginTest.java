import org.testng.annotations.BeforeMethod;

public class PredefinedLoginTest extends BaseTest {
    protected String loginUrl = "https://www.reddit.com/login";
    protected String userName;
    protected String password;
    protected String userCommunity;

    protected HomePage homePage;

    @BeforeMethod
    public void userLogin(){
        userName = properties.getProperty("username");
        password = properties.getProperty("password");
        userCommunity = properties.getProperty("usercommunity");
        driver.get(loginUrl);
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.login(userName, password);
    }
}
