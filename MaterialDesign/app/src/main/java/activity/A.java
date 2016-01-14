package activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeevika.materialdesign.Global;
import com.example.jeevika.materialdesign.R;
import com.software.shell.fab.ActionButton;
//import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
//import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import model.URLHtml;


public class A extends Fragment //implements com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu.MenuStateChangeListener{
{
    ActionButton ab;
   public A() {
        // Required empty public constructor
    }
    int bal=0;
TextView tv,tv1,tv2,tv3,tv4,tv5;
    ProgressBar pb1,pb2,pb3,pb4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.a, container, false);
        ab=(ActionButton)rootView.findViewById(R.id.home_action_button);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateRefresh();
            }
        });

        tv=(TextView)rootView.findViewById(R.id.hometv);
        tv.setText(Calendar.getInstance().getTime().toString().substring(0, 10));
        tv1=(TextView)rootView.findViewById(R.id.hometv2);
        tv2=(TextView)rootView.findViewById(R.id.hometv3);
        tv3=(TextView)rootView.findViewById(R.id.hometv4);
        tv4=(TextView)rootView.findViewById(R.id.hometv5);
        tv5= (TextView) rootView.findViewById(R.id.homebal);
        pb1=(ProgressBar)rootView.findViewById(R.id.homeProgressBar1);
        pb1.setVisibility(ProgressBar.VISIBLE);
        pb2=(ProgressBar)rootView.findViewById(R.id.homeProgressBar2);
        pb2.setVisibility(ProgressBar.VISIBLE);
        pb3=(ProgressBar)rootView.findViewById(R.id.homeProgressBar3);
        pb3.setVisibility(ProgressBar.VISIBLE);
        pb4=(ProgressBar)rootView.findViewById(R.id.homeProgressBar4);
        pb4.setVisibility(ProgressBar.VISIBLE);
        this.hello();
        return rootView;
    }


    public void  hello()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final String  a=URLHtml.currentAndChange("NSE:nifty");
                final String aa[]=a.split(";");
                aa[1]="("+aa[1]+")";

                final String  b=URLHtml.currentAndChange("NSE:niftyjr");
                final String ba[]=b.split(";");
                ba[1]="("+ba[1]+")";

                final String  c=URLHtml.currentAndChange("NSE:banknifty");
                final String ca[]=c.split(";");
                ca[1]="("+ca[1]+")";

                final String  d=URLHtml.currentAndChange("NSE:CNXPSUBANK");
                final String da[]=d.split(";");
                da[1]="("+da[1]+")";
                try {
                    Statement st = Global.getInstance().createStatement();
                    ResultSet rs=st.executeQuery("select * from cbalance where cid=" + Global.read(getContext()));
                    rs.next();
                    bal=rs.getInt(2);

                }catch (Exception e)
                {
                    Log.e("SQL EXcption",e.toString());
                }

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        if(aa[1].charAt(1)=='-')
                            tv1.setText(Html.fromHtml("Nifty : "+aa[0]+" <font color=#B80C09>"+aa[1]));
                        else
                            tv1.setText(Html.fromHtml("Nifty : "+aa[0]+" <font color=#56E39F>"+aa[1]));
                        pb1.setVisibility(ProgressBar.GONE);

                        if(ba[1].charAt(1)=='-')
                            tv2.setText(Html.fromHtml("Nifty Jr. : "+ba[0]+" <font color=#B80C09>"+ba[1]));
                        else
                            tv2.setText(Html.fromHtml("Nifty Jr. : "+ba[0]+" <font color=#56E39F>"+ba[1]));
                        pb2.setVisibility(ProgressBar.GONE);

                        if(ca[1].charAt(1)=='-')
                            tv3.setText(Html.fromHtml("Bank Nifty : "+ca[0]+" <font color=#B80C09>"+ca[1]));
                        else
                            tv3.setText(Html.fromHtml("Bank Nifty : "+ca[0]+" <font color=#56E39F>"+ca[1]));
                        pb3.setVisibility(ProgressBar.GONE);

                        if(da[1].charAt(1)=='-')
                            tv4.setText(Html.fromHtml("PSU Bank Nifty : "+da[0]+" <font color=#B80C09>"+da[1]));
                        else
                            tv4.setText(Html.fromHtml("PSU Bank Nifty : "+da[0]+" <font color=#56E39F>"+da[1]));
                        pb4.setVisibility(ProgressBar.GONE);

                        tv5.setText("Balance : "+bal);


                    }
                });

            }
        });
        t.start();

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */

        // END_INCLUDE (setup_refreshlistener)
    }


    private void initiateRefresh() {

        /**
         * Execute the background task, which uses {@link AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
//        hello();


    }

    private void onRefreshComplete(String arr[]) {
        String[] aa,ba,ca,da;
        aa=new String[2];
        ba=new String[2];
        ca=new String[2];
        da=new String[2];

        aa[0]=arr[0];
        aa[1]=arr[1];
        ba[0]=arr[2];
        ba[1]=arr[3];
        ca[0]=arr[4];
        ca[1]=arr[5];
        da[0]=arr[6];
        da[1]=arr[7];

        if(aa[1].charAt(1)=='-')
            tv1.setText(Html.fromHtml("Nifty : "+aa[0]+" <font color=#B80C09>"+aa[1]));
        else
            tv1.setText(Html.fromHtml("Nifty : "+aa[0]+" <font color=#56E39F>"+aa[1]));
        pb1.setVisibility(ProgressBar.GONE);

        if(ba[1].charAt(1)=='-')
            tv2.setText(Html.fromHtml("Nifty Jr. : "+ba[0]+" <font color=#B80C09>"+ba[1]));
        else
            tv2.setText(Html.fromHtml("Nifty Jr. : "+ba[0]+" <font color=#56E39F>"+ba[1]));
        pb2.setVisibility(ProgressBar.GONE);

        if(ca[1].charAt(1)=='-')
            tv3.setText(Html.fromHtml("Bank Nifty : "+ca[0]+" <font color=#B80C09>"+ca[1]));
        else
            tv3.setText(Html.fromHtml("Bank Nifty : "+ca[0]+" <font color=#56E39F>"+ca[1]));
        pb3.setVisibility(ProgressBar.GONE);

        if(da[1].charAt(1)=='-')
            tv4.setText(Html.fromHtml("PSU Bank Nifty : "+da[0]+" <font color=#B80C09>"+da[1]));
        else
            tv4.setText(Html.fromHtml("PSU Bank Nifty : "+da[0]+" <font color=#56E39F>"+da[1]));
        pb4.setVisibility(ProgressBar.GONE);


        // Stop the refreshing indicator
    }


    public void refresh(View view)
    {
initiateRefresh();
    }



    private class DummyBackgroundTask extends AsyncTask<Void, Void, String[]> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds



        @Override
        protected void onPreExecute() {
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
            tv4.setText("");
            pb1.setVisibility(ProgressBar.VISIBLE);
            pb2.setVisibility(ProgressBar.VISIBLE);
            pb3.setVisibility(ProgressBar.VISIBLE);
            pb4.setVisibility(ProgressBar.VISIBLE);

            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task


            final String  a=URLHtml.currentAndChange("NSE:nifty");
            final String aa[]=a.split(";");
            aa[1]="("+aa[1]+")";

            final String  b=URLHtml.currentAndChange("NSE:niftyjr");
            final String ba[]=b.split(";");
            ba[1]="("+ba[1]+")";

            final String  c=URLHtml.currentAndChange("NSE:banknifty");
            final String ca[]=c.split(";");
            ca[1]="("+ca[1]+")";

            final String  d=URLHtml.currentAndChange("NSE:CNXPSUBANK");
            final String da[]=d.split(";");
            da[1]="("+da[1]+")";

            // Return a new random list of cheeses
            String arr[]=new String[8];
            arr[0]=aa[0];
            arr[1]=aa[1];
            arr[2]=ba[0];
            arr[3]=ba[1];
            arr[4]=ca[0];
            arr[5]=ca[1];
            arr[6]=da[0];
            arr[7]=da[1];
            return arr;
        }

        @Override
        protected void onPostExecute(String arr[]) {
            super.onPostExecute(arr);
//            pb1.setVisibility(ProgressBar.GONE);
//            pb2.setVisibility(ProgressBar.GONE);
//            pb3.setVisibility(ProgressBar.GONE);
//            pb4.setVisibility(ProgressBar.GONE);
            // Tell the Fragment that the refresh has completed
            onRefreshComplete(arr);
        }

    }










    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}