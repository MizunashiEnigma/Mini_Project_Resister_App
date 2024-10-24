package com.example.mini_project_resister_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ColorSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] items;
    private int[] colors;

    public ColorSpinnerAdapter(Context context, String[] items, int[] colors) {
        super(context, android.R.layout.simple_spinner_item, items);
        this.context = context;
        this.items = items;
        this.colors = colors;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(items[position]);
        textView.setBackgroundColor(colors[position]); // Set background color based on the position
        return view;
    }
}

