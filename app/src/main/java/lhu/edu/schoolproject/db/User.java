package lhu.edu.schoolproject.db;

/**
 * Created by 尚宏 on 2017/5/4.
 */

public class User {
    private String ID;
    private String Name;
    private String Password;
    private String Birthday;
    private String HomeAddress;
    private int Height;
    private int Weight;
    private String HelpName;
    private String HelpPhone;
    private boolean healthWarning;
    private boolean WeatherWarning;
    private boolean MessageNotice;
    private boolean isUpdate;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getHelpName() {
        return HelpName;
    }

    public void setHelpName(String helpName) {
        HelpName = helpName;
    }

    public String getHelpPhone() {
        return HelpPhone;
    }

    public void setHelpPhone(String helpPhone) {
        HelpPhone = helpPhone;
    }

    public boolean isHealthWarning() {
        return healthWarning;
    }

    public void setHealthWarning(boolean healthWarning) {
        this.healthWarning = healthWarning;
    }

    public boolean isWeatherWarning() {
        return WeatherWarning;
    }

    public void setWeatherWarning(boolean weatherWarning) {
        WeatherWarning = weatherWarning;
    }

    public boolean isMessageNotice() {
        return MessageNotice;
    }

    public void setMessageNotice(boolean messageNotice) {
        MessageNotice = messageNotice;
    }
}
