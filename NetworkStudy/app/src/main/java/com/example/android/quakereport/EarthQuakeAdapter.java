package com.example.android.quakereport;

/**
 * Created by powflash on 2018. 2. 12..
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        final EarthQuake currentEQ = getItem(position);


        TextView defaultView = (TextView) listItemView.findViewById(R.id.magInfo);
        defaultView.setText(formatMagnitude(currentEQ.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) defaultView.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEQ.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String places = currentEQ.getLocation();
        int splitIndex = -1;
        splitIndex = places.indexOf("of ");

        String [] splitArr = new String[2];
        if(splitIndex != -1){
            splitArr[0] = places.substring(0, splitIndex + 3);
            splitArr[1] = places.substring(splitIndex + 3, places.length());
        } else {// "of " 가 포함되지 않았으면
            splitArr[0] = "Near the";
            splitArr[1] = places;
        }


        TextView secondView = (TextView) listItemView.findViewById(R.id.locInfo);
        secondView.setText(splitArr[0]);

        TextView secondPView = (TextView) listItemView.findViewById(R.id.locInfo2);
        secondPView.setText(splitArr[1]);


        long timeInMilliseconds = currentEQ.getDate();
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        TextView thirdView = (TextView) listItemView.findViewById(R.id.dateInfo);
        thirdView.setText(dateToDisplay);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormatter.format(dateObject);

        TextView fourthView = (TextView) listItemView.findViewById(R.id.timeInfo);
        fourthView.setText(timeToDisplay);

        listItemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent myIntent = null;
                try {
                    myIntent = new Intent().parseUri(currentEQ.getLink(), Intent.URI_INTENT_SCHEME);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                if (myIntent != null) {
                    context.startActivity(myIntent);
                }

            }
        });

        return listItemView;
    }

    public EarthQuakeAdapter(@NonNull Context context, @NonNull ArrayList<EarthQuake> objects) {
        super(context, 0, objects);
    }


    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    public int getMagnitudeColor(double value) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(value);

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
}
