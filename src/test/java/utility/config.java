package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class config {

    private Properties getProperties() {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            String resourceFile = "src/test/resources/AutoTracker.properties";
            properties.load(new FileInputStream(resourceFile));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return properties;
    }

    public String getProperrty(String propName) {
        return getProperties().getProperty(propName);
    }
}


