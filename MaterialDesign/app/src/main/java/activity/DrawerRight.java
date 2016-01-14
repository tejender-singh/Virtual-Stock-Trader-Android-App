package activity;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.jeevika.materialdesign.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerRight extends android.support.v4.app.Fragment {

    private ActionBarDrawerToggle mdrawertoggle;
    private DrawerLayout mdrawerlayout;

    ImageButton b1,b2,b3,b4;

    public DrawerRight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_drawer_right, container, false);

    }


    public void setup(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar)
    {

        b1=(ImageButton)getActivity().findViewById(R.id.imageButton1);
        b2=(ImageButton)getActivity().findViewById(R.id.imageButton2);
        b3=(ImageButton)getActivity().findViewById(R.id.imageButton3);
        b4=(ImageButton)getActivity().findViewById(R.id.imageButton4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getApplicationContext(),Newz3.class);
                startActivity(in);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getBaseContext(),Newz1.class);
                startActivity(in);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getBaseContext(),Newz2.class);
                startActivity(in);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity().getBaseContext(),Newz.class);
                startActivity(in);
            }
        });


        mdrawerlayout=drawerLayout;
        mdrawertoggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.draweropen,R.string.drawerclose){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mdrawerlayout.setDrawerListener(mdrawertoggle);
        mdrawerlayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawertoggle.syncState();
            }
        });
    }


}
