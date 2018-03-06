package axisimski.languagedetectora2;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Result;

/**
 * Created by Alex on 3/5/2018.
 */

public class detectActivity extends AsyncTask<Void, Void, Void> {

    String data;
    String Result;


    @Override
    protected Void doInBackground(Void... voids) {


        String tmp=MainActivity.input.getText().toString();
        String userInput=tmp.replaceAll("\\s+", "+");

        StringBuilder stb= new StringBuilder();
        stb.append("http://apilayer.net/api/detect?access_key=9e0d5359224168d5a9df8ceff4d4e2c3&query="+"Hello");


        try{

            URL url = new URL(stb.toString());

            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line="";

            while(line!=null){
                line=bufferedReader.readLine();
                data=data+line;
            }

            JSONObject JO = new JSONObject(data);
            JSONArray JA=JO.getJSONArray("results");
            JSONObject JR=JA.getJSONObject(0);
            Result=JR.getString("language_name");




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.output.setText(Result);

    }


    }
