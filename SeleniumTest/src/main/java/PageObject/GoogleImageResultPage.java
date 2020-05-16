package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleImageResultPage {
    WebDriver driver;

    //constructor of this PageObject
    public GoogleImageResultPage(WebDriver driver){
        this.driver = driver;
    }

    //element locators of the web elements on this page
    //private String imageXpathList = "//div[@class='normal-header']//following-sibling::div//*[@class='s']/descendant::img";
    private String imageXpathList = "//div[@id='search']//following::div//*[@class='s']/descendant::img";
    private By imageLinkList = By.xpath(imageXpathList);
    private String nextXpath = "//div[@id='foot']//child::td//*[text()='Next']";
    private By nextLink = By.xpath(nextXpath);


    public List<WebElement> searchImageList(){
        return driver.findElements(imageLinkList);
    }

    public void clickImageLink(int count){
        List<WebElement> link = searchImageList();
        link.get(count).click();
    }

    public void clickImageLinks() {
        for (int i = 0; i < searchImageList().size(); i++) {
            searchImageList().get(i).click();
            driver.navigate().back();
        }
    }

        public void clickNext(){
        driver.findElement(nextLink).click();}

        public WebElement getNextLink(){
               return driver.findElement(nextLink);
        }

        public boolean nextLinkExist(){
        try{
            getNextLink();
            return true;
        }
        catch (Exception e){
            return false;
        }
        }

    }



