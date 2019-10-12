package com.list.mysqlexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ItemAdapter extends BaseAdapter
{

    private final ArrayList<String[]> data;
    LayoutInflater minflater;
    public ItemAdapter(Context c, ArrayList<String []> data)
    {


        System.out.println("DATA SIZE : "+data.size());
    this.data=data;
    minflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {


        View v=minflater.inflate(R.layout.mylistviewdetails,null);

        TextView nameTextView=v.findViewById(R.id.nameTextView);
        TextView priceTextView=v.findViewById(R.id.priceTextView);


        String[] dataString = data.get(i);
        String name=dataString[0];
        String cost=dataString[1];


        nameTextView.setText(name);
        priceTextView.setText(cost);
        return v;
    }
}
