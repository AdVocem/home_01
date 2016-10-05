package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;



public class mailbox_login {
    private WebDriver driver;
    private String baseUrl;

    @Parameters({ "login", "password", "loginNameID", "passNameID", "submitClassID", "loggedID" })
    @Test
    public void mailbox_login(String login,
                              String password,
                              String loginNameID,
                              String passNameID,
                              String submitClassID,
                              String loggedID) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name(loginNameID)).clear();
        driver.findElement(By.name(loginNameID)).sendKeys(login);
        driver.findElement(By.name(passNameID)).clear();
        driver.findElement(By.name(passNameID)).sendKeys(password);
        driver.findElement(By.className(submitClassID)).click();

        List<WebElement> elements = driver.findElements(By.xpath(loggedID));
        Assert.assertTrue(elements.size() == 1 && elements.get(0).getText().contains(login));
    }

    @Parameters({ "baseURL" })
    @BeforeClass(alwaysRun = true)
    public void setUp(String baseURL) throws Exception {
        driver = new ChromeDriver();
        baseUrl = baseURL;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}
