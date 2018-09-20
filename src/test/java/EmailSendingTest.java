import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class EmailSendingTest {

    private WebDriver driver;

    @BeforeClass
    public void init(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void sendEmail(){

        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("gmail");
        search.submit();

        WebElement gMailLink = driver.findElement(By.xpath("//a[contains(@href, 'https://www.google.com/gmail/')]"));
        gMailLink.click();

        WebElement signIn = driver.findElement(By.cssSelector("a.gmail-nav__nav-link.gmail-nav__nav-link__sign-in"));
        signIn.click();

        WebElement email = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email.sendKeys("serkach.test@gmail.com");

        WebElement emailNext = driver.findElement(By.id("identifierNext"));
        emailNext.click();

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("QwErTy123456");

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('passwordNext').click();");

        WebElement openFormButton = driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3"));
        openFormButton.click();

        WebElement to = driver.findElement(By.name("to"));
        to.sendKeys("kachanserhii@gmail.com");

        WebElement subject = driver.findElement(By.name("subjectbox"));
        subject.sendKeys("Test");

        WebElement text = driver.findElement(By.cssSelector("div.Am.Al.editable.LW-avf"));
        text.sendKeys("Something");

        WebElement sendButton = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div/div[4]/table/tbody/tr/td[1]/div/div[2]"));
        sendButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link_vsm")));
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public void exit(){
        driver.quit();
    }
}
