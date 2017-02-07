package com.example.vickypatel.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vickypatel.contentprovider.data.StudentUtilities;
import com.example.vickypatel.contentprovider.pojo.Student;

import java.util.ArrayList;

/**
 * Created by Vicky Patel on 2/7/2017.
 */

public class ListViewCursorAdapter extends CursorAdapter {

    private Context context;
    private ArrayList<Student> studentArrayList;
    private LayoutInflater mInflater;

    public ListViewCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name_textview);
        nameTextView.setText(StudentUtilities.getStudentFromCursor(cursor).getName());
    }
}

