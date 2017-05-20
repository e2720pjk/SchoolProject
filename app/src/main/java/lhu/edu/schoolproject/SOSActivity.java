package lhu.edu.schoolproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SOSActivity extends AppCompatActivity {
    ImageView imageView;
    private boolean hasSOSHome,hasSOSHelp;
    private String SOSHome,SOSHelp;
    private TextView Helptext,Hometext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
//        setTitle();
        getSupportActionBar().hide();
//        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        imageView = (ImageView) findViewById(R.id.SOSimageView);
//        imageView.setImageBitmap(MainActivity.readBitMap(this,R.drawable.main_sos));
        Helptext = (TextView) findViewById(R.id.phoneTextView);
        Hometext = (TextView) findViewById(R.id.homeTextView);
        loadSetting();
        if(hasSOSHome){
            Hometext.setText(SOSHome);
        }else {
            Hometext.setText("未設定");
        }
        if(hasSOSHelp){
            Helptext.setText(SOSHome);
        }else {
            Hometext.setText("未設定");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadSetting();
        if(hasSOSHome){
            Hometext.setText(SOSHome);
        }else {
            Hometext.setText("未設定");
        }
        if(hasSOSHelp){
            Helptext.setText(SOSHome);
        }else {
            Hometext.setText("未設定");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();loadSetting();
        if(hasSOSHome){
            Hometext.setText(SOSHome);
        }else {
            Hometext.setText("未設定");
        }
        if(hasSOSHelp){
            Helptext.setText(SOSHelp);
        }else {
            Helptext.setText("未設定");
        }

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Homebutton:
                if(hasSOSHome){
                    Hometext.setText(SOSHome);
//                    Uri uri = Uri.parse("geo:38.899533,-77.036476");
//                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.setClass(this,SettingActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.Phonebutton:
                if(hasSOSHelp){
                    Helptext.setText(SOSHelp);
                    Uri uri = Uri.parse("tel:" + SOSHelp);
                    Intent i = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(i);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(this,SettingActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    public void loadSetting(){
        SharedPreferences preferences = this.getSharedPreferences(getResources().getString(R.string.PREF_NAME), MODE_PRIVATE);
        hasSOSHome = preferences.getBoolean("hasSOSHome", false);
        hasSOSHelp = preferences.getBoolean("hasSOSHelp", false);
        SOSHelp=preferences.getString("SOSHelp",null);
        SOSHome=preferences.getString("SOSHome",null);
    }
}
