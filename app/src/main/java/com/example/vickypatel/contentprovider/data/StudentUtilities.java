package com.example.vickypatel.contentprovider.data;

import android.content.ContentValues;

import com.example.vickypatel.contentprovider.data.StudentContract.StudentEntry;
import com.example.vickypatel.contentprovider.pojo.Student;

/**
 * Created by Vicky Patel on 2/6/2017.
 */

public class StudentUtilities {

    static ContentValues createStudentValues(Student student) {
        ContentValues studentValues = new ContentValues();
        studentValues.put(StudentEntry.COLUMN_NAME, student.getName());
        studentValues.put(StudentEntry.COLUMN_SURNAME, student.getSurname());
        studentValues.put(StudentEntry.COLUMN_ZIP_CODE, student.getZipCode());
        return studentValues;
    }

}
