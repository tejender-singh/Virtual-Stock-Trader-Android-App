package model;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLHtml {
    public static void main(String[] args) {
        System.out.println(current("NTPC"));
    }
    public static String currentAndChange(String args)
    {
        String url = "http://finance.google.com/finance/info?client=ig&q="+args;
        String output = getHTML(url);
        System.out.println("html output is from next line");
        System.out.println(output);
        int i=0;
        try
        {
            while(i<=4)
            {
                output=output.substring(output.indexOf(":")+1);
                //     System.out.println("in while"+i+output);
                i++;
            }
            String change=output.substring(output.indexOf(",")-2, output.length());
            output=output.substring(2, output.indexOf(",")-2);
            System.out.println("Change1\n"+change);
            change=change.substring(change.indexOf("c_fix")/*, change.indexOf("cp")+12*/);
            change=change.substring(change.indexOf(":")+3, change.indexOf(",")-2);

            System.out.println("Change2\n"+change);



            return(""+Double.parseDouble(output)+";"+Double.parseDouble(change));
        }
        catch(Exception e)
        {e.printStackTrace();
            System.out.println("Please connect to the internet"+output);
            return(null);
        }


    }

    public static String current(String args)
    {
        //String url = "enter your URL here";
        String url = "http://finance.google.com/finance/info?client=ig&q="+args;
        String output = getHTML(url);
        System.out.println("html output is from next line");
        System.out.println(output);
        int i=0;
        try
        {
            while(i<=4)
            {
                output=output.substring(output.indexOf(":")+1);
                System.out.println("in while"+i+output);
                i++;
            }
            output=output.substring(2, output.indexOf(",")-2);
            return(""+Double.parseDouble(output));
        }
        catch(Exception e)
        {e.printStackTrace();
            System.out.println("Please connect to the internet"+output);
            return(null);
        }


    }
    public static String getHTML(String url)
    {   try {
        String result = "";
        int c;
        URL hp = new URL(url);
        URLConnection hpCon = hp.openConnection();
        InputStream input = hpCon.getInputStream();
        while (((c = input.read()) != -1)) {
            //           System.out.print((char) c);
            result = result + (char) c;
        }
        input.close();

        return result;
    }
    catch(Exception e)
    {
//        Log.e("ERROR",e.getMessage());
        e.printStackTrace();
        return null;
    }
    }



}
