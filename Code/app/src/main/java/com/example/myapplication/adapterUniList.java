

package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


class uniinfo
{
    public String uniName;
    public int uniPic;

    public uniinfo(String un, int up)
    {
        this.uniName = un;
        this.uniPic = up;
    }

}

public class adapterUniList extends ArrayAdapter<uniinfo> {


    public adapterUniList(@NonNull Context context, int resource, @NonNull uniinfo[] objects) {
        super(context, resource, objects);
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        uniinfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.uni_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView tview = (TextView) convertView.findViewById(R.id.uniname);
        ImageView iview = (ImageView) convertView.findViewById(R.id.unipicture);
        tview.setText(obj.uniName);
        iview.setImageResource(obj.uniPic);

        return convertView;
    }
}
