package com.calendar.upay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LauncherActivity extends AppCompatActivity {
    EditText et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        et_id=findViewById(R.id.et_id);
        findViewById(R.id.bt_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LauncherActivity.this,MainActivity.class).putExtra("id",Integer.parseInt(et_id.getText().toString())));
            }
        });

    }
}
