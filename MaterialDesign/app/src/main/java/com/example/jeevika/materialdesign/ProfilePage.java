package com.example.jeevika.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import activity.FragmentDrawer;
import activity.MainActivity;

public class ProfilePage extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    Button save,logout;
    String oldpass;
    ImageButton prof;
    EditText ed,ed2,ed5,ed6;
    String user,pass,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        ed=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed5=(EditText)findViewById(R.id.editText5);
        ed6=(EditText)findViewById(R.id.editText6);
        save=(Button)findViewById(R.id.saveButton);
        logout= (Button) findViewById(R.id.LogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Global.write(getApplicationContext(),"-1");
                Intent in= new Intent(getApplicationContext(),BeforeLogin.class);
                startActivity(in);
            }
        });
        prof=(ImageButton)findViewById(R.id.imgprof);
        final Thread t=new Thread(new Runnable() {

            public void run() {
                try {
                    String userCid=Global.read(getApplicationContext());
                    Statement st = Global.conn.createStatement();
                    ResultSet rs = st.executeQuery("select username, phone, pass from customer where cid="+userCid);
                    rs.next();

                    user=rs.getString("username");
                    phone=rs.getString("phone");
                    oldpass=rs.getString("pass");
                    ProfilePage.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ed.setText(user);
                            ed6.setText(phone);
                        }
                    });
                    Log.d("tag.........passwordd", "" + oldpass+"...");

                } catch (Exception e) {
                    Log.e("Oldddd passworddd",e.toString());
                    e.printStackTrace();
                }


            }
        });t.start();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            String userCid1 = Global.read(getApplicationContext());
                            pass = ed5.getText().toString();
                            Log.d("tag.........user id",""+userCid1);
                            Log.d("tag.........old pass...",""+pass);
                            if(pass.equals(""))
                            {
                                pass=oldpass;
                            }
                            if (oldpass.equalsIgnoreCase(ed2.getText().toString())) {
                                PreparedStatement ps = Global.conn.prepareStatement("update customer set pass=?,phone=? where cid=? ");
                                ps.setString(1, pass);
                                ps.setString(2, ed6.getText().toString());
                                ps.setString(3, userCid1);
                                ps.executeUpdate();
                                ProfilePage.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();
                                    }
                                });
                                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(in);
                                saveImage(getApplicationContext(), BitmapFactory.decodeFile(imgDecodableString), "Home", "jpg");

                            } else {
                                ProfilePage.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "NOT SAVED", Toast.LENGTH_LONG).show();
                                    }
                                });
                                saveImage(getApplicationContext(), BitmapFactory.decodeFile(imgDecodableString), "Home", "jpg");

                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Log.e("tag", e.toString());
                        }
                    }
                });
                t.start();

            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String
                SharedPreferences prefs=getApplicationContext().getSharedPreferences("VStockPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("image",imgDecodableString);
                editor.apply();


                prof.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error",e.toString());
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
        saveImage(getApplicationContext(),BitmapFactory.decodeFile(imgDecodableString),"Home","jpg");
    }

    public void saveImage(Context context, Bitmap b,String name,String extension){
        name=name+"."+extension;
        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (Exception e) {
            Log.e("saveimage",e.toString());

            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_page, menu);
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
