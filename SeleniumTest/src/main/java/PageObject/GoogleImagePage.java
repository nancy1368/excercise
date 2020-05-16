package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleImagePage {

    WebDriver driver;

    //constructor of this PageObject
    public GoogleImagePage(WebDriver driver){
        this.driver = driver;
    }

    //element locators of the web elements on this page
    private By serachImageButton = By.className("LM8x9c");
    private By searchImageWindow = By.className("YgiJIe");
    private By pasteImageURL = By.id("cjyo4e");
    private By imageURL = By.id("Ycyxxc");
    private By searchByImageButton = By.id("aoghAf");
    private By searchByUpload = By.linkText("Upload an image");
    private By uploadImageButton = By.name("encoded_image");

    //this method allows to click search image button
    public void clickSearchImage(){
        driver.findElement(serachImageButton).click();
    }

    public String getSearchImageWindowTitle(){
       String title =  driver.findElement(searchImageWindow).getText();
       return title;
    }

    public void clickPasteImageURL(){
        driver.findElement(pasteImageURL).click();
    }

    public void enterImageURL(String param){
        driver.findElement(imageURL).clear();
        driver.findElement(imageURL).sendKeys(param);
    }

    public void clickSearchByImageButton(){
        driver.findElement(searchByImageButton).click();
    }

    public void clickSearchByUpload(){driver.findElement(searchByUpload).click();}

    public WebElement getUploadImageButton(){
        WebElement element = driver.findElement(uploadImageButton);
        return element;

    }


}
