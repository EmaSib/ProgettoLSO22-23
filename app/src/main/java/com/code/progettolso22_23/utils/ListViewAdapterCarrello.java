package com.code.progettolso22_23.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.code.progettolso22_23.R;

public class ListViewAdapterCarrello extends BaseAdapter {

    Context context;
    String[] nomi;
    String[] costi;
    String[] quantita;
    private static LayoutInflater inflater = null;

    public ListViewAdapterCarrello(Context context, String[] nomi, String[] costi, String[] quantita) {
        this.context = context;
        this.nomi = nomi;
        this.costi = costi;
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
            vi = inflater.inflate(R.layout.list_view_row_carrello, null);
        TextView text = (TextView) vi.findViewById(R.id.nome_list_view_row_carrello);
        text.setText(nomi[i]);
        TextView text2 = (TextView) vi.findViewById(R.id.costo_list_view_row_carrello);
        text2.setText("Costo: " + costi[i]);
        TextView text3 = (TextView) vi.findViewById(R.id.quantita_list_view_row_carrello);
        text3.setText("Quantita: " + quantita[i]);
        return vi;
    }

}
