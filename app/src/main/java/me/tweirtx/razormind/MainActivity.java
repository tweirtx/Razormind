package me.tweirtx.razormind;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
    public void toastyC(Context context) {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        int reset_count = sp.getInt("reset_count", 0);
        int current_count = sp.getInt("current_count", 0);
        current_count++;
        CharSequence output;
        if (current_count >= reset_count) {
            output = "Change time!";
            current_count = 0;
        }
        else {
            output = "Number of uses: " + current_count;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("current_count", current_count);
        editor.apply();
        Toast toast = Toast.makeText(context, output, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void toasty(View view) {
        toastyC(view.getContext());
    }
}
