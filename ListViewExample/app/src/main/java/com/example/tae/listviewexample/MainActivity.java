package com.example.tae.listviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private HandleXML obj;
    private String finalUrl = "http://opendata.busan.go.kr/openapi/service/GoodPriceStoreService/getGoodPriceStoreList?ServiceKey=KFnBHXKiFRZkxtf%2FWsREkZI3upwcV2ENs7BWztZh%2BTrTgVxDihhuFBS2uHaiYljamjMAL8CtRIYxgR7GBNjltA%3D%3D";
    private ListView listView;
    private List<User> users;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.lvItems);

        obj = new HandleXML(finalUrl);
        users = obj.fetchXML();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        while(obj.parsingComplete);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
