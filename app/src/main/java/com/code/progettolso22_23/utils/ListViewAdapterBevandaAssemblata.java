package com.code.progettolso22_23.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.code.progettolso22_23.R;

public class ListViewAdapterBevandaAssemblata extends BaseAdapter {

    Context context;
    String[] nomi;
    String[] costi;
    String[] ingredienti;
    private static LayoutInflater inflater = null;

    public ListViewAdapterBevandaAssemblata(Context context, String[] nomi, String[] costi, String[] ingredienti) {
        this.context = context;
        this.nomi = nomi;
        this.costi = costi;
        this.ingredienti = ingredienti;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nomi.length;
    }

    @Override
    public Object getItem(int i) {
        return nomi[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.list_view_row_bevanda_assemblata, null);
        TextView text = (TextView) vi.findViewById(R.id.nome_list_view_row_bevanda_assemblata);
        text.setText(nomi[i]);
        TextView text2 = (TextView) vi.findViewById(R.id.costo_list_view_row_bevanda_assemblata);
        text2.setText(costi[i]);
        TextView text3 = (TextView) vi.findViewById(R.id.costo_list_view_row_bevanda_assemblata);
        text3.setText(ingredienti[i]);
        return vi;
    }
}