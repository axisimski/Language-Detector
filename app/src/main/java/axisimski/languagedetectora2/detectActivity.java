package axisimski.languagedetectora2;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    String data = "";
    String Result="n";
    String lc;
    String secondGuess="";
    String lc2;
    int JarrLength=0;
    int worked=1;
    String accuracy;




    @Override
    protected Void doInBackground(Void... voids) {


        String tmp=MainActivity.input.getText().toString();
        String userInput=tmp.replaceAll("\\s+", "+");

        StringBuilder stb= new StringBuilder();
        stb.append("http://apilayer.net/api/detect?access_key=9e0d5359224168d5a9df8ceff4d4e2c3&query="+userInput);


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
            JSONArray Jarr=JO.getJSONArray("results");
            JSONObject JR=Jarr.getJSONObject(0);
            Result=JR.getString("language_name");
            lc=JR.getString("language_code");
            accuracy=JR.getString("probability");

           if( Jarr.length()>1) {
               JarrLength=1;
               JSONObject JSecond = Jarr.getJSONObject(1);
               secondGuess = JSecond.getString("language_name");
               lc2=JSecond.getString("language_code");

           }


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

        if(!Result.equals("n")) {



            if(Double.parseDouble(accuracy.toString())>20){
                MainActivity.output.setText(Result);

                Context context = MainActivity.flag.getContext();
                int id = context.getResources().getIdentifier(lc, "drawable", context.getPackageName());
                if(id!=0) {
                    MainActivity.flag.setImageResource(id);
                }

            }

            else if(Double.parseDouble(accuracy.toString())<20&&JarrLength==1){
                MainActivity.output.setText(Result);
                MainActivity.output2.setText(secondGuess);

                Context context = MainActivity.flag.getContext();
                int id = context.getResources().getIdentifier(lc, "drawable", context.getPackageName());

                if(id!=0) {
                    MainActivity.flag.setImageResource(id);
                }
                Context context2 = MainActivity.flag2.getContext();
                int id2 = context.getResources().getIdentifier(lc2, "drawable", context2.getPackageName());
                MainActivity.flag2.setImageResource(id2);

                MainActivity.output2.setVisibility(View.VISIBLE);
                MainActivity.flag2.setVisibility(View.VISIBLE);

            }

            else {
                MainActivity.output.setText(Result);

                Context context = MainActivity.flag.getContext();
                int id = context.getResources().getIdentifier(lc, "drawable", context.getPackageName());
                if (id != 0) {
                    MainActivity.flag.setImageResource(id);
                }
            }

            MainActivity.input.setText("");


        }

        else
          MainActivity.output.setText("Unknown");


    }


}
