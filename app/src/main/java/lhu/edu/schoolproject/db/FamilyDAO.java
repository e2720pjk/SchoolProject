package lhu.edu.schoolproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 尚宏 on 2017/5/4.
 */

public class FamilyDAO {
    //重點是身體狀況先不做社群
    private Family[] family;
    private SQLiteDatabase db;
    public final static String CREATE_TABLE =
            "CREATE TABLE if not exists" + FamilyDAO.class.getSimpleName() + " ( " +
                    "FY_ID" + " INTEGER PRIMARY KEY, " +
                    "FY_NAME" + " TEXT NOT NULL" +
                    "); ";

    public FamilyDAO(Context context) {
        db =  SchoolDBHelper.getDatabase(context);
        
    }
    public Family[] getFamily(){
        return family;
    }
}
