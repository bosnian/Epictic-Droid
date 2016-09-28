/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Ammar Hadzic
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ba.fit.epictic;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ammar on 9/28/16.
 */
public class Epictic {

    private Context context;
    private EpicticConfiguration configuration = null;
    private String identifier = "";
    private String PREF_KEY = "epictic-user-identifier";
    private Hashtable<String,Object> base = new Hashtable<>();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    public static Epictic shared = null;

    /**
     * Initialize shared instance
     *
     * @param ctx context for shared preferences
     * @param configuration configuration to be set
     */
    public static void initShared(Context ctx, EpicticConfiguration configuration){
        shared = new Epictic(ctx,configuration);
    }

    /**
     * Initialize library
     *
     * @param ctx context for shared preferences
     * @param configuration configuration to be set
     */
    public Epictic(Context ctx, EpicticConfiguration configuration){
        this.context = ctx;
        this.configuration = configuration;
    }

    /**
     * Track event with given name without properties
     *
     * @param name          Name of event
     */
    public void track(String name){
        EpicticRecord prop = new EpicticRecord();
        prop.key = configuration.getKey();
        EpicticRecordContent content = new EpicticRecordContent();
        content.properties = new Hashtable<>();
        content.identifier = retrieveIdentifier();
        content.name = name;
        content.base = base;
        prop.content = content;

        Gson gson = new Gson();
        this.sendRequest(gson.toJson(prop));
    }

    /**
     * Track event with given name and properties
     *
     * @param name          Name of event
     * @param properties    Properties of sent with event
     */
    public void track(String name, Hashtable<String,Object> properties){
        EpicticRecord prop = new EpicticRecord();
        prop.key = configuration.getKey();
        EpicticRecordContent content = new EpicticRecordContent();
        content.properties = properties;
        content.identifier = retrieveIdentifier();
        content.name = name;
        content.base = base;
        prop.content = content;

        Gson gson = new Gson();
        this.sendRequest(gson.toJson(prop));
    }

    /**
     * Sends HTTP Post request
     *
     * @param json Payload to be sent in json format
     */
    private void sendRequest(String json)  {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(configuration.getApi())
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("API","Fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("API","Success");
            }
        });
    }

    /**
     * Generate GUID if identifier is not specified, otherwise use that one
     *
     * @return identifier
     */
    private String retrieveIdentifier(){
        String guid = "";
        if(!PrefUtils.getFromPrefs(context,PREF_KEY,"").isEmpty()){
            guid = PrefUtils.getFromPrefs(context,PREF_KEY,"");
        } else {
            guid = UUID.randomUUID().toString();
            PrefUtils.saveToPrefs(context,PREF_KEY,guid);
        }
        return guid;
    }

    /**
     * Set custom identifier for user instead of GUID
     *
     * @param identifier    identifier to be used
     */
    public void identify(String identifier){
        this.identifier = identifier;
        PrefUtils.saveToPrefs(context,PREF_KEY,identifier);
    }

    /**
     * Register properties which will be sent with every request
     *
     * @param properties    dictionary of properties to add
     */
    public void register(Hashtable<String,Object> properties){
        this.base.putAll(properties);
    }

    /**
     * Get current configuration
     *
     * @return  EpicticConfiguration
     */
    public EpicticConfiguration getConfiguration(){
        return configuration;
    }
}
