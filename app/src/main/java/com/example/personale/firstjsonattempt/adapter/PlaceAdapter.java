package com.example.personale.firstjsonattempt.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.personale.firstjsonattempt.R;
import com.example.personale.firstjsonattempt.controller.PlaceList;
import com.example.personale.firstjsonattempt.model.Place;

import java.util.ArrayList;

/**
 * Created by personale on 02/03/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceVH> {

    private final Context context;
    private PlaceList placeList;

    public PlaceAdapter(Context context){
        this.context = context;
        placeList = new PlaceList();
    }

    @Override
    public PlaceVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_remote, null);
        return new PlaceVH(v);
    }

    @Override
    public void onBindViewHolder(PlaceVH holder, int position) {
        Place place = placeList.getPlace(position);
        holder.nameTv.setText(place.getName());

        if(!place.getAddress().isEmpty()){
            holder.addressTv.setText(place.getAddress());
        } else {
            setSize(holder.addressTv);
        }

        if(!place.getCountry().isEmpty()){
            holder.countryTv.setText(place.getCountry());
        } else {
            setSize(holder.countryTv);
        }

        if(place.getPhone().isEmpty()){
            setSize(holder.phoneIv);
            setSize(holder.separatorV);
        }
    }

    private void setSize(View view) {
        view.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        view.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
    }

    @Override
    public int getItemCount() {
        return placeList.getSize();
    }

    public void setDataSet(ArrayList<Place> dataSet) {
        placeList.setDataSet(dataSet);
    }

    class PlaceVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTv, addressTv, countryTv;
        private ImageView phoneIv;
        private View separatorV;

        public PlaceVH(View itemView) {
            super(itemView);
            separatorV = (View) itemView.findViewById(R.id.separator_phone);
            nameTv = (TextView) itemView.findViewById(R.id.item_tv_name);
            addressTv = (TextView) itemView.findViewById(R.id.item_tv_address);
            countryTv = (TextView) itemView.findViewById(R.id.item_tv_country);
            phoneIv = (ImageView) itemView.findViewById(R.id.item_iv_phone);

            phoneIv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel: " + placeList.getPlace(getAdapterPosition()).getPhone()));
            v.getContext().startActivity(i);
        }
    }
}
