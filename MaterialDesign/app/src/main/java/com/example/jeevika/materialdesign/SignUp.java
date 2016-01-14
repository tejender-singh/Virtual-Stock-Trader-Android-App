package com.example.jeevika.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText t1,t2,t3,t4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        t1= (EditText) findViewById(R.id.etEmail);
        t2= (EditText) findViewById(R.id.etUserName);
        t3= (EditText) findViewById(R.id.etPass);
        t4= (EditText) findViewById(R.id.etphone);
        b1= (Button) findViewById(R.id.signUp1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=t1.getText().toString();
                String user=t2.getText().toString();
                String pass=t3.getText().toString();
                String phone=t4.getText().toString();

                Intent in= new Intent(getBaseContext(),SignUpConfirm.class);
                in.putExtra("email",email);
                in.putExtra("user",user);
                in.putExtra("pass",pass);
                in.putExtra("phone",phone);
                startActivity(in);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
