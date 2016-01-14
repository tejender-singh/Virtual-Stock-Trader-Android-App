package activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Lakshay on 8/14/2015.
 */
public class IoHandler2 extends DefaultHandler{
//	private String url = "http://www.indiatvnews.com/rssfeed/india.xml";
	private String url = "http://www.ft.com/rss/home/india";
    private boolean inUrl = false;
    private boolean inTitle = false;
    private boolean inDescription = false;
    private boolean inItem = false;
    private boolean inLink = false;
    private Bitmap image = null;
    private StringBuffer title = new StringBuffer();
    private StringBuffer description = new StringBuffer();
    private StringBuffer link = new StringBuffer();
    ArrayList<Vids> vids=new ArrayList<Vids>();
    public void processFeed() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(this);
            InputStream inputStream = new URL(url).openStream();
            reader.parse(new InputSource(inputStream));
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {

//	System.out.println("Start Element :" + qName);

	if (qName.equalsIgnoreCase("title")) {
		inTitle = true;
	}

	if (qName.equalsIgnoreCase("description")) {
		inDescription = true;
	}

	if (qName.equalsIgnoreCase("link")) {
		inLink = true;
	}


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

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

			if (qName.equalsIgnoreCase("description")) {
				inDescription = false;
			}
//			System.out.println("End Element :" + qName);

			if (qName.equalsIgnoreCase("link")) {
				inLink = false;
			}

			if (qName.equalsIgnoreCase("title")) {
				inTitle = false;
			}
			
			if(qName.equalsIgnoreCase("item"))
			{
				String urlnew;
				inItem=false;
				try {
					urlnew = description.substring((description.indexOf("img src=") + 9), (description.indexOf(".jpg") + 4));
					Log.d("tag",urlnew);
				}catch (Exception e)
				{
					urlnew="";
				}

				vids.add(new Vids(title.toString(),description.toString(),link.toString(),getBitmap(urlnew)));
				description=new StringBuffer();
				link=new StringBuffer();
				title=new StringBuffer();

			}
			
			
		}

		public void characters(char ch[], int start, int length) throws SAXException {

			if (inTitle) {
				title=title.append(new String(ch, start, length));
//				inTitle = false;
			}

			if (inDescription) {
				description=description.append(new String(ch, start, length));
//				inDescription = false;
			}

			if (inLink) {
				link=link.append(new String(ch, start, length));
//				inLink = false;
			}

		}

		public ArrayList<Vids> getVids()
		{
			return vids;
		}
    public StringBuffer getTitle() { return title; }
    public StringBuffer getDescription() { return description; }
    public StringBuffer getLink() { return link; }

}
