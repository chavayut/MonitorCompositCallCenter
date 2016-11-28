package com.example.administrator.monitorcompositcallcenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter {
    private List<String[]> scoreList = new ArrayList<String[]>();

    static class ItemViewHolder {
        TextView name;
        TextView score;
        TextView [] Columns= new TextView[7];
    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            viewHolder.Columns[0] = (TextView) row.findViewById(R.id.name);
            viewHolder.Columns[1] = (TextView) row.findViewById(R.id.score);
            viewHolder.Columns[2] = (TextView) row.findViewById(R.id.textView);
            viewHolder.Columns[3] = (TextView) row.findViewById(R.id.textView2);
            viewHolder.Columns[4] = (TextView) row.findViewById(R.id.textView3);
            viewHolder.Columns[5] = (TextView) row.findViewById(R.id.textView4);
            viewHolder.Columns[6] = (TextView) row.findViewById(R.id.textView5);

            viewHolder.score = (TextView) row.findViewById(R.id.score);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        for (int i = 0; i <7 ; i++) {
            viewHolder.Columns[i].setText(stat[i]);
        }
//        viewHolder.name.setText(stat[0]);
//        viewHolder.score.setText(stat[1]);
        return row;
    }}