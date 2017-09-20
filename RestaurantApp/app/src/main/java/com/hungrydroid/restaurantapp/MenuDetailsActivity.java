package com.hungrydroid.restaurantapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.hungrydroid.restaurantapp.utils.Connectivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class MenuDetailsActivity extends AppCompatActivity {

    TextView tv,tv2,tv4;
    PhotoView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Menu");
        tv = (TextView) findViewById(R.id.menudetails_textView1);
        tv.setText(getIntent().getStringExtra("name"));

        tv2 = (TextView) findViewById(R.id.menudetails_textView2);
        tv2.setText("Price: "+getIntent().getStringExtra("price"));

        tv4 = (TextView) findViewById(R.id.menudetails_textView4);
        tv4.setText(getIntent().getStringExtra("desc"));

        iv = (PhotoView) findViewById(R.id.menudetails_imageView);
        iv.setDrawingCacheEnabled(true);



            if (Connectivity.isNetworkAvailable(MenuDetailsActivity.this)) {

                Picasso.with(this).load(getIntent().getStringExtra("image")).placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                        Toast.makeText(MenuDetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(MenuDetailsActivity.this, "Network Not Available!", Toast.LENGTH_SHORT).show();

            }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                try {
                    if (isStoragePermissionGranted()) {
                        share();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                }
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void share() {
        Bitmap bmap = iv.getDrawingCache();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(MenuDetailsActivity.this, bmap));
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "Share..."));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "greatwarphotos", null);
        return Uri.parse(path);
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {

            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            share();
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}
