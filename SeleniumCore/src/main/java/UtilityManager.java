import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityManager {

    public static String SNAP_SHOT_FOLDER;

    private String filename;

    public UtilityManager(){
        SNAP_SHOT_FOLDER = ConfigurationManager.config().TEST_SNAPSHOT;
    }

    public void setFilename( String filename ) {
        this.filename = filename;
    }

    public String getFilename(){
        return filename;
    }

    public void takeSnapShot( WebDriver webdriver, String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss.SSS");

        String out_timestamp = sdf.format(new Date());

        if(fileWithPath == null){
           boolean success = (new File(fileWithPath)).mkdirs();
           if(success){
               fileWithPath = fileWithPath + "\\snapshot_"+out_timestamp+".png";
           }
        }
        else
        {
            fileWithPath = fileWithPath + "\\snapshot_"+out_timestamp+".png";
        }

        File DestFile=new File(fileWithPath);

        filename = fileWithPath;

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }

    public String getFilePath(String fileWithPath, String filename){

        //Move image file to new destination
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss.SSS");

        String out_timestamp = sdf.format(new Date());

        if(fileWithPath == null)
            (new File(fileWithPath)).mkdirs();
        fileWithPath = fileWithPath + "\\"+filename+out_timestamp+".png";
        return fileWithPath;

    }

    public boolean templateMatch(String src, String tar){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source=null;
        Mat template=null;
        source=Imgcodecs.imread(tar);
        template=Imgcodecs.imread(src);

        Mat outputImage=new Mat();
        int machMethod=Imgproc.TM_CCOEFF;
        //Template matching method
        Imgproc.matchTemplate(source, template, outputImage, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc=mmr.maxLoc;
        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(255, 255, 255));

        String filePath = getFilePath(ConfigurationManager.config().TEST_SNAPSHOT,"sounc");

        Imgcodecs.imwrite(filePath, source);

        if(matchLoc != null)return true;
        else return false;


            }




}
