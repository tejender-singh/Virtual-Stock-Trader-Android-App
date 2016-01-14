package myhelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.jeevika.materialdesign.BeforeLogin;
import com.example.jeevika.materialdesign.R;
import com.software.shell.fab.ActionButton;


public class Help_6 extends Fragment {

    ActionButton ab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_help_6, container, false);
        ab= (ActionButton)rootView.findViewById(R.id.action_button_help);
        YoYo.with(Techniques.Landing).duration(3000).playOn(ab);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity().getApplicationContext(),BeforeLogin.class);
                startActivity(in);
            }
        });

        return rootView;
//        return inflater.inflate(R.layout.fragment_help_6, container, false);

    }


}
