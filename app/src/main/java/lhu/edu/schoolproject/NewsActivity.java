package lhu.edu.schoolproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(MainActivity.readBitMap(this,R.drawable.main_news));
        creatButton();
    }
    public void creatButton(){
        Button menu_btn_family,menu_btn_friend,menu_btn_chat,menu_btn_news;
        menu_btn_family = (Button) findViewById(R.id.menu_btn_family);
        menu_btn_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,FamilyActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu_btn_friend = (Button) findViewById(R.id.menu_btn_friend);
        menu_btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,FriendActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu_btn_chat = (Button) findViewById(R.id.menu_btn_chat);
        menu_btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,MainChatActivity.class);
                startActivity(intent);
            }
        });
        menu_btn_news = (Button) findViewById(R.id.menu_btn_news);
        menu_btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            }
        });
    }
}
