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
import com.example.personale.firstjsonattempt.controller.ImageDownloadTasker;
import com.example.personale.firstjsonattempt.controller.StudentList;
import com.example.personale.firstjsonattempt.model.Student;

import java.util.ArrayList;

/**
 * Created by personale on 27/02/2017.
 */

public class StudentAdapter extends SelectableAdapter<StudentAdapter.StudentVH> {

    private final Context context;
    private StudentList list;
    private boolean startSelection;
    private ClickListener adapterListener;

    public StudentAdapter(Context context, ClickListener clickListener){
        list = new StudentList();
        this.context = context;
        this.adapterListener = clickListener;
    }

    public void setDataSet(ArrayList<Student> dataSet) {
        list.setDataSet(dataSet);
        notifyDataSetChanged();
    }

    public void setStartSelection(boolean startSelection) {
        this.startSelection = startSelection;
    }

    public void delete(){
        for(int i : getSelectedItem()){
            list.removeStudent(i);
        }

        clearSelection();
        notifyDataSetChanged();
    }

    @Override
    public StudentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main, null);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(StudentVH holder, int position) {
        holder.itemView.setSelected(isSelected(position));
        holder.nameTv.setText(list.getStudent(position).getName());
        holder.emailTv.setText(list.getStudent(position).getEmail());
        holder.corsoTv.setText(list.getStudent(position).getCorso().getCorso());
        new ImageDownloadTasker(holder.avatarIv, holder.itemView.getContext()).execute(list.getStudent(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return list.getSize();
    }

    public class StudentVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView nameTv, emailTv, corsoTv;
        ImageView githubIv, fbIv, avatarIv;
        ClickListener listener;

        public StudentVH(View itemView) {
            super(itemView);
            nameTv  = (TextView)itemView.findViewById(R.id.item_title);
            emailTv = (TextView)itemView.findViewById(R.id.item_mail);
            corsoTv = (TextView)itemView.findViewById(R.id.item_corso);
            githubIv   = (ImageView) itemView.findViewById(R.id.item_github);
            avatarIv   = (ImageView) itemView.findViewById(R.id.item_avatar);
            fbIv = (ImageView) itemView.findViewById(R.id.item_fb);
            listener = adapterListener;

            githubIv.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_github:
                    openGitHub();
                    break;
                default:
                    listener.OnClickListener(getAdapterPosition());
            }
        }

        private void openGitHub() {
            if(!startSelection){
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(list.getStudent(getAdapterPosition()).buildGithubUrl());
                i.setData(uri);
                context.startActivity(i);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(!startSelection){
                listener.OnLongClickListener(getAdapterPosition(), startSelection);
            }

            startSelection = !startSelection;
            return true;
        }
    }

    public interface ClickListener{
        void OnClickListener(int pos);
        void OnLongClickListener(int pos, boolean startSelection);
    }
}
