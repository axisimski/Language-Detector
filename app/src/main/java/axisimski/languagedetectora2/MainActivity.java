package axisimski.languagedetectora2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView output;
    static EditText input;
    static ImageView flag;
    static ImageView flag2;
    static TextView output2;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        output=(TextView)findViewById(R.id.output);
        input=(EditText) findViewById(R.id.input);
        play=(Button) findViewById(R.id.detect);
        flag=(ImageView)findViewById(R.id.flag);
        flag2=(ImageView)findViewById(R.id.flag2);
        output2=(TextView)findViewById(R.id.output2);

        flag2.setVisibility(View.INVISIBLE);
        output2.setVisibility(View.INVISIBLE);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectLanguage();
            }
        });

    }


    public void detectLanguage(){

        flag2.setVisibility(View.INVISIBLE);
        output2.setVisibility(View.INVISIBLE);

        detectActivity a = new detectActivity();
        a.execute();

        ((EditText) findViewById(R.id.input)).setText("");
    }

}
