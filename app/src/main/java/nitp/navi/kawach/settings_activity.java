package nitp.navi.kawach;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class settings_activity extends AppCompatActivity implements View.OnClickListener {

    TextView settings_title;
    Button save_btn;
    EditText phn1, phn2, phn3;
    SharedPreferences Pref;
    SharedPreferences.Editor Editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings_title = (TextView) findViewById(R.id.heading_tv);
        //Typeface jackson = Typeface.createFromAsset(getAssets(), "fonts/Jackson-Regular.ttf");
        //settings_title.setTypeface(jackson);
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);
        //Typeface aloha = Typeface.createFromAsset(getAssets(), "fonts/Aloha.otf");
        //save_btn.setTypeface(aloha);
        Pref = getSharedPreferences("bbt", MODE_PRIVATE);
        Editor = Pref.edit();
        phn1 = (EditText) findViewById(R.id.ph1_et);
        phn1.setText(Pref.getString("phn_1", "0"));
        phn2 = (EditText) findViewById(R.id.ph2_et);
        phn2.setText(Pref.getString("phn_2", "0"));
        phn3 = (EditText) findViewById(R.id.ph3_et);
        phn3.setText(Pref.getString("phn_3", "0"));
        Log.e("check", "working");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_btn) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            String num1 = phn1.getText().toString();
            Editor.putString("phn_1", num1);
            Log.e("check", "first : " + Pref.getString("phn_1", "0"));
            String num2 = phn2.getText().toString();
            Editor.putString("phn_2", num2);
            Log.e("check", "second : " + Pref.getString("phn_2", "0"));
            String num3 = phn3.getText().toString();
            Editor.putString("phn_3", num3);
            Log.e("check", "third : " + Pref.getString("phn_3", "0"));
            Editor.commit();
        }
    }
}


