package cn.lei.verticalviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_01:
                intent = new Intent(this, VerticalActivity01.class);
                break;
            case R.id.btn_02:
                intent = new Intent(this, VerticalActivity02.class);
                break;
        }
        startActivity(intent);
    }
}
