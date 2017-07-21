package io.github.ryanhoo.music.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : zhangzhongqiang@jsdttec.com
 * Time   : 2017/07/19 下午 2:44
 * Desc   : description
 */

public class PersonalSelect implements Parcelable {

    private String startNumStr;
    private String endNumStr;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.startNumStr);
        dest.writeString(this.endNumStr);
    }

    public PersonalSelect() {

    }

    public PersonalSelect(String startNumStr, String endNumStr) {
        this.startNumStr = startNumStr;
        this.endNumStr = endNumStr;
    }

    public String getStartNumStr() {
        return startNumStr;
    }

    public void setStartNumStr(String startNumStr) {
        this.startNumStr = startNumStr;
    }

    public String getEndNumStr() {
        return endNumStr;
    }

    public void setEndNumStr(String endNumStr) {
        this.endNumStr = endNumStr;
    }

    protected PersonalSelect(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.startNumStr = in.readString();
        this.endNumStr = in.readString();
    }

    public static final Creator<PersonalSelect> CREATOR = new Creator<PersonalSelect>() {
        @Override
        public PersonalSelect createFromParcel(Parcel source) {
            return new PersonalSelect(source);
        }

        @Override
        public PersonalSelect[] newArray(int size) {
            return new PersonalSelect[size];
        }
    };
}
