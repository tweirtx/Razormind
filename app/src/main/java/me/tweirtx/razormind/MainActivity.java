package me.tweirtx.razormind;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatTextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        if (!sp.contains("reset_count")) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("reset_count", 0);
            editor.putInt("current_count", 0);
            editor.apply();
        }
        int reset_count = sp.getInt("reset_count", 0);
        int current_count = sp.getInt("current_count", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText numReset = (EditText) findViewById(R.id.num);
        numReset.setText(String.valueOf(reset_count));
        AppCompatTextView numCurrent = findViewById(R.id.textView3);
        numCurrent.setText("Current count: " + current_count);
    }

    public void save(View view) {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        EditText numReset = (EditText) findViewById(R.id.num);
        editor.putInt("reset_count", Integer.parseInt(numReset.getText().toString()));
        editor.apply();
    }
}
