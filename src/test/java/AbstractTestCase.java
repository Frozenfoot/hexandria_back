import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nikita on 22.02.2017.
 */
public abstract class AbstractTestCase {
    protected static Properties properties;
    protected static String localAddress;
    //init properties
    static {
        initProperties();
    }
    private static void initProperties(){
        properties = new Properties();
        try(InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        localAddress = "localhost" + properties.getProperty("server.port");
    }
}
