package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<QuakeData>{
    public CustomAdapter(@NonNull Context context, @NonNull List<QuakeData> data) {
        super(context, 0, data);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        QuakeData data=getItem(position);
        View lstview = convertView;
        if(lstview==null){
            lstview= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView mag =(TextView)lstview.findViewById(R.id.magnitude);
        TextView loc_offset=(TextView)lstview.findViewById(R.id.location_offset);
        TextView loc_primary=(TextView)lstview.findViewById(R.id.primary_location);
        TextView date=(TextView)lstview.findViewById(R.id.date);
        GradientDrawable cicle=(GradientDrawable) mag.getBackground();
        int magnitudeColor = getMagnitudeColor(data.getMag());
        cicle.setColor(magnitudeColor);
        mag.setText(String.valueOf(data.getMag()));
        String city=data.getCity();
        if(!city.contains(" of ")){
            loc_offset.setText(getContext().getString(R.string.near_the));
            loc_primary.setText(city);
        }
        else {
            String arr[] = city.split(" of ");
            loc_offset.setText(arr[0]+" of ");
            loc_primary.setText(arr[1]);

        }
        Date dateobject=new Date(data.getDate());
        String formatdate=formatDate(dateobject);
        date.setText(formatdate);
        TextView time=(TextView)lstview.findViewById(R.id.time);
        String formatTime=formatTime(dateobject);
        time.setText(formatTime);



        return lstview;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
