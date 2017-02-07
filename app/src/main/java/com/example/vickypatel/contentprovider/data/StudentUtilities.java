package com.example.vickypatel.contentprovider.data;

import android.content.ContentValues;

import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;
import com.example.vickypatel.contentprovider.pojo.Student;

import java.util.ArrayList;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public class StudentUtilities {

    public static ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Vicky", "Patel", "Toronto", "396110"));
        students.add(new Student("Jacky", "Patel", "Toronto", "ASD123"));
        students.add(new Student("Roy", "Patel", "Sarnia", "M6R2H6"));
        return students;
    }

    public static ContentValues createStudentValues(Student student) {
        ContentValues studentValues = new ContentValues();
        studentValues.put(StudentEntry.COLUMN_NAME, student.getName());
        studentValues.put(StudentEntry.COLUMN_SURNAME, student.getSurname());
        studentValues.put(StudentEntry.COLUMN_ADDRESS, student.getAddress());
        studentValues.put(StudentEntry.COLUMN_ZIP_CODE, student.getZipCode());
        return studentValues;
    }

}
