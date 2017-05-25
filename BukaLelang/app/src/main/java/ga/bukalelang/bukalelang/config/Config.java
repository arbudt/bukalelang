package ga.bukalelang.bukalelang.config;

/**
 * Created by arbudt on 5/23/2017.
 */

public class Config {
    private static final Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }

    public static String BASE_API_BUKALAPAK  = "https://api.bukalapak.com/v2/";
}
