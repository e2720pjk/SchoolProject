package lhu.edu.schoolproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Context context;
    boolean creat =false;
    ImageView BG;
    Button btn_weather,btn_sos,btn_hs,btn_family,btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context =this;
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        findView();
        creatBtnListener();
        BG.setImageBitmap(readBitMap(context,R.drawable.mainbg));

    }
    public void findView(){
        BG = (ImageView) findViewById(R.id.Main_imageView);        ;
        btn_weather = (Button) findViewById(R.id.btn_weather);

        btn_sos = (Button) findViewById(R.id.btn_sos);

        btn_hs = (Button) findViewById(R.id.btn_hs);

        btn_setting = (Button) findViewById(R.id.btn_setting);
//        btn_family = (Button) findViewById(R.id.btn_family);
    }
    public void creatBtnListener(){
        btn_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SOSActivity.class);
                startActivity(intent);
            }
        });
        btn_hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HumenStatesActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onClick(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FamilyActivity.class);
        startActivity(intent);
    }
    public static Bitmap readBitMap(Context context, int resId){
         BitmapFactory.Options opt = new BitmapFactory.Options();
         opt.inPreferredConfig = Bitmap.Config.RGB_565;
         opt.inPurgeable = true;
         opt.inInputShareable = true;
         //獲取資源圖片
         InputStream is = context.getResources().openRawResource(resId);
         return BitmapFactory.decodeStream(is,null,opt);
    }

}
