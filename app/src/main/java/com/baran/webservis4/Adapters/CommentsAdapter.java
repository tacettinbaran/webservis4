package com.baran.webservis4.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baran.webservis4.Models.CommentsModel;
import com.baran.webservis4.R;
import com.baran.webservis4.Activities.MainActivity2;

import java.util.List;

public class CommentsAdapter extends BaseAdapter {

    List<CommentsModel> list;
    Context context;
    Activity activity;

    public CommentsAdapter(List<CommentsModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity= activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_comment, parent, false);

        TextView postIdText, idText, nameText, emailText, bodyText;
        LinearLayout layoutList;
        postIdText = convertView.findViewById(R.id.postId);
        idText = convertView.findViewById(R.id.id);
        nameText = convertView.findViewById(R.id.name);
        emailText = convertView.findViewById(R.id.email);
        bodyText = convertView.findViewById(R.id.body);
        layoutList = convertView.findViewById(R.id.layout_list);

        postIdText.setText(postIdText.getText().toString() + " " + list.get(position).getPostId());
        idText.setText(idText.getText().toString() + " " + list.get(position).getId() + "");
        nameText.setText(nameText.getText().toString() + " " + list.get(position).getName());
        emailText.setText(emailText.getText().toString() + " " + list.get(position).getEmail());
        bodyText.setText(bodyText.getText().toString() + " " + list.get(position).getBody());
        final String postId= String.valueOf(list.get(position).getPostId());
        final String idm= String.valueOf(list.get(position).getId());

        layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity,MainActivity2.class);
                intent.putExtra("post_id",postId);
                intent.putExtra("idm",idm);
                activity.startActivity(intent);

            }
        });


        return convertView;
    }
}
