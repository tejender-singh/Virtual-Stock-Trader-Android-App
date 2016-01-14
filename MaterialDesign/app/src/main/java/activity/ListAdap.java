package activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeevika.materialdesign.R;

public class ListAdap extends ArrayAdapter<Vids>{
    
	ArrayList<Vids> vids=new ArrayList<Vids>();
	private final Context context;
	
	public ListAdap(Context context,ArrayList<Vids> vids) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.listadap,vids);
		this.context=context;
		this.vids=vids;
	}

	@Override
	public void add(Vids object) {
		// TODO Auto-generated method stub
		super.add(object);
		super.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View View, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(View==null)
		{
//			LayoutInflater inflater=LayoutInflater.from(parent.getContext());
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View=inflater.inflate(R.layout.listadap	, parent, false);
		}
		
		Vids vid=vids.get(position);
		TextView title=(TextView) View.findViewById(R.id.title1);
		title.setText(vid.gettitle());

		ImageView imagev=(ImageView) View.findViewById(R.id.img);
		imagev.setImageBitmap(vid.getImg());
		return View;

	}

	
    public Bitmap getBitmap(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            return bitmap;
        } catch (Exception ioe) {
        	ioe.printStackTrace();
        	return null; }
    }

}