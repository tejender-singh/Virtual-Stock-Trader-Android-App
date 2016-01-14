package activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jeevika.materialdesign.Global;
import com.example.jeevika.materialdesign.PortfolioAdapter;
import com.example.jeevika.materialdesign.PortfolioData;
import com.example.jeevika.materialdesign.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.URLHtml;

public class B extends Fragment {
 public   ProgressBar pb1;
public PortfolioAdapter adapter;
    public RecyclerView recyclerView;
    public B() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.b, container, false);
        pb1=(ProgressBar)rootView.findViewById(R.id.portfolioProgressBar);
        pb1.setVisibility(ProgressBar.VISIBLE);

        recyclerView= (RecyclerView) rootView.findViewById(R.id.recyclerview);
        BAsyncTask ab=new BAsyncTask();
        ab.execute();


        return rootView;
    }

//    public static List<PortfolioData> getData(){
//        List<PortfolioData> data= new ArrayList<>();
//        String stock="NTPC";
//        int nos=100;
//        double avgprice=150.0;
//        double currentprice=180.0;
//        double profit=currentprice-avgprice;
//        double profitp=(profit/avgprice)*100;
//        PortfolioData item1=new PortfolioData(stock,nos,avgprice,currentprice,profit,profitp);
//        data.add(item1);
//        stock="JUNIORBEES";
//        nos=200;
//        avgprice=100.0;
//        currentprice=50.0;
//        profit=currentprice-avgprice;
//        profitp=(profit/avgprice)*100;
//        item1=new PortfolioData(stock,nos,avgprice,currentprice,profit,profitp);
//        data.add(item1);
//        return data;
//    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    class BAsyncTask extends AsyncTask<Void,Void,List<PortfolioData>>
    {


        @Override
        protected List<PortfolioData> doInBackground(Void... voids) {
            List<PortfolioData> da = new ArrayList<>();
            Connection conn=Global.conn;
            try {
                Statement st=conn.createStatement();
                String cid= Global.read(getActivity());
                ResultSet rs=st.executeQuery("select * from holdings where cid=" + cid);
                double totalhold=0.0;
                double totalcurrent=0.0;
                while(rs.next())
                {
                 String stock=rs.getString(2).toUpperCase();
                    Log.e("TEJ",stock);
                    int nos=rs.getInt(3);
                    Log.e("TEJ",nos+"");
                    double avgprice=rs.getDouble(4);
                    Log.e("TEJ",avgprice+"");
                    String aprice=URLHtml.current("NSE:"+stock);
                    Log.e("TEJ", aprice + "");
                    double currentprice= Double.parseDouble(aprice);
                    double profit=(currentprice-avgprice)*nos;
                    String sprofit=String.format("%.2f", profit);
                    profit=Double.parseDouble(sprofit);
                    double profitp=profit/avgprice*100/nos;
                    String sprofitp=String.format("%.2f",profitp);
                    profitp=Double.parseDouble(sprofitp);
                    PortfolioData item= new PortfolioData(stock,nos,avgprice,currentprice,profit,profitp);
                    da.add(item);
                    totalhold+=(avgprice*nos);
                    totalcurrent+=(currentprice*nos);

                }
                double totalprofit= Double.parseDouble(String.format("%.2f",(totalcurrent-totalhold)));
                double totalprofitp= Double.parseDouble(String.format("%.2f",((totalcurrent-totalhold)/totalhold*100)));
                PortfolioData i= new PortfolioData("TOTAL",0,totalhold,totalcurrent,totalprofit,totalprofitp);
                da.add(i);


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return da;
        }

        @Override
        protected void onPostExecute(List<PortfolioData> portfolioDatas) {
            super.onPostExecute(portfolioDatas);

            adapter=new PortfolioAdapter(getActivity(),portfolioDatas);
            pb1.setVisibility(ProgressBar.GONE);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
    }



}
