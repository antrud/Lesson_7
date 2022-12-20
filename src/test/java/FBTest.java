import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FBTest {
    protected static WebDriver driver;

    static String firstName = "Anton";
    static String surname = "Ukr";
    static String email = "anton_ukr9876@gmail.com";

    static String password = "password3456!";


    @BeforeTest
    public void setUp() {
        driver = WebDriverManager.chromedriver().create();;
    }

    @Test
    public void getPage() throws InterruptedException {
        driver.get("https://www.facebook.com/reg/");
        Thread.sleep(3000);
    }

    @Test
    public void setUserData() throws InterruptedException {
        getPage();
        fbTest(Drivers.CHROME);
        System.out.println("Test completed on platform: " + Drivers.CHROME.name());
        Thread.sleep(3000);
    }


    @Test(dependsOnMethods = {"setUserData"})
    public void checkUserData() throws InterruptedException {
        setUserData();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        String currentFirstName = registrationPage.getFirstName();
        Assert.assertEquals(currentFirstName, firstName);

        String currentSurname = registrationPage.getSurname();
        Assert.assertEquals(currentSurname, surname);

        String currentEmail = registrationPage.getEmail();
        Assert.assertEquals(currentEmail, email);

        String currentPassword = registrationPage.getPassword();
        Assert.assertEquals(currentPassword, password);
    }

    @Test(dependsOnMethods = {"setUserData"})
    public boolean isRegBtnVisible() throws InterruptedException {
        checkUserData();
        return new RegistrationPage(driver).btnSignUp.isDisplayed();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


    public static void fbTest(Drivers driver) throws InterruptedException {
        switch (driver) {
            case CHROME:
            case FF:
                webPlatformTest();
                break;
            case CHROME_MOBILE:
            case FF_MOBILE:
                mobilePlatformTest();
                break;
            default:
                System.out.println("I can't run test on platform: " + driver.name());
        }
    }

    public static void webPlatformTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setFirstName(firstName);
        registrationPage.setSurname(surname);
        registrationPage.setEmail(email);
        registrationPage.checkEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setBirthDate("7", "5", "1996");
        registrationPage.chooseGender(Genders.MALE);
        registrationPage.clickSignUp();
    }

    public static void mobilePlatformTest() throws InterruptedException {
        /*etFirstName = driver.findElement(By.xpath("//*[@name='firstname']"));
        etFirstName.sendKeys(firstName);

        etSurname = driver.findElement(By.xpath("//*[@name='lastname']"));
        etSurname.sendKeys(surname);

        WebElement btnNext = driver.findElement(By.xpath("//*[@class='_54k8 _8x0i _8x0j _9adg _723k']"));
        btnNext.click();

        Thread.sleep(3000);

        WebElement ddDay = driver.findElement(By.xpath("//*[@id='day']"));
        Select selectDdDay = new Select(ddDay);
        selectDdDay.selectByValue("7");

        WebElement ddMonth = driver.findElement(By.xpath("//*[@id='month']"));
        Select selectDdMonth = new Select(ddMonth);
        selectDdMonth.selectByValue("5");

        WebElement ddYear = driver.findElement(By.xpath("//*[@id='year']"));
        Select selectDdYear = new Select(ddYear);
        selectDdYear.selectByValue("1996");
        btnNext.click();

        Thread.sleep(3000);

        WebElement etPhone = driver.findElement(By.xpath("//*[@id='contactpoint_step_input']"));
        etPhone.sendKeys("380958113315");
        btnNext.click();

        Thread.sleep(3000);

        WebElement chbGenderMale = driver.findElement(By.xpath("//*[contains(text(),'Male')]"));
        chbGenderMale.click();
        btnNext.click();

        Thread.sleep(3000);

        etPassword = driver.findElement(By.xpath("//*[@name='reg_passwd__']"));
        etPassword.sendKeys(password);
        WebElement btnSignUp = driver.findElement(By.xpath("//*[@name='submit']"));
        btnSignUp.click();*/
    }
}
