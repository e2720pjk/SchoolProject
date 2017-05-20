package lhu.edu.schoolproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_CONTACTS;

public class SettingActivity extends Activity {
    public AutoCompleteTextView editName, editPhone;
    public EditText editHome;
    public Context context;
    public Spinner spinner;
    public Button button;
    private boolean hasSOSHome,hasSOSHelp;
    private String SOSHome,SOSHelp;
    private static final int REQUEST_CONTACTS = 103;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        context = this;
        preferences = this.getSharedPreferences(getResources().getString(R.string.PREF_NAME), MODE_PRIVATE);
        loadSetting();
        editName = (AutoCompleteTextView) findViewById(R.id.editName);
//        editName.setEnabled(false);
        editPhone = (AutoCompleteTextView) findViewById(R.id.editPhone);
        editHome = (EditText) findViewById(R.id.editHome);
//        editPhone.setEnabled(false);
        spinner = (Spinner) findViewById(R.id.spinner2);
//        spinner.setEnabled(false);

        checkPermission();
        spinner = getContent(spinner);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                SOSHelp = editPhone.getText().toString();
                saveHelpSetting(SOSHelp);
                break;
            case R.id.selectButton:
                SOSHome = editHome.getText().toString();
                saveHomeSetting(SOSHome);
//                舊版--------------------
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("選擇聯絡人");
//                builder.setIcon(android.R.drawable.ic_dialog_info);
//                Spinner spinner =new Spinner(context);
//                spinner = getContent(spinner);
//                if(spinner == null){
//                    return;
//                }
//                builder.setMessage("選擇聯絡人");
//                builder.setView(spinner);

//                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.create().show();
                break;
        }
    }


    public void checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(this,
                READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_CONTACTS, WRITE_CONTACTS},
                    REQUEST_CONTACTS);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACTS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //取得聯絡人權限，進行存取
                    spinner = getContent(spinner);
                } else {
                    //使用者拒絕權限，顯示對話框告知
                    new AlertDialog.Builder(this)
                            .setMessage("必須允許聯絡人權限才能顯示資料")
                            .setPositiveButton("OK", null)
                            .show();
                }
                return;
        }
    }

    public Spinner getContent(Spinner spinner) {
        ArrayList<String> sprlist = new ArrayList<String>();
        ArrayList<String> namelist = new ArrayList<String>();
        ArrayList<String> phonelist = new ArrayList<String>();
        sprlist.add("請選擇聯絡人");
        //取得聯絡人資料
        ArrayList<String>[] list = fetchContactInformation(sprlist, namelist, phonelist);
        if(list == null){
            return null;
        }
        sprlist = list[0];
        namelist = list[1];
        phonelist = list[2];
        //設定姓名輸入框
        ArrayAdapter nameAdapter = new ArrayAdapter(this
                , android.R.layout.simple_spinner_item
                , namelist);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editName.setAdapter(nameAdapter);
        editName.setThreshold(1);
        //設定電話輸入框
        ArrayAdapter phoneAdapter = new ArrayAdapter(this
                , android.R.layout.simple_spinner_item
                , phonelist);
        phoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editPhone.setAdapter(phoneAdapter);
        editPhone.setThreshold(1);
        //設定選取聯絡人
        ArrayAdapter sprAdapter = new ArrayAdapter(this
                , android.R.layout.simple_spinner_item
                , sprlist);
        sprAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sprAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String[] obj = ((String) adapterView.getItemAtPosition(i)).split(": Phone=");
                    editName.setText(obj[0]);
                    editPhone.setText(obj[1]);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return spinner;
    }

    //fetchContactInformation用來取得聯絡人資料，回傳重新包好的三個list
    public ArrayList[] fetchContactInformation(ArrayList<String> sprlist
            , ArrayList<String> namelist
            , ArrayList<String> phonelist) {
        String id, name, phoneNumber;
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(android.provider.ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
            name = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));

            //Fetch Phone Number
            Cursor phoneCursor = contentResolver.query(
                    android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, android.provider.ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            while (phoneCursor.moveToNext()) {
                phoneNumber = phoneCursor.getString(
                        phoneCursor.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER));
                sprlist.add(name + ": Phone=" + phoneNumber);
                namelist.add(name);
                phonelist.add(phoneNumber);
            }
            phoneCursor.close();
        }
        cursor.close();
        if(sprlist.size()==0 || namelist.size()==0 || phonelist.size() ==0){
            return null;
        }
        return new ArrayList[]{sprlist, namelist, phonelist};
    }

    public void loadSetting(){
        hasSOSHome = preferences.getBoolean("hasSOSHome", false);
        hasSOSHelp = preferences.getBoolean("hasSOSHelp", false);
        SOSHelp=preferences.getString("SOSHelp",null);
        SOSHome=preferences.getString("SOSHome",null);
    }
    public void saveHelpSetting(String helpStr){

        preferences.edit().putBoolean("hasSOSHelp", true).commit();
        preferences.edit().putString("SOSHelp",helpStr).commit();

        hasSOSHelp = preferences.getBoolean("hasSOSHelp", false);
        SOSHelp=preferences.getString("SOSHelp",null);
        SOSHome=preferences.getString("SOSHome",null);
    }
    public void saveHomeSetting(String homeStr){
        preferences.edit().putBoolean("hasSOSHome", true).commit();
        preferences.edit().putString("SOSHome",homeStr).commit();
        hasSOSHome = preferences.getBoolean("hasSOSHome", false);
        SOSHome=preferences.getString("SOSHome",null);
    }
}

