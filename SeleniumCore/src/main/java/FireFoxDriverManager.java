import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxDriverManager extends DriverManager {

    @Override
    public void createWebDriver(){
        FirefoxOptions options = new FirefoxOptions();

        //set your browser-specific options here
        this.driver = new FirefoxDriver();
    }


}
