package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private Button okButton;
    private Button cancelButton;
    private TextView text;

    private OkButtonOnClickListener okButtonOnClickListener = new OkButtonOnClickListener();
    private class OkButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intentToParent = new Intent();
            intentToParent.putExtra("ro.pub.cs.systems.eim.lab04.status", 1);
            setResult(RESULT_OK, intentToParent);
            finish();
        }
    }

    private CancelButtonOnClickListener cancelButtonOnClickListener = new CancelButtonOnClickListener();
    private class CancelButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(Activity.RESULT_CANCELED, new Intent());
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        okButton = (Button)findViewById(R.id.okButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        text = (TextView)findViewById(R.id.text);

        okButton.setOnClickListener(okButtonOnClickListener);
        cancelButton.setOnClickListener(cancelButtonOnClickListener);

        Intent intentFromParent = getIntent();
        String count = intentFromParent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01.COUNT");

        Log.d("myTag", "This is second count: " + count);

        text.setText(count);
    }
}
