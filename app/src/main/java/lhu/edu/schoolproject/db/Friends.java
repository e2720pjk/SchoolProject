package lhu.edu.schoolproject.db;

/**
 * Created by 尚宏 on 2017/5/4.
 */

public class Friends {
    //重點是身體狀況先不做社群
    private String F_ID;
    private String F_Name;
    private boolean isUpdate;

    public Friends(String f_ID, String f_Name, boolean isUpdate) {
        F_ID = f_ID;
        F_Name = f_Name;
        this.isUpdate = isUpdate;
    }

    public String getF_ID() {
        return F_ID;
    }

    public void setF_ID(String f_ID) {
        F_ID = f_ID;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String f_Name) {
        F_Name = f_Name;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }
}
