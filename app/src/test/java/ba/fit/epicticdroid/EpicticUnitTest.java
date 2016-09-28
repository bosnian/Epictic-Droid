package ba.fit.epicticdroid;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ammar on 9/28/16.
 */
public class EpicticUnitTest {

    @Test
    public void instanceIsConfiguredProperly() throws Exception{
        EpicticConfiguration configuration = new EpicticConfiguration("key","www.google.com");
        Epictic epictic = new Epictic(configuration);
        assertEquals(configuration, epictic.getConfiguration());
    }
}
