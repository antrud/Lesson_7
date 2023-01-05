import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.Browser.CHROME;

public class FBTest {

    protected WebDriver chromeDriver = null;
    protected WebDriver firefoxDriver = null;
    public WebDriver getDriver(Drivers browser) throws MalformedURLException {
        switch (browser) {
            case CHROME:
                if (chromeDriver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("se:name", "My test in browser: " + browser);
                    chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
                }
                return chromeDriver;

            case FF:
                if (firefoxDriver == null) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setCapability("se:name", "My test in browser: " + browser);
                    firefoxDriver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
                }
                return firefoxDriver;
            default:
                return null;
        }
    }

    static String firstName = "Anton";
    static String surname = "Ukr";
    static String email = "anton_ukr9875@gmail.com";
    static String password = "password3456!";

    @DataProvider(name = "browsers", parallel = true)
    public Object[][] browserSetUp() {
        return new Object[][] {
                {Drivers.CHROME},
                {Drivers.FF}
        };
    }


    @BeforeTest
    public void setUp() {
//        driver = WebDriverManager.chromedriver().create();;
    }

    @Test(dataProvider = "browsers")
    public void getPage(Drivers browser) throws InterruptedException, MalformedURLException {

        getDriver(browser).get("https://www.facebook.com/reg/");
        Thread.sleep(3000);
        System.out.println();
    }

    @Test(dataProvider = "browsers")
    public void setUserData(Drivers browser) throws InterruptedException, MalformedURLException {

        getDriver(browser).get("https://www.facebook.com/reg/");
        Thread.sleep(3000);

        RegistrationPage registrationPage = new RegistrationPage(getDriver(browser));
        registrationPage.setFirstName(firstName);
        registrationPage.setSurname(surname);
        registrationPage.setEmail(email);
        registrationPage.checkEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setBirthDate("7", "5", "1996");
        registrationPage.chooseGender(Genders.MALE);

        System.out.println("Test completed on platform: " + browser);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"setUserData"}, dataProvider = "browsers")
    public void checkUserData(Drivers browser) throws InterruptedException, MalformedURLException {
        getDriver(browser).get("https://www.facebook.com/reg/");
        Thread.sleep(3000);

        System.out.println("Test completed on platform: " + browser);
        Thread.sleep(3000);

        RegistrationPage registrationPage = new RegistrationPage(getDriver(browser));

        registrationPage.setFirstName(firstName);
        registrationPage.setSurname(surname);
        registrationPage.setEmail(email);
        registrationPage.checkEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setBirthDate("7", "5", "1996");
        registrationPage.chooseGender(Genders.MALE);

        String currentFirstName = registrationPage.getFirstName();
        String currentSurname = registrationPage.getSurname();
        String currentEmail = registrationPage.getEmail();
        String currentPassword = registrationPage.getPassword();

        Assert.assertEquals(currentFirstName, firstName);
        Assert.assertEquals(currentSurname, surname);
        Assert.assertEquals(currentEmail, email);
        Assert.assertEquals(currentPassword, password);

    }

    @Test(dependsOnMethods = {"setUserData"}, dataProvider = "browsers")
    public void isRegBtnVisible(Drivers browser) throws InterruptedException, MalformedURLException {
        getDriver(browser).get("https://www.facebook.com/reg/");
        Thread.sleep(3000);

        RegistrationPage registrationPage = new RegistrationPage(getDriver(browser));

        registrationPage.setFirstName(firstName);
        registrationPage.setSurname(surname);
        registrationPage.setEmail(email);
        registrationPage.checkEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setBirthDate("7", "5", "1996");
        registrationPage.chooseGender(Genders.MALE);

        String currentFirstName = registrationPage.getFirstName();
        String currentSurname = registrationPage.getSurname();
        String currentEmail = registrationPage.getEmail();
        String currentPassword = registrationPage.getPassword();

        Assert.assertEquals(currentFirstName, firstName);
        Assert.assertEquals(currentSurname, surname);
        Assert.assertEquals(currentEmail, email);
        Assert.assertEquals(currentPassword, password);
        Assert.assertTrue(registrationPage.btnSignUp.isDisplayed());

        System.out.println("Test completed on platform: " + browser);
        Thread.sleep(3000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws MalformedURLException {
        if (chromeDriver != null) {
            chromeDriver.quit();
        } else if (firefoxDriver != null) {
            firefoxDriver.quit();
        }
    }


    public void fbTest(Drivers browser) throws InterruptedException, MalformedURLException {
        switch (browser) {
            case CHROME:
            case FF:
                webPlatformTest(browser);
                break;
//            case CHROME_MOBILE:
//            case FF_MOBILE:
//                mobilePlatformTest(driver);
//                break;
            default:
                System.out.println("I can't run test on platform: " + browser);
        }
    }

    public void webPlatformTest(Drivers driver) throws MalformedURLException {
        RegistrationPage registrationPage = new RegistrationPage(getDriver(driver));
        registrationPage.setFirstName(firstName);
        registrationPage.setSurname(surname);
        registrationPage.setEmail(email);
        registrationPage.checkEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setBirthDate("7", "5", "1996");
        registrationPage.chooseGender(Genders.MALE);
        registrationPage.clickSignUp();
    }

//    public static void mobilePlatformTest(Drivers driver) throws InterruptedException {
//        etFirstName = driver.findElement(By.xpath("//*[@name='firstname']"));
//        etFirstName.sendKeys(firstName);
//
//        etSurname = driver.findElement(By.xpath("//*[@name='lastname']"));
//        etSurname.sendKeys(surname);
//
//        WebElement btnNext = driver.findElement(By.xpath("//*[@class='_54k8 _8x0i _8x0j _9adg _723k']"));
//        btnNext.click();
//
//        Thread.sleep(3000);
//
//        WebElement ddDay = driver.findElement(By.xpath("//*[@id='day']"));
//        Select selectDdDay = new Select(ddDay);
//        selectDdDay.selectByValue("7");
//
//        WebElement ddMonth = driver.findElement(By.xpath("//*[@id='month']"));
//        Select selectDdMonth = new Select(ddMonth);
//        selectDdMonth.selectByValue("5");
//
//        WebElement ddYear = driver.findElement(By.xpath("//*[@id='year']"));
//        Select selectDdYear = new Select(ddYear);
//        selectDdYear.selectByValue("1996");
//        btnNext.click();
//
//        Thread.sleep(3000);
//
//        WebElement etPhone = driver.findElement(By.xpath("//*[@id='contactpoint_step_input']"));
//        etPhone.sendKeys("380958113315");
//        btnNext.click();
//
//        Thread.sleep(3000);
//
//        WebElement chbGenderMale = driver.findElement(By.xpath("//*[contains(text(),'Male')]"));
//        chbGenderMale.click();
//        btnNext.click();
//
//        Thread.sleep(3000);
//
//        etPassword = driver.findElement(By.xpath("//*[@name='reg_passwd__']"));
//        etPassword.sendKeys(password);
//        WebElement btnSignUp = driver.findElement(By.xpath("//*[@name='submit']"));
//        btnSignUp.click();
//    }
}
