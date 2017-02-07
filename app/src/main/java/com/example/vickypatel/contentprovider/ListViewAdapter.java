package com.example.vickypatel.contentprovider;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vickypatel.contentprovider.pojo.Student;

import java.util.ArrayList;

/**
 * Created by Vicky Patel on 2/7/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> studentArrayList;
    private LayoutInflater mInflater;

    public ListViewAdapter(Context context, ArrayList<Student> studentArrayList){
        this.context = context;
        this.studentArrayList = studentArrayList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.single_list_item, parent, false);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.name_textview);
        nameTextView.setText(studentArrayList.get(position).getName());
        return rowView;
    }
}
