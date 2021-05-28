package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    Context ctx;
    int layoutItem;
    ArrayList<User> arrayList;

    public ListViewAdapter(Context ctx, int layoutItem, ArrayList<User> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        //truyền vào 3 tham số, view này chính là layout item


        view = LayoutInflater.from(ctx).inflate(layoutItem, parent, false);


        //->go on : ánh xạ view của layout này qua code
        TextView tvId = view.findViewById(R.id.tv_Id);
        TextView tvName = view.findViewById(R.id.tv_Name);
        TextView tv_Age = view.findViewById(R.id.tv_Age);

        // gán giá trị
        tvId.setText(String.valueOf(arrayList.get(position).getId()));
        tvName.setText(arrayList.get(position).getName());
        tv_Age.setText(String.valueOf(arrayList.get(position).getAge()));


        return view;
    }
}
