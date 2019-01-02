package com.example.poplify.baby_guru_sample.thirdTab;


import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_mthd_other_frag extends Fragment implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {


    private static final String SERIALIZED_KEY = "method2";
    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;
    SeekBar seekbar;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
      ExpandableListView expandableListView2;
    FragmentManager fragmentManager;
    Bundle bundle2 = new Bundle();
    private CryingScaleResponse method2;
    private Typeface regular,regularMon;
    private TextView listenSamTv,levelAndSuggestion,start_txt,end_txt;
    private int[] images = {R.drawable.cry_scale_logo_1, R.drawable.cry_scale_logo_2, R.drawable.cry_scale_logo_3, R.drawable.cry_scale_logo_4, R.drawable.cry_scale_logo_5};
    private List<CryingScaleResponse.CryingScaleDatum> method_List;
    private List<String> levelList, titleList;
    private ExpandableListAdapter adapter;
    HashMap<String, List<List<String>>> childdata;
    TextView levelTv, tvbelowlevel, tvSuggest;
    ImageView levelImage;
    private ArrayList<String> childtext;
    private TextView expandChildText;
    List<CryingScaleResponse.MethodTwo> methodTwo;

    public Choose_mthd_other_frag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle2 = getArguments();
        method2 = (CryingScaleResponse) bundle2.getSerializable(SERIALIZED_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_mthd_other_frag, container, false);
        fragmentManager = getFragmentManager();
        expandableListView2 = view.findViewById(R.id.suggestionExpendableMethod2);





        container = (ViewGroup) getLayoutInflater().inflate(R.layout.choose_expand_header, expandableListView2, false);

        expandableListView2.addHeaderView(container);

        initMethod2(view);
        setupExpandableHeader(method2);
        setupExpandableView(method2);
        expandableListView2.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {

                if (groupPosition != previousGroup)
                    expandableListView2.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        try {

            mediaPlayer = MediaPlayer.create(getContext(), R.raw.maroon);
            mediaPlayer.setOnCompletionListener(this);
            seekbar.setClickable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    private void setupExpandableHeader(CryingScaleResponse method2) {
        listenSamTv.setText(method2.getCryingScaleLabel().getLabels().getListenToSam());
        listenSamTv.setTypeface(regular);
        levelAndSuggestion.setText(method2.getCryingScaleLabel().getHeader().getSubHeader());
        levelAndSuggestion.setTypeface(regular);
    }


    private void setupExpandableView(CryingScaleResponse method2) {

        List<List<String>> suggestionList;
        methodTwo = new ArrayList<>();
        Mylist m = new Mylist();
        levelList = new ArrayList<>();
        titleList = new ArrayList<>();

        childdata = new HashMap<>();

        method_List = method2.getCryingScaleData();
        for (int i = 0; i < method_List.size(); i++) {
            if (method_List.get(i).getMethodTwo() != null) {
                methodTwo = method_List.get(i).getMethodTwo();
                break;
            }
        }

        for (CryingScaleResponse.MethodTwo dataMethodTwo : methodTwo) {
            suggestionList = new ArrayList<>();
            levelList.add(dataMethodTwo.getLevel());
            titleList.add(dataMethodTwo.getTitle());
            suggestionList.add(dataMethodTwo.getSuggestion());
            childdata.put(dataMethodTwo.getLevel(), suggestionList);

        }


        m.setLevelList(levelList);
        m.setTitleList(titleList);
        m.setImagesList(images);
        m.setSuggest(method2.getCryingScaleLabel().getLabels().getSuggestion());

        adapter = new ExpandableListAdapter(getContext(), m, childdata);
        expandableListView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class Mylist {
        private List<String> levelList, titleList = new ArrayList<>();
        private int[] imagesList;
        private String suggest;

        public int[] getImagesList() {
            return imagesList;
        }

        public void setImagesList(int[] imagesList) {
            this.imagesList = imagesList;
        }

        public String getSuggest() {
            return suggest;
        }

        public void setSuggest(String suggest) {
            this.suggest = suggest;
        }

        public List<String> getLevelList() {
            return levelList;
        }

        public void setLevelList(List<String> levelList) {
            this.levelList = levelList;
        }

        public List<String> getTitleList() {
            return titleList;
        }

        public void setTitleList(List<String> titleList) {
            this.titleList = titleList;
        }
    }

    class ExpandableListAdapter extends BaseExpandableListAdapter {

        Context context;
        private HashMap<String, List<List<String>>> childSuggestion;

        Mylist mt;


        public ExpandableListAdapter(Context context, Mylist m, HashMap<String, List<List<String>>> childdata) {
            this.context = context;
            this.mt = m;
            this.childSuggestion = childdata;

        }

        @Override
        public int getGroupCount() {
            return mt.getLevelList().size();
        }

        @Override
        public int getChildrenCount(int i) {
            return childSuggestion.get(this.mt.getLevelList().get(i)).size();
        }

        @Override
        public Object getGroup(int i) {
            Mylist group = new Mylist();

            group.setLevelList(mt.getLevelList());
            group.setTitleList(mt.titleList);
            group.setImagesList(mt.getImagesList());
            group.setSuggest(mt.getSuggest());

            return group;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return this.childSuggestion.get(this.mt.getLevelList().get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.choose_expand_lis_header, null);

            regularMon = Typeface.createFromAsset(convertView.getResources().getAssets(), "Montserrat-Regular.otf");
            regular = Typeface.createFromAsset(convertView.getResources().getAssets(), "Comfortaa_Regular.ttf");

            Mylist headTitle = (Choose_mthd_other_frag.Mylist) getGroup(i);

            levelTv = convertView.findViewById(R.id.Levels);
            tvbelowlevel = convertView.findViewById(R.id.titleBelowLevel);
            tvSuggest = convertView.findViewById(R.id.suggest);
            levelImage = convertView.findViewById(R.id.recycler_img);


            levelTv.setText(headTitle.getLevelList().get(i));
            levelTv.setTypeface(regular);
            tvbelowlevel.setText(headTitle.getTitleList().get(i));
            tvbelowlevel.setTypeface(regularMon);

            levelImage.setImageResource(images[i]);
            tvSuggest.setText(headTitle.getSuggest());

            return convertView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.choose_expand_list_child, viewGroup, false);

            childtext = (ArrayList<String>) getChild(i, i1);
            expandChildText = view.findViewById(R.id.childtext);

            expandChildText.setText(childtext.get(i1));

//        if (groupPosition % 2 == 0) {
//
//            markdownView.setBackground(context.getDrawable(R.drawable.bef_list_1_text));//pink
//        } else if (groupPosition % 3 == 0) {
//            markdownView.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//green
//        } else if (groupPosition % 5 == 0) {
//
//            markdownView.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//purple
//        } else {
//            markdownView.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//yellow
//        }

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

    private void initMethod2(View view) {


        fragmentManager = getFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        //setting up the header
        start_txt = view.findViewById(R.id.start_time);
        end_txt = view.findViewById(R.id.end_time);

        listenSamTv = view.findViewById(R.id.listenSam);
        levelAndSuggestion = view.findViewById(R.id.blueHeader);


        seekbar = view.findViewById(R.id.music_bar);

        start_txt = view.findViewById(R.id.start_time);

        end_txt = view.findViewById(R.id.end_time);


    }

    private void replacementFragment(Fragment fragment) {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack, 0);

        Log.d("", "replacementFragment: fragmentPopped" + fragmentPopped);
        try {
            if (fragmentPopped != true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    void play_music_on_click() {
//        Toast.makeText(getContext(), "Playing  sound", Toast.LENGTH_SHORT).show();
//
//        if (!mediaPlayer.isPlaying()) {
//            try {
//                mediaPlayer.start();
//
//                //mediaPlayer.setOnPreparedListener(this);
//                //mediaPlayer.prepareAsync();
//                finalTime = mediaPlayer.getDuration();
//                startTime = mediaPlayer.getCurrentPosition();
//
//                if (oneTimeOnly == 0) {
//                    seekbar.setMax((int) finalTime);
//                    oneTimeOnly = 1;
//                }
//
//                start_txt.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                        finalTime)))
//                );
//
//                end_txt.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                        startTime)))
//                );
//
//                seekbar.setProgress((int) startTime);
//                myHandler.postDelayed(UpdateSongTime, 100);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //getActivity().finish();
//
//    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            start_txt.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }
}
