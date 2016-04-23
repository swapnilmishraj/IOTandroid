package com.example.swapnilmishraa.helloworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.*;
import android.net.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "HttpExample";
    private EditText urlText;
    private TextView textView;
    private boolean button1on = false;
    private boolean button1off = false;
    private boolean button2on = false;
    private boolean button2off = false;

    private boolean button3on = false;
    private boolean button3off = false;
    private boolean button4off = false;
    private boolean button4on= false;
    private String stringUrl;
    private String urlMain;
//    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
    }



    public void Button1On(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field1=100";

        button1on=true;
        myClickHandler();
//        img.setBackgroundColor(Color.YELLOW);
    }

    public void Button1Off(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field1=0";
        ImageView img=(ImageView)findViewById(R.id.imageView);
        button1off=true;
        myClickHandler();
//        img.setBackgroundColor(Color.BLACK);
    }

    public void Button2On(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field2=100";
//        ImageView img=(ImageView)findViewById(R.id.imageView3);
        button2on=true;
        myClickHandler();
//        img.setBackgroundColor(Color.YELLOW);
    }

    public void Button2Off(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field2=0";
//        ImageView img=(ImageView)findViewById(R.id.imageView3);
        button2off=true;
        myClickHandler();
//        img.setBackgroundColor(Color.BLACK);
    }

    public void Button3On(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field3=100";
//        ImageView img=(ImageView)findViewById(R.id.imageView5);
        button3on=true;
        myClickHandler();
//        img.setBackgroundColor(Color.YELLOW);
    }

    public void Button3Off(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field3=0";
//        ImageView img=(ImageView)findViewById(R.id.imageView5);
//        img.setBackgroundColor(Color.BLACK);
        button3off=true;
        myClickHandler();
//        img.setBackgroundColor(Color.BLACK);

    }
    public void Button4On(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field4=100";
//        ImageView img=(ImageView)findViewById(R.id.imageView7);
        button4on=true;
        myClickHandler();
//        img.setBackgroundColor(Color.YELLOW);

    }

    public void Button4Off(View view)
    {
        urlMain="https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field4=0";
//        ImageView img=(ImageView)findViewById(R.id.imageView7);
        button4off=true;
        myClickHandler();
//        img.setBackgroundColor(Color.BLACK);

    }



    public void myClickHandler() {
        // Gets the URL from the UI's text field.
        //String stringUrl1 = "https://api.thingspeak.com/update?api_key=03CJ77L9RV48XD4B&field1=100";
        textView.setText("waittt");
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(urlMain);
        } else {
            textView.setText("No network connection available.");
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. Check net Connection.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.

        protected void onPostExecute(String result) {
            String resultTemp = "";

            Log.d(DEBUG_TAG, "result is: " + result);
            for (int i = 0; i < result.length(); i++) {
                if (Character.isDigit(result.charAt(i))) {
                    resultTemp += result.charAt(i);
                }
            }
            Log.d(DEBUG_TAG, "resultTemp is: " + resultTemp);
            int p=1;
            if(resultTemp!="")
                 p = Integer.parseInt(resultTemp);
            if (p == 0) {
                //repeat();
                myClickHandler();
                Log.d(DEBUG_TAG, "repeat task");
            } else
            {
                if (button1on==true) {
                    ImageView img=(ImageView)findViewById(R.id.imageView);
                    img.setBackgroundColor(Color.YELLOW);
                    button1on=false;
                }
                else if (button1off==true)
                {
                    ImageView img=(ImageView)findViewById(R.id.imageView);
                    img.setBackgroundColor(Color.BLACK);
                    button1off=false;
                }
                else if (button2on==true)
                {
                    button2on=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView3);
                    img.setBackgroundColor(Color.YELLOW);
                }
                else if (button2off==true)
                {
                    button2off=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView3);
                    img.setBackgroundColor(Color.BLACK);
                }
                else if (button3on==true)
                {
                    button3on=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView5);
                    img.setBackgroundColor(Color.YELLOW);
                }
                else if (button3off==true)
                {
                    button3off=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView7);
                    img.setBackgroundColor(Color.BLACK);
                }
                else if (button4on==true)
                {
                    button4on=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView7);
                    img.setBackgroundColor(Color.YELLOW);
                }
                else if (button4off==true)
                {
                    button4off=false;
                    ImageView img=(ImageView)findViewById(R.id.imageView7);
                    img.setBackgroundColor(Color.BLACK);
                }
                textView.setText(result);
            }

        }


        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(myurl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

//        public void repeat() {
//            Log.d(DEBUG_TAG, "repeating task");
//            textView.setText("waittt");
//            ConnectivityManager connMgr = (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isConnected()) {
//                new DownloadWebpageTask().execute(urlMain);
//            } else {
//                textView.setText("No network connection available.");
//            }
//        }
    }
};

