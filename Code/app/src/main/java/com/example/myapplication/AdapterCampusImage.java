package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Classes.imageClass;

import java.util.List;

public class AdapterCampusImage extends BaseAdapter {
    Context context;
    List<imageClass> listimage;
    LayoutInflater inflator;

    public AdapterCampusImage(Context ctx, List<imageClass> imageList)
    {
        this.context = ctx;
        this.listimage = imageList;
        inflator = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listimage.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        v = inflator.inflate(R.layout.campuslife_list_row, null);
        ImageView iview = (ImageView) v.findViewById(R.id.campuslifeimage);
        TextView tview = (TextView) v.findViewById(R.id.picCaption);
        iview.setImageBitmap(listimage.get(i).getImage());
        tview.setText(listimage.get(i).getCaption());
        return v;
    }

}
