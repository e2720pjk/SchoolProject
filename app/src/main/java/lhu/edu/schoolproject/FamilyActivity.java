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

public class FamilyActivity extends AppCompatActivity {
    Context context;
    ImageView imageView;
    Button familybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        context = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(readBitMap(context,R.drawable.main_family));
        familybtn = (Button) findViewById(R.id.btn_familyChat);
        familybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FamilyActivity.this,FamilyChatActivity.class);
                startActivity(intent);
            }
        });
        creatButton();

    }
    public void creatButton(){
        Button menu_btn_family,menu_btn_friend,menu_btn_chat,menu_btn_news;
        menu_btn_family = (Button) findViewById(R.id.menu_btn_family);
        menu_btn_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        menu_btn_friend = (Button) findViewById(R.id.menu_btn_friend);
        menu_btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FamilyActivity.this,FriendActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu_btn_chat = (Button) findViewById(R.id.menu_btn_chat);
        menu_btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FamilyActivity.this,MainChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu_btn_news = (Button) findViewById(R.id.menu_btn_news);
        menu_btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FamilyActivity.this, NewsActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
