package activity;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Vids{

	private String title;
	private String description;
	private String imageurl;
	private String link;
	private Bitmap bitmap;
	
public Vids(String title,String description,String link,Bitmap bitmap) {
	// TODO Auto-generated constructor stub
this.title=title;
this.description=description;
this.link=link;
try
{
this.imageurl=description.substring((description.indexOf("src=")+5),(description.indexOf(".jpg")+4 ));
}catch(Exception e)
{
	this.imageurl="";
}
this.bitmap=bitmap;
}


public String gettitle()
{
	return title;
}

public Bitmap getImg()
{
	return this.bitmap;
}

public String getdescription()
{
	return description;
}

public String getlink()
{
	return link;
}

public Bitmap getBitmap(String url)
{
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
