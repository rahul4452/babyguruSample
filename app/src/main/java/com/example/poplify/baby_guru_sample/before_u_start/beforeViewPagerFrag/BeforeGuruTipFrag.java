package com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import java.util.ArrayList;
import java.util.List;

//import br.tiagohm.markdownview.MarkdownView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeGuruTipFrag extends Fragment {

    private Bundle bundle;

    private String SERIALIZED_KEY = "gurusTips";
    private WebView descriptionWebView;
    private RecyclerView guruTipsRecycler;
    private List<String> guruMainContentLabel;
    private List<String> guruMainContentDesc;
    private BeforeYouStartResponse.GuruTips guruTips;
    private List<BeforeYouStartResponse.MainContent> gurusTipData;


    public BeforeGuruTipFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        guruTips = (BeforeYouStartResponse.GuruTips) bundle.getSerializable(SERIALIZED_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_before_guru_tip, container, false);
        init(view);

        descriptionWebView.loadData(guruTips.getDescription(),"text/html", "UTF-8");

        setupGuruDataFromServer(view);

        return view;
    }


    private void init(View view) {
        descriptionWebView = view.findViewById(R.id.webViewGuru);
        guruTipsRecycler = view.findViewById(R.id.guru_list);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------Setting up the Guru Tips Recycler------------------------------------------------------

    private void setupGuruDataFromServer(View view) {

        guruMainContentLabel = new ArrayList<>();
        guruMainContentDesc = new ArrayList<>();

        gurusTipData = guruTips.getMainContent();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        for (BeforeYouStartResponse.MainContent mainContent : gurusTipData) {

            guruMainContentLabel.add(mainContent.getLabel());
            guruMainContentDesc.add(mainContent.getDescription());
        }
        guruTipsRecycler.setHasFixedSize(true);
        guruTipsRecycler.setLayoutManager(layoutManager);

        Recycle_Guru recycle_guru = new Recycle_Guru(guruMainContentLabel,guruMainContentDesc,view);
        guruTipsRecycler.setAdapter(recycle_guru);

    }

    public class Recycle_Guru extends RecyclerView.Adapter<Recycle_Guru.Guru_holder>
    {

        List<String> head_list,body_list;
        View view;
        Resources res;


        public Recycle_Guru(List<String> head_list, List<String> body_list, View view) {
            this.head_list = head_list;
            this.body_list = body_list;
            this.view = view;
        }

        public class Guru_holder extends RecyclerView.ViewHolder{
            TextView head_txt;
            WebView body_txt;
            View v1,v2;

            public Guru_holder(@NonNull View itemView) {
                super(itemView);
                head_txt = itemView.findViewById(R.id.text_head);
                body_txt = itemView.findViewById(R.id.text_body);
                v1 = itemView.findViewById(R.id.view);
                v2 = itemView.findViewById(R.id.view2);
            }
        }


        @NonNull
        @Override
        public Guru_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_guru_layout,viewGroup,false);
            Guru_holder guru_holder = new Guru_holder(view);
            return guru_holder;
        }


        @Override
        public void onBindViewHolder(@NonNull Guru_holder holder, int i) {
            res = holder.itemView.getContext().getResources();
            String s2 = head_list.get(i);
            String s3 = body_list.get(i);

            int size = head_list.size()-1;

            if(i==size)
            {
                holder.head_txt.setVisibility(View.GONE);
                holder.v1.setVisibility(View.GONE);
                holder.v2.setVisibility(View.GONE);
            }
            else {
                holder.head_txt.setText(s2);
                holder.body_txt.loadData(s3,"text/html","UTF-8");
            }
        }


        @Override
        public int getItemCount() {
            return head_list.size();
        }


    }


}
