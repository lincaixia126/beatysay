package com.example.lin.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.lin.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 18/1/11.
 */

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    String jsonString = "[{\"id\":18,\"city\":\"test\",\"street\":\"test 1\",\"zipcode\":121209,\"state\":\"IL\",\"lat\":32.158138,\"lng\":34.807838},{\"id\":19,\"city\":\"test\",\"street\":\"1\",\"zipcode\":76812,\"state\":\"IL\",\"lat\":32.161041,\"lng\":34.810410}]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        List<ListMapData> mUser = fromToJson(jsonString);
        List<ListMapData> mUser = new Gson().fromJson(jsonString,
                new TypeToken<List<ListMapData>>(){}.getType());
        Log.d(TAG, "onCreate: " + mUser.toString());

        Toast.makeText(this, mUser.toString(), Toast.LENGTH_SHORT).show();

//        test(jsonString);
    }

    private void test(String json) {
        class Foo<T> {
            T value;
        }
        Gson gson = new Gson();
        Foo<MapData> foo = new Foo<MapData>();
        gson.toJson(foo); // May not serialize foo.value correctly

        gson.fromJson(json, foo.getClass()); // Fails to deserialize foo.value as Bar


    }

    //根据泛型返回解析制定的类型
    public <M> M fromToJson(String json) {
        Type type = new TypeToken<M>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    class WrapMapData {
        List<MapData> wrapData;
    }

    class MapData {
        int id;
        String city;
    }
}