import PageObject.GoogleImagePage;
import PageObject.GoogleImageResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleImagePageTests {
    DriverManager driverManager;
    WebDriver driver;
    GoogleImagePage googleImagePage;
    UtilityManager utilityManager;
    GoogleImageResultPage googleImageResultPage;

    @BeforeClass
    public void setUp() {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver = driverManager.getWebDriver();
        utilityManager = new UtilityManager();
    }

   @BeforeMethod
   public void clickSearchByImageTest() throws Exception {
       driver.get("https://www.google.com/imghp?hl=en&tab=ri&ogbl");
       googleImagePage = new GoogleImagePage(driver);
       googleImagePage.clickSearchImage();
       assertEquals(googleImagePage.getSearchImageWindowTitle(),"Search by image");
    }

    @Test(priority = 0, enabled = false)
    @Parameters("url")
    public void searchImageByURL(String url) throws Exception {
        googleImagePage.clickPasteImageURL();
        googleImagePage.enterImageURL(url);
        googleImagePage.clickSearchByImageButton();
        assertEquals(driver.getTitle().toString(),"Google Search");
        utilityManager.takeSnapShot(driver, UtilityManager.SNAP_SHOT_FOLDER);
    }

    @Test(priority = 1)
    @Parameters({"image","count"})
    public void searchByUpload(String image, int count) throws Exception {
        googleImagePage.clickSearchByUpload();
        assertTrue(googleImagePage.getUploadImageButton().toString().contains("encoded_image"));
        googleImagePage.getUploadImageButton().sendKeys(image);
        assertEquals(driver.getTitle().toString(),"Google Search");
        utilityManager.takeSnapShot(driver, UtilityManager.SNAP_SHOT_FOLDER);
        googleImageResultPage = new GoogleImageResultPage(driver);
        googleImageResultPage.clickImageLink(count);
        utilityManager.takeSnapShot(driver, UtilityManager.SNAP_SHOT_FOLDER);
        driver.navigate().back();

        int i;
        while(googleImageResultPage.nextLinkExist()){
            int size = googleImageResultPage.searchImageList().size();
            System.out.print(size);
        for(i = 0 ; i < size; i++){
            googleImageResultPage.searchImageList().get(i).click();
            sleep(10000);
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img")));
            utilityManager.takeSnapShot(driver, UtilityManager.SNAP_SHOT_FOLDER);
            boolean similar = utilityManager.templateMatch(image,utilityManager.getFilename());
            assertEquals(similar,true);
            driver.navigate().back();
        }
        googleImageResultPage.clickNext();}
    }

    @AfterClass
    public  void tearDown(){
        driverManager.quiteWebDriver();
    }
}
