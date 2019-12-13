package com.example.e16_preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView checkbox, ringtone, txtScreen;
    EditText text;
    Button btnOk;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("test", "onCreate");
        checkbox = findViewById(R.id.checkbox);
        ringtone = findViewById(R.id.ringtone);
        txtScreen = findViewById(R.id.screen);
        text = findViewById(R.id.text);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("text", text.getText().toString());
                edit.commit();
                Toast.makeText(getApplicationContext(), "환경설정 값이 변경되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("환경설정").setIntent(new Intent(this, EditPreferences.class));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("test", "onResume");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float font_size = Float.parseFloat(prefs.getString("font", "10"));
        ringtone.setTextSize(TypedValue.COMPLEX_UNIT_SP, font_size);
        checkbox.setTextSize(TypedValue.COMPLEX_UNIT_SP, font_size);
        txtScreen.setTextSize(TypedValue.COMPLEX_UNIT_SP, font_size);
        String color = prefs.getString("font_color", "#ffffff");

        checkbox.setTextColor(Color.parseColor(color));
        ringtone.setTextColor(Color.parseColor(color));
        txtScreen.setTextColor(Color.parseColor(color));

        boolean ch = prefs.getBoolean("checkbox", false);
        checkbox.setText("" + ch);
        boolean screenCheck = prefs.getBoolean("screenOn", false);
        txtScreen.setText("" + screenCheck);
        String ring = prefs.getString("ringtone", "<unset>");
        ringtone.setText(ring);

        if (screenCheck) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        String str = prefs.getString("text", "");
        text.setText(str);
    }
}
