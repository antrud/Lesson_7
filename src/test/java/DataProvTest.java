
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvTest {
    WebDriver driver;

    @DataProvider(name = "dp")
    public Object[][] parseData() {
        return new Object[][] {
                {"https://www.bing.com/"},
                {"https://duckduckgo.com/?"}
        };
    }

    @BeforeTest
    public void setUp() {
        driver = WebDriverManager.chromedriver().create();;
    }

    @Test(dataProvider = "dp")
    public void test(String searchUrl) throws InterruptedException {
        driver.get(searchUrl);

        try {
            WebElement btnContinue = driver.findElement(By.xpath("//*[@class='WrNOuc']"));
            while (btnContinue.isDisplayed()) {
                btnContinue.click();
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            System.out.println("element not found");
        }

        if (searchUrl.contains("google")) {
            try {
                WebElement btnAccept = driver.findElement(By.xpath("//*[@id='L2AGLb']"));
                Thread.sleep(2_000);
                btnAccept.click();
            } catch (Exception ex) {
                System.out.println("element not found");
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, searchUrl, "Wrong URL displayed");

        WebElement etSearchField = driver.findElement(By.xpath(".//*[@name='q']"));
        etSearchField.sendKeys("Selenium");
        etSearchField.submit();
        Thread.sleep(2_000);
        System.out.println("Page title is " + driver.getTitle());
        Thread.sleep(2_000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
