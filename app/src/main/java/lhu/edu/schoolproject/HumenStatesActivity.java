package lhu.edu.schoolproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HumenStatesActivity extends AppCompatActivity {
    ImageView imageView;
    Button input,boby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humen_states);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        imageView = (ImageView) findViewById(R.id.HS_imageView);
        imageView.setImageBitmap(MainActivity.readBitMap(this,R.drawable.main_hs));
        input = (Button) findViewById(R.id.btn_input);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HumenStatesActivity.this, HSInputActivity.class);
                startActivity(intent);
            }
        });
        boby = (Button) findViewById(R.id.btn_body);
        boby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HumenStatesActivity.this, HSBodySelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
