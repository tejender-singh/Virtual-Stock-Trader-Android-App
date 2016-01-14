package activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jeevika.materialdesign.R;

public class Detail extends Activity {

	ProgressDialog Dialog;
	WebView w;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		w=(WebView) findViewById(R.id.webView1);
		Dialog = ProgressDialog.show(Detail.this,"Loading","Loading Newzzzz");
		Dialog.setCancelable(true);
		Intent i=getIntent();
		s=i.getStringExtra("link");

		w.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Dialog.dismiss();
			}
		});

		myrun r=new myrun();
		Thread t=new Thread(r);
		t.start();
	

	}

	class myrun implements Runnable
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub

		    Detail.this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub

					w.loadUrl(s);
					w.getSettings().setLoadWithOverviewMode(true);
				    w.getSettings().setUseWideViewPort(true);
				}
			});
				
		

		}
}
}