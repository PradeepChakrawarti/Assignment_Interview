package Day1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Project {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

        // Initialize ExtentReports
        report = new ExtentReports("E:\\SCREEN SHOTS\\Report.html");

        // Create a test in ExtentReports
        test = report.startTest("Project Test");
    }

    @Test(priority = 1)
    public void registerFunctionality() throws IOException {
        test.log(LogStatus.PASS, "Starting Register Functionality Test");

        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Pradeep");
        driver.findElement(By.id("LastName")).sendKeys("Chakrawarti");

        WebElement dayDropdown = driver.findElement(By.name("DateOfBirthDay"));
        Select daySelect = new Select(dayDropdown);
        daySelect.selectByValue("2");

        WebElement monthDropdown = driver.findElement(By.name("DateOfBirthMonth"));
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByVisibleText("January");

        WebElement yearDropDown = driver.findElement(By.name("DateOfBirthYear"));
        Select sl3 = new Select(yearDropDown);
        sl3.selectByValue("2000");

        driver.findElement(By.id("Email")).sendKeys("Pradeep@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("Google");
        driver.findElement(By.name("Newsletter")).click();
        driver.findElement(By.id("Password")).sendKeys("Selenium");
        driver.findElement(By.name("ConfirmPassword")).sendKeys("Selenium");
        driver.findElement(By.id("register-button")).click();

        test.log(LogStatus.PASS, "Registration successful");
    }

    @Test(priority = 2, enabled = true)
    public void loginFunctionality() throws IOException {
        test.log(LogStatus.PASS, "Starting Login Functionality Test");

        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.className("email")).sendKeys("Pradeep@gmail.com");
        driver.findElement(By.className("password")).sendKeys("pradeep@123");
        driver.findElement(By.id("RememberMe")).click();
        driver.findElement(By.className("button-1")).click();

        test.log(LogStatus.PASS, "Login successful");
    }

    @AfterTest
    public void tearDown() {
        // End the test in ExtentReports
        report.endTest(test);

        // Flush the ExtentReports
        report.flush();

        driver.quit();
    }
}
