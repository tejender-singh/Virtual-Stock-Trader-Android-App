package com.example.jeevika.materialdesign;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;
import java.sql.SQLException;


import java.sql.PreparedStatement;

import activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    ActionBar ab;
    EditText user,password;
    String login,pass;
    Button b;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ab=getActionBar();

        user=(EditText )findViewById(R.id.UserName);
        password=(EditText)findViewById(R.id.Pass);
        b=(Button)findViewById(R.id.in);

        final Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                login=user.getText().toString();
                pass=password.getText().toString();

                try {

                        PreparedStatement ps=Global.getInstance().prepareStatement("select * from customer where username=? and pass=?");
                        ps.setString(1, login);
                        ps.setString(2, pass);
                        ResultSet rs2=ps.executeQuery();
                        if(rs2.next())
                        {
                            String cid=   rs2.getString("cid");
                            Global.write(getApplicationContext(),cid);
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getBaseContext(),"Welcome "+login.toString()+" !", Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent i=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getBaseContext(), "Invalid Username or Password !", Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent i=new Intent(getApplicationContext(), BeforeLogin.class);
                            startActivity(i);


                        }


                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setEnabled(false);
                t.start();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
