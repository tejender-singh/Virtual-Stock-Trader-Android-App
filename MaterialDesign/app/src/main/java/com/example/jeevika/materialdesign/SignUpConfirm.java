package com.example.jeevika.materialdesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import activity.MainActivity;
import model.Gmail;

public class SignUpConfirm extends AppCompatActivity {

    int i= (int) (Math.random()*1000000);
    Button b;
    ProgressDialog pd;
    EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirm);
        Intent in=getIntent();
        final String email=in.getStringExtra("email");
        final String user=in.getStringExtra("user");
        final String pass=in.getStringExtra("pass");
        final String phone=in.getStringExtra("phone");
        b= (Button) findViewById(R.id.btnSingIn);
        t= (EditText) findViewById(R.id.etPass);
        Toast.makeText(getBaseContext(),email+" : "+phone,Toast.LENGTH_LONG).show();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                Gmail g=new Gmail();
                g.setTo(new String[]{email});
                g.setBody("Your Registration Pin is : " + i);
                try {
                    g.send();
                } catch (Exception e) {
                    Log.e("Tag",e.toString());
                }

                    String s="https://api.clicksend.com/http/v2/send.php?method=http&username=cooltoon007&key=963E21F5-6C5B-594C-EC5D-AAE1A4094DF0&to=+91"+phone+"&message=PIN:"+i;

                g.processFeed(s);

                try {
    Log.d("Tag", s);
    URL url = new URL(s);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.connect();
}catch (Exception e)
{
    Log.e("Tag",e.toString());
}
            }
        });
        thread.start();

        final Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Statement st = Global.getInstance().createStatement();
                    ResultSet rs = st.executeQuery("select max(cid) from customer");
                    rs.next();
                    int cid = rs.getInt(1);
                    PreparedStatement ps = Global.getInstance().prepareStatement("insert into customer(cid,username,pass,phone,email) values(?,?,?,?,?)");
                    ps.setInt(1, cid + 1);
                    ps.setString(2, user);
                    ps.setString(3, pass);
                    ps.setString(4, phone);
                    ps.setString(5, email);
                    ps.executeUpdate();
                    PreparedStatement ps1 = Global.getInstance().prepareStatement("insert into cbalance values(?,?)");
                    ps1.setInt(1, cid + 1);
                    ps1.setInt(2, 10000);
                    cid=cid+1;
                    ps1.executeUpdate();
                    Global.write(getApplicationContext(), "" + cid);
                    SignUpConfirm.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Welcome " + user.toString() + " !", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(in);
                        }
                    });


                }catch (final Exception e)
                {
                    Log.e("Tag",e.toString());
                    SignUpConfirm.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Unable to register...! Please try after some time. "+e, Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getBaseContext(), BeforeLogin.class);
                            startActivity(in);
                        }
                    });
                }
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (t.getText().toString().equals("" + i)) {
                    b.setEnabled(false);
                    t2.start();
                } else {
                    Toast.makeText(getBaseContext(), "Invalid Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up_confirm, menu);
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
            return   true;
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