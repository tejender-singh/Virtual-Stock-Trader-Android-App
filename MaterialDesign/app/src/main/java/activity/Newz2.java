package activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeevika.materialdesign.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Newz2 extends Activity {
	
	ListView lv;
	ProgressDialog dialog;
	ArrayList<Vids> vids=new ArrayList<Vids>();
	ListAdap1 adap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss);
		dialog = ProgressDialog.show(this,"Loading","Loading Today's Videos");
		lv=(ListView) findViewById(R.id.lv);
		myrun r=new myrun();
		Thread t=new Thread(r);
		t.start();

	
	}
	

	class myrun implements Runnable
	{
		ArrayList<Vids> naya;
		@Override
		public void run() {
			// TODO Auto-generated method stub

			final IoHandler2 handler=new IoHandler2();
	        handler.processFeed();
	        naya=handler.getVids();
	        
		    Newz2.this.runOnUiThread(new Runnable() {
				
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try
							{
								adap=new ListAdap1(Newz2.this, vids);
								lv.setAdapter(adap);
								lv.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int position, long id) {
										// TODO Auto-generated method stub
										Vids v=new Vids("", "", "", null);
										Iterator<Vids> i=naya.iterator();
										for(int j=0;j<=position;j++)
										{
										v=(Vids)i.next();
										}
										Intent in=new Intent(Newz2.this,Detail.class);
										in.putExtra("link", v.getlink());
										Toast.makeText(Newz2.this, v.gettitle(),Toast.LENGTH_LONG).show();
										startActivity(in);
									}
								});
								
								
								Iterator<Vids> i=naya.iterator();
								while(i.hasNext())
								{
									Vids v=(Vids)i.next();
									adap.add(new Vids(v.gettitle(),v.getdescription(),v.getlink(),v.getImg()));
									adap.notifyDataSetChanged();
								}

								
								dialog.dismiss();
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					});
		
	        
	        
		}
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

