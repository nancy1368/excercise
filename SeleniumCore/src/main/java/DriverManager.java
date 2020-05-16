import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createWebDriver();

    public void quiteWebDriver(){
        if (null!=driver){
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver() {
        if(null==driver){
            createWebDriver();
        }
        return driver;
    }
}
