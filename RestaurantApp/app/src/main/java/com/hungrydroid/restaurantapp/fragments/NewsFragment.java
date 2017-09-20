package com.hungrydroid.restaurantapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.hungrydroid.restaurantapp.R;
import com.hungrydroid.restaurantapp.adapters.NewsAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    ListView lv;
    ProgressDialog pDialog;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        lv = (ListView)getActivity().findViewById(R.id.news_list1);
        loadData();
    }

    public void loadData() {
        System.out.println("Menu::"+"Load Data");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://hungrydroid.com/restaurant/data.php", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                pDialog = new ProgressDialog(getActivity());
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
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        //String name = c.getString("name");


                        prgmNameList[i] = c.getString("name");
                        prgmImages[i] = c.getString("image");
                        prgmIds[i] = c.getString("id");

                    }

                    lv.setAdapter(new NewsAdapter(getActivity(), prgmNameList, prgmImages, prgmIds));
                    System.out.println("Menu::"+"Try End");

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                //finish();
            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }


        });
    }
}
