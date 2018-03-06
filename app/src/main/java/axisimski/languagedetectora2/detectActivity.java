package axisimski.languagedetectora2;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alex on 3/5/2018.
 */

public class detectActivity extends AsyncTask<Void, Void, Void> {

    String inp;


    @Override
    protected Void doInBackground(Void... voids) {


        String tmp=MainActivity.output.getText().toString();
        String userInput=tmp.replaceAll("\\s+", "+");

        StringBuilder stb= new StringBuilder();
        stb.append("http://apilayer.net/api/detect?access_key=9e0d5359224168d5a9df8ceff4d4e2c3&query="+userInput);


        try{

            URL url = new URL(stb.toString());

            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
