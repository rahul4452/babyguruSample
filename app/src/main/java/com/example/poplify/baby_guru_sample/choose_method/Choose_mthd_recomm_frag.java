package com.example.poplify.baby_guru_sample.choose_method;


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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;
//import com.example.poplify.baby_guru_sample.adapter.Service_class_music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_mthd_recomm_frag extends Fragment implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, View.OnClickListener {


    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;
    @BindView(R.id.music_bar)
    SeekBar seekbar;

    TextView start_txt, end_txt, listenSamTv, levelAndSuggestion;


    ExpandableListView expandableListView;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private int startTime = 0;
    private int finalTime = 0;
    public static int oneTimeOnly = 0;
    Typeface regular, regularMon;
    @BindView(R.id.play_btn)
    ImageButton btn_4_music;
    private int image = R.drawable.choose_method_bullet;
    private String SERIALIZED_KEY = "method1";
    private Button proceed_timer;
    private FragmentManager fragmentManager;
    private CryingScaleResponse method1;
    private static final String TAG = "Choose_mthd_recomm_frag";
    private CryingScaleResponse serverExistUser;
    private Bundle bundle;
    private List<CryingScaleResponse.CryingScaleDatum> cryingScaleData;
    private int[] images = {R.drawable.cry_scale_logo_1, R.drawable.cry_scale_logo_2, R.drawable.cry_scale_logo_3, R.drawable.cry_scale_logo_4, R.drawable.cry_scale_logo_5};
    private List<CryingScaleResponse.CryingScaleDatum> method_List;
    private List<String> levelList, titleList;
    private ExpandableListAdapter adapter;
    HashMap<String, List<List<String>>> childdata;
    TextView levelTv, tvbelowlevel, tvSuggest;
    ImageView levelImage;
    private ArrayList<String> childtext;
    private TextView expandChildText;
    List<CryingScaleResponse.MethodOne> methodOne;

    public Choose_mthd_recomm_frag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        method1 = (CryingScaleResponse) bundle.getSerializable(SERIALIZED_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_mthd_recomm_frag, container, false);

        expandableListView = view.findViewById(R.id.suggestionExpendable);

        container = (ViewGroup) getLayoutInflater().inflate(R.layout.choose_expand_header, expandableListView, false);

        expandableListView.addHeaderView(container);

        initCryingScale(view);
        setupCryingScale(view);
        setupExpandableHeader(method1);
        setupExpandableView(method1);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {

                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;

                expandableListView.setSelectedGroup(groupPosition);
            }
        });

        //Button to proceed the Timer Fragment

//        proceed_timer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replacementFragment(new Timer_frag());
//            }
//        });


        try {

            mediaPlayer = MediaPlayer.create(getContext(), R.raw.maroon);
            mediaPlayer.setOnCompletionListener(this);
            seekbar.setClickable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //playMusic = new Intent(getActivity(), Service_class_music.class);

        return view;
    }

    private void setupExpandableHeader(CryingScaleResponse method1) {
        listenSamTv.setText(method1.getCryingScaleLabel().getLabels().getListenToSam());
        listenSamTv.setTypeface(regular);
        levelAndSuggestion.setText(method1.getCryingScaleLabel().getHeader().getSubHeader());
        levelAndSuggestion.setTypeface(regular);
    }


    private void setupExpandableView(CryingScaleResponse method1) {

        List<List<String>> suggestionList;
        methodOne = new ArrayList<>();
        Mylist m = new Mylist();
        levelList = new ArrayList<>();
        titleList = new ArrayList<>();

        childdata = new HashMap<>();

        method_List = method1.getCryingScaleData();
        for (int i = 0; i < method_List.size(); i++) {
            if (method_List.get(i).getMethodOne() != null) {
                methodOne = method_List.get(i).getMethodOne();
                break;
            }
        }

        for (CryingScaleResponse.MethodOne dataMethodOne : methodOne) {
            suggestionList = new ArrayList<>();
            levelList.add(dataMethodOne.getLevel());

            titleList.add(dataMethodOne.getTitle());
            suggestionList.add(dataMethodOne.getSuggestion());
            childdata.put(dataMethodOne.getLevel(), suggestionList);

        }


        m.setLevelList(levelList);
        m.setTitleList(titleList);
        m.setImagesList(images);
        m.setSuggest(method1.getCryingScaleLabel().getLabels().getSuggestion());

        adapter = new ExpandableListAdapter(getContext(), m, childdata);
        expandableListView.setAdapter(adapter);
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

            Mylist headTitle = (Mylist) getGroup(i);

            levelTv = convertView.findViewById(R.id.Levels);
            levelTv.setTypeface(regular);
            tvbelowlevel = convertView.findViewById(R.id.titleBelowLevel);
            tvbelowlevel.setTypeface(regularMon);
            tvSuggest = convertView.findViewById(R.id.suggest);
            levelImage = convertView.findViewById(R.id.recycler_img);


            levelTv.setText(headTitle.getLevelList().get(i));
            tvbelowlevel.setText(headTitle.getTitleList().get(i));

            levelImage.setImageResource(images[i]);
            tvSuggest.setText(headTitle.getSuggest());
            tvSuggest.setTypeface(regularMon);

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


    private void setupCryingScale(View view) {
        //proceed_timer = view.findViewById(R.id.proceed);


    }

    private void initCryingScale(View view) {
        fragmentManager = getFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        //setting up the header
        start_txt = view.findViewById(R.id.start_time);
        end_txt = view.findViewById(R.id.end_time);

        listenSamTv = view.findViewById(R.id.listenSam);
        levelAndSuggestion = view.findViewById(R.id.blueHeader);


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

    //=====================================================================================//
    //================================Setting up the Music Player==========================//

    @OnClick(R.id.play_btn)
    void play_music_on_click() {
        Toast.makeText(getContext(), "Playing  sound", Toast.LENGTH_SHORT).show();

        if (!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.start();

                //mediaPlayer.setOnPreparedListener(this);
                //mediaPlayer.prepareAsync();
                finalTime = mediaPlayer.getDuration();
                Log.d("", "play_music_on_click: final Time");
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                seekbar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //getActivity().finish();

    }
    //------------------------------------------------------------------------------------------------------//


    //=====================================================================================//
    //================================Setting up the runnable for music Player==========================//


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            try {


                if (mediaPlayer.isPlaying()) {
                    startTime = mediaPlayer.getCurrentPosition();
                    finalTime = mediaPlayer.getDuration();
                    updatePlayer(startTime, finalTime);

                    seekbar.setProgress((int) startTime);
                    myHandler.postDelayed(this, 100);

                }
               /* start_txt.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) startTime)))
                );*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
//------------------------------------------------------------------------------------------------------//


    //=====================================================================================//
    //========================method For updating start time and Runtime of media player=========================//

    private void updatePlayer(int currentDuration, int final_time) {
        start_txt.setText("" + milliSecondsToTimer((long) currentDuration));

        Log.d(TAG, "updatePlayer: ennd duration of song" + mediaPlayer.getDuration());
        end_txt.setText("" + milliSecondsToTimer((long) final_time));
    }

    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

//------------------------------------------------------------------------------------------------------//

    @Override
    public void onPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        //mediaPlayer.release();

        super.onPause();
    }


    @Override
    public void onDestroyView() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();

        super.onDestroyView();

    }

    @Override
    public void onDestroy() {

        mediaPlayer.release();


        super.onDestroy();
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onClick(View view) {

    }
}