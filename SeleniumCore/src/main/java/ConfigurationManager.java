import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    private static final ConfigurationManager instance = new ConfigurationManager();

    public final String CHROME_DRIVER;
    public final String FIREFOX_DRIVER;
    public final String VISIT_RESULT;
    public final String TEST_RESULT;
    public final String TEST_SNAPSHOT;

    private ConfigurationManager(){
        Config config = new Config(Config.CONFIG_FILE_NAME);

        CHROME_DRIVER = config.getProperty("driver.chrome");
        FIREFOX_DRIVER = config.getProperty("driver.firefox");
        VISIT_RESULT =  config.getProperty("visit.result");
        TEST_RESULT = config.getProperty("test.result");
        TEST_SNAPSHOT = config.getProperty("test.snapshot");
    }

    public static ConfigurationManager config(){
        return instance;
    }

    private static class Config{
        private static final String CONFIG_FILE_NAME = "config.properties";
        private Properties configFile;
        private Config(String configFileName) {
            configFile = new java.util.Properties();

            try{
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
                if (inputStream != null) {
                    configFile.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + configFile + "' not found in the classpath");         }
            }
            catch (Exception eta) {
                System.out.println("\tError;");
                System.out.println("\t******");
                System.out.println("\tCannot find \"" + configFileName +"\"");
                System.out.println("\tExiting program.");
                System.exit(1);
            }
        }

        private String getProperty(String key) {
            return this.configFile.getProperty(key);
        }
    }




    }


