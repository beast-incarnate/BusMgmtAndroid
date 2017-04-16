package com.example.kunalsingh.busmgmt.models;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kunalsingh on 14/04/17.
 */

public class Student implements Parcelable{

    private String name;
    private String fathers_name;
    private String mothers_name;
    private String address;
    private String contact;
    private String access_token;
    private boolean fees;
    private int total;
    private int present;


    public Student() {
    }

    public Student(Parcel parcel){

        this.name = parcel.readString();
        this.fathers_name = parcel.readString();
        this.mothers_name = parcel.readString();
        this.address = parcel.readString();
        this.contact = parcel.readString();
        this.access_token = parcel.readString();
        this.fees = parcel.readInt() == 1;
        this.total = parcel.readInt();
        this.present = parcel.readInt();

    }



    public String getName() {
        return name;
    }

    public String getFathersName() {
        return fathers_name;
    }

    public String getMothersName() {
        return mothers_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public boolean isFees() {
        return fees;
    }

    public int getTotal() {
        return total;
    }

    public int getPresent() {
        return present;
    }

    public String getAccess_token(){
        return access_token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.fathers_name);
        dest.writeString(this.mothers_name);
        dest.writeString(this.contact);
        dest.writeString(this.access_token);
        dest.writeInt(this.total);
        dest.writeInt(this.present);
        dest.writeInt(this.fees ? 1 : 0);

    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
