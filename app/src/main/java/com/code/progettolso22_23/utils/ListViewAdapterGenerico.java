package com.code.progettolso22_23.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.code.progettolso22_23.R;

public class ListViewAdapterGenerico extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public ListViewAdapterGenerico(Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.list_view_row_generica, null);
        TextView text = (TextView) vi.findViewById(R.id.textView_list_view_row_generica);
        text.setText(data[position]);
        return vi;
    }
}
