package ba.fit.epicticdroid;

/**
 * Created by ammar on 9/28/16.
 */
public class EpicticConfiguration {

    private String key = "";

    private String api = "";

    public EpicticConfiguration(String key, String api){
        this.key = key;
        this.api = api;
    }

    public String getApi() {
        return api;
    }

    public String getKey() {
        return key;
    }
}
