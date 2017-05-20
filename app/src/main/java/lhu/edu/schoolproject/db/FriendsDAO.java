package lhu.edu.schoolproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 尚宏 on 2017/5/4.
 */

public class FriendsDAO {
    //重點是身體狀況先不做社群
    private static ArrayList<Friends> friendsList;
    private SQLiteDatabase db;
    private static final String TABLE_NAME=Friends.class.getSimpleName();
    private static final String FRIENDS_ID = "FY_ID";
    private static final String FRIENDS_NAME = "FY_NAME";
    public final static String CREATE_TABLE =
            "CREATE TABLE if not exists" + TABLE_NAME + " ( " +
                    FRIENDS_ID + " INTEGER PRIMARY KEY, " +
                    FRIENDS_NAME + " TEXT NOT NULL" +
                    "); ";
    public FriendsDAO(Context context) {
        db =  SchoolDBHelper.getDatabase(context);
        friendsList = readData2CreateFile();
    }
    private ArrayList<Friends> readData2CreateFile(){
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        ArrayList<Friends> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            arrayList.add(getData(cursor));
        }
        cursor.close();
        return arrayList;
    }
    private Friends getData(Cursor cursor){

        return new Friends("","",false);
    }
    public void insert(Friends friends){
        friendsList.add(friends);

        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(FRIENDS_ID, friends.getF_ID());
        cv.put(FRIENDS_NAME, friends.getF_Name());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
//        item.setId(id);
        // 回傳結果
        return ;
    }

}
