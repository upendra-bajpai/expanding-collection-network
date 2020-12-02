package com.grb.sapmle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ramotion.expandingcollection.ECCardContentListItemAdapter;


import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

@SuppressLint({"SetTextI18n", "InflateParams"})
public class CommentArrayAdapter extends ECCardContentListItemAdapter<Comment> {

    public CommentArrayAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, R.layout.list_form_detils, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            rowView = inflater.inflate(R.layout.list_form_detils, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.line1 = (TextView) rowView.findViewById(R.id.firstLine);
            viewHolder.line2 = (TextView) rowView.findViewById(R.id.secondLine);
            viewHolder.purchase = (Button) rowView.findViewById(R.id.purchase);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final Comment objectItem = getItem(position);


        if (objectItem != null) {
            viewHolder.line1.setText(objectItem.getCommentPersonName() + ":");
            viewHolder.line2.setText(objectItem.getCommentText());
            viewHolder.date.setText(objectItem.getCommentDate());
        }

        // Removing item by tap on comment icon
//        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CommentArrayAdapter.this.remove(objectItem);
//                CommentArrayAdapter.this.notifyDataSetChanged();
//            }
//        });

        return rowView;
    }

    static class ViewHolder {
        TextView date;
        TextView line1;
        TextView line2;
        Button purchase;
    }



}
