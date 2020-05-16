import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    @Override

    public void createWebDriver(){
        System.setProperty("webdriver.chrome.driver", ConfigurationManager.config().CHROME_DRIVER);
        ChromeOptions options = new ChromeOptions();

        //set your browser-specific options here
        this.driver = new ChromeDriver(options);
    }

}
