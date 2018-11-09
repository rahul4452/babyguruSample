package com.example.poplify.baby_guru_sample.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BulletSpan;

import android.text.style.MetricAffectingSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

import com.example.poplify.baby_guru_sample.choose_method.Choose_mthd_recomm_frag;


import java.util.List;




public class Recycler_adapt extends RecyclerView.Adapter<Recycler_adapt.Holder> {

    int image ;
    List<String> list;
    Fragment context;
    Resources res;




    public Recycler_adapt(int image, List<String> list, Fragment fragment) {
        this.image = image;
        this.list = list;
        this.context =  fragment;
    }



    public class Holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public Holder(@NonNull View itemView) {

            super(itemView);
           imageView = itemView.findViewById(R.id.recycler_img);
            textView = itemView.findViewById(R.id.recycler_txt);

        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view,viewGroup,false);
        Holder holder = new Holder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
         res = holder.itemView.getContext().getResources();

        String s2 = list.get(i);
        holder.textView.setText(s2);
        holder.imageView.setImageResource(image);

        /*SpannableString spannableString = new SpannableString(s2);
        ImageSpan imageSpan = new ImageSpan(context.getContext(), R.drawable.choose_method_bullet,ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, 0, 1, 0);
        holder.textView.setText(spannableString);


        SpannableString sag = new SpannableString(s2);
        sag.setSpan(new BulletSpan(30,Color.rgb(251, 177, 243)), 0, s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.textView.setText(sag);
            holder.imageView.setImageResource(image);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
