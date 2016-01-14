package activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.jeevika.materialdesign.Global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.URLHtml;


public class MyService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        //-------------------------------------------------------------------------------------------------------------------------
//        try {
//            c = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/virtuastock", "virtuastock", "virtuastock");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch(Exception e){
//            e.printStackTrace();
//        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection c = Global.getInstance();



                int j=0;
                while (true) {

                    Log.e("SERVICE", c + " " + j++);

                    try {
                        ////////////////////////////////////////////////////////////////////////////////////////////////////

                        Statement s4 = Global.getInstance().createStatement();
                        String i = Global.read(getApplicationContext());
                        Log.e("SERVICE", "in try " + i);

                        String g = "select * from tobuy where status='pending' and username=" + Global.read(getApplicationContext());
                        ResultSet rs2 = s4.executeQuery(g);
                        while (rs2.next()) {
                            Log.e("SERVICE", "in while " + rs2.getString(1));

                            double bid = rs2.getDouble(3);
                            double mp = Double.parseDouble(URLHtml.current("NSE:" + rs2.getString(1)));
                            if (bid >= mp) {


                                Log.e("SERV", "bid =" + bid + " mp= " + mp);
                                Statement s5 = Global.getInstance().createStatement();
                                String s5s = "select count(tid) from buy";
                                ResultSet rs5 = s5.executeQuery(s5s);
                                int tid = 1;
                                if (rs5.next()) {
                                    tid = rs5.getInt(1) + 1;
                                }
                                Statement s6 = Global.getInstance().createStatement();
                                String s6s = "insert into buy values(" + tid + "," + rs2.getInt(2) + ",'" + rs2.getString(1) + "'," + rs2.getInt(4) + ",'2015-02-10'," + rs2.getDouble(3) + ")";
                                s6.executeUpdate(s6s);


                                Statement s8 = Global.getInstance().createStatement();
                                String s8s = "select * from holdings where sid='" + rs2.getString(1) + "' and cid=" + rs2.getInt(2);
                                ResultSet rs8 = s8.executeQuery(s8s);
                                int newnos = rs2.getInt(4);
                                double newavgprice = rs2.getDouble(3);
                                if (rs8.next())

                                {
                                    Log.e("SERV", "IN REFRESH DB IN IF");
                                    int inos = rs8.getInt(3);   //initial no of stocks
                                    double avgprice = rs8.getDouble(4);
                                    double itprice = inos * avgprice; //initial total price
                                    int anos = rs2.getInt(4);       //added no of stocks
                                    double price = rs2.getDouble(3);    //new price per stock
                                    newnos = inos + anos;
                                    newavgprice = (itprice + (price * anos)) / newnos;
                                    Log.e("SERV", "new avg price" + newavgprice);
                                    Statement s9 = Global.getInstance().createStatement();
                                    String s9s = "update holdings set nos=" + newnos + " , avgprice=" + newavgprice + " where sid='" + rs2.getString(1) + "' and cid=" + rs2.getInt(2) + "";
                                    s9.executeUpdate(s9s);
                                } else {
                                    Log.e("SERV", "IN REFRESH DB IN ELSE");
                                    Statement s9 = Global.getInstance().createStatement();
                                    String s9s = "insert into holdings values(" + rs2.getInt(2) + ",'" + rs2.getString(1) + "'," + newnos + "," + newavgprice + ")";
                                    s9.executeUpdate(s9s);

                                }


                                Statement s7 = Global.getInstance().createStatement();
                                String s7s = "update tobuy set status='successfull' where username=" + rs2.getInt(2) + " and stockname='" + rs2.getString(1) + "' and price=" + bid;
                                s7.executeUpdate(s7s);


                                Statement st13 = Global.getInstance().createStatement();
                                String s13 = "select balance from cbalance where cid=" + rs2.getInt(2);
                                ResultSet rs3 = st13.executeQuery(s13);
                                double balance = 0;
                                if (rs3.next()) {
                                    balance = rs3.getDouble(1);
                                }
                                balance = balance - (rs2.getDouble(3) * rs2.getInt(4));

                                Statement st11 = Global.getInstance().createStatement();
                                String s11 = "update cbalance set balance=" + balance + " where cid=" + rs2.getInt(2);
                                st11.executeUpdate(s11);


                            }


                        }

                            Log.e("SERVICE","HIIIIIIIIIIIIIIIIIII");
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        Statement s8 = Global.getInstance().createStatement();
                        String g2 = "select * from tosell where status='pending'and username=" + Global.read(getApplicationContext());
                        ResultSet rs8 = s4.executeQuery(g2);
                        while (rs8.next()) {
                            double bid = rs8.getDouble(3);
                            double mp = Double.parseDouble(URLHtml.current(rs8.getString(1)));
                            if (bid <= mp) {
                                Statement s5 = Global.getInstance().createStatement();
                                String s5s = "select count(tid) from sell";
                                ResultSet rs5 = s5.executeQuery(s5s);
                                int tid = 1;
                                if (rs5.next()) {
                                    tid = rs5.getInt(1) + 1;
                                }
                                Statement s16 = Global.getInstance().createStatement();
                                String s16s = "insert into sell values(" + tid + "," + rs8.getInt(2) + ",'" + rs8.getString(1) + "'," + rs8.getInt(4) + ",'2015-02-10'," + rs8.getDouble(3) + ")";
                                s16.executeUpdate(s16s);


                                Statement s9 = Global.getInstance().createStatement();
                                String s9s = "select * from holdings where sid='" + rs8.getString(1) + "' and cid=" + rs8.getInt(2);
                                ResultSet rs9 = s9.executeQuery(s9s);
                                if (rs9.next()) {
                                    int n = rs9.getInt(3);
                                    Log.e("SERV", n + "");
                                    Log.e("SERV", (n - rs8.getInt(4)) + "");
                                    Statement s10 = Global.getInstance().createStatement();
                                    int x = n - rs8.getInt(4);
                                    double iprice = rs9.getDouble(4) * rs9.getInt(3);
                                    double nprice = iprice - (rs8.getDouble(3) * rs8.getInt(4));
                                    nprice = nprice / x;
                                    String s10s;
                                    if (x > 0) {
                                        s10s = "update holdings set nos=" + x + ", avgprice=" + nprice + " where sid='" + rs8.getString(1) + "' and cid=" + rs8.getInt(2);
                                    } else {
                                        s10s = "delete from holdings where sid='" + rs8.getString(1) + "' and cid=" + rs8.getInt(2);
                                    }
                                    s10.executeUpdate(s10s);

                                }

                                Statement st13 = Global.getInstance().createStatement();
                                String s13 = "select balance from cbalance where cid=" + rs8.getInt(2);
                                ResultSet rs3 = st13.executeQuery(s13);
                                double balance = 0;
                                if (rs3.next()) {
                                    balance = rs3.getDouble(1);
                                }


                                Statement st2 = Global.getInstance().createStatement();

                                balance = balance + (rs8.getDouble(3) * rs8.getInt(4));
                                String ss2 = "update cbalance set balance=" + balance + " where cid=" + rs8.getInt(2);
                                st2.executeUpdate(ss2);


                                Statement s7 = Global.getInstance().createStatement();
                                String s7s = "update tosell set status='successfull' where username=" + rs8.getInt(2) + " and stockname='" + rs8.getString(1) + "' and price =" + bid;
                                s7.executeUpdate(s7s);
                            }
                        }
//                        c.commit();
                        Thread.sleep(5000);

                    }catch(SQLException e){
                        e.printStackTrace();
                        Log.e("SERVICE", "Breaking from sql");
                        Log.e("SERVICE", e.getMessage());

                        break;
                    }catch(Exception e){

                        Log.e("SERVICE", e.getMessage());
                        Log.e("SERVICE", "Breaking from outside");

                        e.printStackTrace();
                        break;
                    }

Log.e("SERVICE","BYEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                }






            }
        });
        t.start();
   //--------------------------------------------------------------------------------------------------------------------------
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}