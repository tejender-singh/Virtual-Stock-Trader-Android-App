package activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jeevika.materialdesign.R;
import com.example.jeevika.materialdesign.Trade;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    CharSequence items[]={"Hangouts","Whatsapp","Gmail"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle("Virtua Stock");
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        DrawerRight df1=(DrawerRight)getSupportFragmentManager().findFragmentById(R.id.fragmentright);
        df1.setup(R.id.fragmentright,(DrawerLayout)findViewById(R.id.drawer_layout), mToolbar);


        // display the first navigation drawer view on app launch
        displayView(0);
        startService(new Intent(getBaseContext(), MyService.class));
    }
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = "Virtua Stock";
        switch (position) {
            case 0:
                fragment = new A();
                title = "HOME";
                break;
            case 1:
                fragment = new B();
                title ="PORTFOLIO";
                break;
            case 2:
                fragment = new C();
                title = "LEARN";
                break;

            case 3:
                fragment = new Trade();
                title = "TRADE" ; break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem mnu1 = menu.add(0, 0, 0, "Share");
        {
            //mnu3.setIcon(R.drawable.images);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case 0:
                showDialog(0);

        }

        return super.onOptionsItemSelected(item);
    }
    public Dialog onCreateDialog(int id)
    {

        switch(id)
        {

            case 0:AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setIcon(R.drawable.images);
                //builder1.setMessage("Select Company");
                builder1.setTitle("Share Using...");
                int checkedItem=-1;
                builder1.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which] == "Hangouts") {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody = "Hey There!I am using AudioRec. You should try it too.Click here";
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            sharingIntent.setPackage("com.google.android.talk");
                            startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        } else if (items[which] == "Whatsapp") {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody = "Hey There!I am using AudioRec. You should try it too.Click here";
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            sharingIntent.setPackage("com.whatsapp");
                            startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        } else if (items[which] == "Gmail") {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody = "Hey There!I am using AudioRec. You should try it too.Click here";
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            sharingIntent.setPackage("com.google.android.gm");
                            startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        } else
                            Toast.makeText(getApplicationContext(), "Wrong Choice", Toast.LENGTH_LONG).show();
                    }
                });

                return builder1.create();

            default: break;
        }
        return super.onCreateDialog(id);
    }
    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }


}
