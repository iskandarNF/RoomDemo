package tj.iskandar.roomdemo;

import java.util.Collection;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Repo  {

    int id;
    String startDate;
    String endDate;
    String name;
    String icon;
    String objType;
    String loginRequired;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Repo(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(String loginRequired) {
        this.loginRequired = loginRequired;
    }


}