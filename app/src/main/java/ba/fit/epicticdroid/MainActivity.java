package ba.fit.epicticdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Dictionary;
import java.util.Hashtable;

import ba.fit.epictic.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Epictic t = new Epictic(getApplicationContext(),new EpicticConfiguration("06c4aed327920fd83a81e624b37fb9e3", "http://192.168.1.5:3000"));

        Epictic.initShared(getApplicationContext(),new EpicticConfiguration("06c4aed327920fd83a81e624b37fb9e3", "http://192.168.1.5:3000"));
        Hashtable<String,Object> a = new Hashtable<>();
        a.put("prop4","mujo");
        a.put("pro22","suljo");

        Dictionary<String,Object> b = new Hashtable<>();
        b.put("prop4","mujo");
        b.put("pro22","suljo");
        a.put("aaa",b);

        Hashtable<String,Object> d = new Hashtable<>();
        d.put("aa","aa");
        d.put("bb","bb");
        Epictic.shared.register(d);

        Hashtable<String,Object> e = new Hashtable<>();
        e.put("aa","cc");
        e.put("dd","dd");
        e.put("bb",4);
        Epictic.shared.register(e);
        Epictic.shared.track("Shared1234",a);
    }
}
