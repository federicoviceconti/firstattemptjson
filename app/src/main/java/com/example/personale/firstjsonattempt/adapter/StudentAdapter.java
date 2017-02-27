package com.example.personale.firstjsonattempt.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personale.firstjsonattempt.R;
import com.example.personale.firstjsonattempt.model.Student;

import java.util.ArrayList;

/**
 * Created by personale on 27/02/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH> {

    private final Context context;
    StudentList list;

    public StudentAdapter(Context context){
        list = new StudentList();
        this.context = context;
    }

    @Override
    public StudentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main, null);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(StudentVH holder, int position) {
        holder.nameTv.setText(list.getStudent(position).getName());
        holder.emailTv.setText(list.getStudent(position).getEmail());
        holder.corsoTv.setText(list.getStudent(position).getCorso().getCorso());
    }

    @Override
    public int getItemCount() {
        return list.getSize();
    }

    public void setDataSet(ArrayList<Student> dataSet) {
        list.setDataSet(dataSet);
        notifyDataSetChanged();
    }

    public class StudentVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTv, emailTv, corsoTv;
        ImageView githubIv, fbIv;

        public StudentVH(View itemView) {
            super(itemView);
            nameTv  = (TextView)itemView.findViewById(R.id.item_title);
            emailTv = (TextView)itemView.findViewById(R.id.item_mail);
            corsoTv = (TextView)itemView.findViewById(R.id.item_corso);
            githubIv   = (ImageView) itemView.findViewById(R.id.item_github);
            fbIv = (ImageView) itemView.findViewById(R.id.item_fb);

            githubIv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(list.getStudent(getAdapterPosition()).buildGithubUrl());
            i.setData(uri);
            context.startActivity(i);
        }
    }
}
