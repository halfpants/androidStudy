package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by powflash on 2018. 2. 7..
 */

public class WordAdapter extends ArrayAdapter<Word> {
    int mColorResourceID = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        Word currentWord = getItem(position);
        TextView defaultView = (TextView) listItemView.findViewById(R.id.defaultTranslation);
        defaultView.setText(currentWord.getDefaultTranslation());

        TextView secondView = (TextView) listItemView.findViewById(R.id.secondTranslation);
        secondView.setText(currentWord.getMiwokTranslation());

        ImageView leftImageView = (ImageView) listItemView.findViewById(R.id.leftImage);
//        String tmpStr = new String("number_" + currentWord.getDefaultTranslation());

        if(currentWord.hasImage()) {
            leftImageView.setImageResource(currentWord.getImageResourceId());
        } else {
            //Otheerwise hide the ImageView(set visibility to GONE)
            leftImageView.setVisibility(View.GONE);
        }

        View listLayout = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        listLayout.setBackgroundColor(color);

        return listItemView;
    }
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceID) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mColorResourceID = colorResourceID;
    }
}
