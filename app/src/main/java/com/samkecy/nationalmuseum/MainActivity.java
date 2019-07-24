package com.samkecy.nationalmuseum;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
// Help links
    // https://guides.codepath.com/android/Using-a-BaseAdapter-with-ListView
    // https://www.homeandlearn.co.uk/android/custom_list_new_activity.html

    AdapterViewFlipper adapterViewFlipper;
    GestureDetector mGestureDetector;
    CustomAdapter adapters;

    int[] IMAGES = {

            R.drawable.back,
            R.drawable.culture,
            R.drawable.coin,
            R.drawable.fifty,
            R.drawable.dance,
            R.drawable.girls,
            R.drawable.twenty,
            R.drawable.onethousand

    };

    String[] NAME = {

            "Cloth",
            "Culture",
            "Nigeria Coins",
            "Fifty Naira",
            "Dancing",
            "Nigeria girls",
            "Twenty Naira Note",
            "One Thousand Naira Note"

    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        */
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.ViewFliper);

        adapters = new CustomAdapter(getApplicationContext(), NAME, IMAGES);
        adapterViewFlipper.setAdapter(adapters);
        adapterViewFlipper.setFlipInterval(2600);
        adapterViewFlipper.setAutoStart(true);


        adapterViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });

//        adapterViewFlipper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                val2 = i;
//            }
//        });

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
        switch (item.getItemId()) {

            case R.id.action_rate:
                rateApp();
                break;
            case R.id.action_about:
                Intent about = new Intent(MainActivity.this, about.class);
                startActivity(about);
                break;
            case R.id.action_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "I suggest this app for you : https://play.google.com/store/apps/details?id=com.samkecy.fcairesultchecker");
                intent.setType("text/plain");
                startActivity(intent);
                break;
            case R.id.action_FeedBack:
                String uriText =
                        "mailto:samkecy1@gmail.com" +
                                "?subject=" + Uri.encode("") +
                                "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(sendIntent, "Send message"));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                adapterViewFlipper.stopFlipping();
                adapterViewFlipper.showNext();
                adapterViewFlipper.startFlipping();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                adapterViewFlipper.stopFlipping();
                adapterViewFlipper.showPrevious();
                adapterViewFlipper.startFlipping();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            // use the adapter to get the clicked item
            long val = adapterViewFlipper.indexOfChild(adapterViewFlipper.getCurrentView());

            Intent intent = new Intent(MainActivity.this, info.class);
            intent.putExtra("values", val);
            startActivity(intent);
//            Toast.makeText(getBaseContext(), "you choose " + val, Toast.LENGTH_SHORT).show();

            return true;
        }

    }


    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }


}