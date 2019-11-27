package com.hungrydroid.restaurantapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hungrydroid.restaurantapp.adapters.MenuListAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MenuListActivity extends AppCompatActivity {

    ListView lv;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Menu");
        lv = (ListView)findViewById(R.id.menu_list1);
        loadData();
    }

    public void loadData() {
        System.out.println("Menu::"+"Load Data");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://hungrydroid.com/restaurant/data.php", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                pDialog = new ProgressDialog(MenuListActivity.this);
                pDialog.setMessage("Loading Data...");
                pDialog.setCancelable(false);
                pDialog.show();
                System.out.println("Menu::"+"Start");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    System.out.println("Menu::"+"Try Start");
                    JSONObject m = new JSONObject(new String(responseBody));
                    JSONArray contacts = m.getJSONArray("menu");
                    //System.out.println("Menu::"+contacts.toString());
                    //System.out.println("Menu::"+contacts.length());
                    //Toast.makeText(getActivity(), contacts.toString(), Toast.LENGTH_SHORT).show();

                    String[] prgmImages = new String[contacts.length()];
                    String[] prgmNameList = new String[contacts.length()];
                    String[] prgmIds = new String[contacts.length()];
                    String[] prgmDesc = new String[contacts.length()];
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        //String name = c.getString("name");


                        prgmNameList[i] = c.getString("name");
                        prgmImages[i] = c.getString("image");
                        System.out.println(prgmImages[i]);
                        prgmIds[i] = c.getString("id");
                        prgmDesc[i] = c.getString("description");
                    }

                    lv.setAdapter(new MenuListAdapter(MenuListActivity.this, prgmNameList, prgmImages, prgmIds,prgmDesc));
                    System.out.println("Menu::"+"Try End");

                } catch (Exception e) {
                    Toast.makeText(MenuListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    lv.setAdapter(null);
                }
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(MenuListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                //finish();
            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }


        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
