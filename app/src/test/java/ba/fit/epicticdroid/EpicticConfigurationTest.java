package ba.fit.epicticdroid;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ammar on 9/28/16.
 */
public class EpicticConfigurationTest {
    @Test
    public void instanceIsConfiguredProperly() throws Exception{
        String key = "dfasdfasdfasdfasfsa";
        String api = "www.google.com";
        EpicticConfiguration configuration = new EpicticConfiguration(key,api);
        assertEquals(api, configuration.getApi());
        assertEquals(key, configuration.getKey());
    }
}
