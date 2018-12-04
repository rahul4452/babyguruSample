package com.example.poplify.baby_guru_sample.adapter;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

import com.example.poplify.baby_guru_sample.choose_method.Choose_mthd_recomm_frag;


import java.util.List;


public class Recycler_adapt extends RecyclerView.Adapter<Recycler_adapt.Holder> {

    int image;
    Fragment context;
    private List<List<String>> suggestionList;
    private List<String> levelList,titleList;
    String suggestion;
    Resources res;

    public Recycler_adapt(int image, List<String> levelList, List<String> titleList, List<List<String>> suggestionList, String suggestion, Choose_mthd_recomm_frag choose_mthd_recomm_frag) {
        this.image = image;
        this.context = choose_mthd_recomm_frag;
        this.suggestionList = suggestionList;
        this.levelList = levelList;
        this.titleList = titleList;
        this.suggestion = suggestion;

    }



    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView levelTV, suggestionTextview;
        ExpandableListView expendableSuggestion;

        public Holder(@NonNull View itemView) {

            super(itemView);
            levelTV = itemView.findViewById(R.id.Levels);
//            suggestionTextview = itemView.findViewById(R.id.visibleSuggestion);
            imageView = itemView.findViewById(R.id.recycler_img);
            expendableSuggestion = itemView.findViewById(R.id.suggestionExpendable);

        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        res = holder.itemView.getContext().getResources();

//        String s2 = list.get(i);
//        holder.textView.setText(s2);
//        holder.imageView.setImageResource(image);

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
        return 12;
    }


}
