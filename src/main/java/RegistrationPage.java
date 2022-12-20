import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@name='firstname']")
    public WebElement etFirstName;

    @FindBy(xpath = "//*[@name='lastname']")
    public WebElement etSurname;

    @FindBy(xpath = "//*[@name='reg_email__']")
    public WebElement etLogin;

    @FindBy(xpath = "//*[@name='reg_email_confirmation__']")
    public WebElement etLoginCheck;

    @FindBy(xpath = "//*[@name='reg_passwd__']")
    public WebElement etPassword;

    @FindBy(xpath = "//*[@id='day']")
    public WebElement ddDay;

    @FindBy(xpath = "//*[@id='month']")
    public WebElement ddMonth;

    @FindBy(xpath = "//*[@id='year']")
    public WebElement ddYear;

    @FindBy(xpath = "//*[contains(text(),'Male')]")
    public WebElement chbGenderMale;

    @FindBy(xpath = "//*[contains(text(),'Female')]")
    public WebElement chbGenderFemale;

    @FindBy(xpath = "//*[@name='websubmit']")
    public WebElement btnSignUp;

    public void setFirstName(String firstName) {
        etFirstName.sendKeys(firstName);
    }

    public String getFirstName() {
        return etFirstName.getAttribute("value");
    }

    public void setSurname(String surname) {
        etSurname.sendKeys(surname);
    }

    public String getSurname() {
        return etSurname.getAttribute("value");
    }

    public void setEmail(String email) {
        etLogin.sendKeys(email);
    }

    public String getEmail() {
        return etLogin.getAttribute("value");
    }

    public void checkEmail(String email) {
        etLoginCheck.sendKeys(email);
    }

    public void setPassword (String password) {
        etPassword.sendKeys(password);
    }

    public String getPassword () {
        return etPassword.getAttribute("value");
    }

    public void setBirthDate(String day, String month, String year) {
        Select selectDdDay = new Select(ddDay);
        selectDdDay.selectByValue(day);

        Select selectDdMonth = new Select(ddMonth);
        selectDdMonth.selectByValue(month);

        Select selectDdYear = new Select(ddYear);
        selectDdYear.selectByValue(year);
    }

    public void chooseGender(Genders gender) {
        switch (gender) {
            case MALE:
                chbGenderMale.click();
            case FEMALE:
                chbGenderFemale.click();
        }
    }

    public void clickSignUp() {
        btnSignUp.click();
    }




    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
