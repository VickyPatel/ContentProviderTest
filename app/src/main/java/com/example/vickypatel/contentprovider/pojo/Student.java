package com.example.vickypatel.contentprovider.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public class Student implements Parcelable {

    private int studentId;
    private String name;
    private String surname;
    private String zipCode;


    protected Student(Parcel in) {
        studentId = in.readInt();
        name = in.readString();
        surname = in.readString();
        zipCode = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(studentId);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(zipCode);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
