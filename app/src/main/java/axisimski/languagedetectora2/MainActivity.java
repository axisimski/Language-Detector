package axisimski.languagedetectora2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView output;
    static EditText input;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        output=(TextView)findViewById(R.id.output);
        input=(EditText) findViewById(R.id.input);
        play=(Button) findViewById(R.id.detect);

    }
}
