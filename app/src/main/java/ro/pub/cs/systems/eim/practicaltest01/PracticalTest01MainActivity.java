package ro.pub.cs.systems.eim.practicaltest01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText text1;
    private EditText text2;
    private Button button1;
    private Button button2;
    private Button navigateButton;

    private Button1OnClickListener button1OnClickListener = new Button1OnClickListener();
    private class Button1OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int value = Integer.parseInt(text1.getText().toString());
            String valueString = "" + (value+1);
            text1.setText(valueString);
        }
    }

    private Button2OnClickListener button2OnClickListener = new Button2OnClickListener();
    private class Button2OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int value = Integer.parseInt(text2.getText().toString());
            String valueString = "" + (value+1);
            text2.setText(valueString);
        }
    }

    private NavigateButtonOnClickListener navigateButtonOnClickListener = new NavigateButtonOnClickListener();
    private class NavigateButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int count = Integer.parseInt(text1.getText().toString()) + Integer.parseInt(text2.getText().toString());

            Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01.PracticalTest01SecondaryActivity");
            Log.d("myTag", "Sending count: " + count);
            intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.COUNT", Integer.toString(count));
            startActivityForResult(intent, 888);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 888:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Got Ok.", Toast.LENGTH_LONG).show();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Got Canceled.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String value1 = text1.getText().toString();
        outState.putString("value1", value1);

        String value2 = text2.getText().toString();
        outState.putString("value2", value2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("value1")) {
            String value1 = savedInstanceState.getString("value1");
            text1.setText(value1);
        }

        if (savedInstanceState.containsKey("value2")) {
            String value2 = savedInstanceState.getString("value2");
            text2.setText(value2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        navigateButton = (Button)findViewById(R.id.navigateButton);

        button1.setOnClickListener(button1OnClickListener);
        button2.setOnClickListener(button2OnClickListener);
        navigateButton.setOnClickListener(navigateButtonOnClickListener);
    }
}
