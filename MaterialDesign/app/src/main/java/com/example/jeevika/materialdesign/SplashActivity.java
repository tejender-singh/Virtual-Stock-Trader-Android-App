package com.example.jeevika.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Handler;

import activity.MainActivity;
import myhelper.MainActivity1;

public class SplashActivity extends AppCompatActivity {

    WebView web;
    TextView tv;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        tv=(TextView)findViewById(R.id.textView);
        web=(WebView)findViewById(R.id.webView );
        web.loadUrl("file:///android_asset/Splash.html");
        Thread t=new Thread(){
            public void run()
            {
                try {
                    sleep(7500);

                    SplashActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            web.setVisibility(View.GONE);
                            spinner.setVisibility(View.VISIBLE);
                        }
                    });

                    Class.forName("com.mysql.jdbc.Driver");
                    Global.conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/virtuastock", "virtuastock", "virtuastock");
                    //                  Global.conn = DriverManager.getConnection("jdbc:mysql://192.168.43.158:3306/virtuastock", "root", "root");

                    SplashActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spinner.setVisibility(View.GONE);
                        }
                    });

                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    Log.e("TAG", e.toString());
                    e.printStackTrace();
                }catch (SQLException e) {
                    // TODO Auto-generated catch block
                    Log.e("TAG", e.toString());
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    String cid=Global.read(getApplicationContext());
                    if(cid.equalsIgnoreCase("-1")){
                        Intent intent=new Intent(getBaseContext(), MainActivity1.class);
                        startActivity(intent);}
                    else
                    {
                        Intent intent=new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }; t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
