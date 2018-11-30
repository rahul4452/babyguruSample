package com.example.poplify.baby_guru_sample.choose_method;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.Recycler_adapt;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;
//import com.example.poplify.baby_guru_sample.adapter.Service_class_music;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_mthd_recomm_frag extends Fragment implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, View.OnClickListener {


    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;
    private @BindView(R.id.music_bar)
    SeekBar seekbar;
    private @BindView(R.id.start_time)
    TextView start_txt;
    private @BindView(R.id.end_time)
    TextView end_txt;
    private @BindView(R.id.toolbar_title)
    TextView tb_title_recomm;

    private @BindView(R.id.listenSam)
    TextView listenSamTv;

    private @BindView(R.id.blueHeader)
    TextView levelAndSuggestion;

    private @BindView(R.id.suggestionExpendable)
    ExpandableListView expandableListView;

    private Handler myHandler = new Handler();

    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private int startTime = 0;
    private int finalTime = 0;
    public static int oneTimeOnly = 0;
    Typeface regular, regularMon;

    private @BindView(R.id.play_btn)
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
    //private int[] images = {R.drawable.cry_scale_logo_1, R.drawable.cry_scale_logo_2, R.drawable.cry_scale_logo_3, R.drawable.cry_scale_logo_4, R.drawable.cry_scale_logo_5};
    private List<CryingScaleResponse.CryingScaleDatum> method_List;
    private List<List<String>> suggestionList;
    private List<String> levelList, titleList;
    private String suggestion;
    private ExpandableListAdapter adapter;

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


        container = (ViewGroup) getLayoutInflater().inflate(R.layout.choose_expand_header, expandableListView, false);

        expandableListView.addHeaderView(container);

        initCryingScale(view);
        setupCryingScale(view);

        setupExpandableView(method1);


        //Button to proceed the Timer Fragment

        proceed_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacementFragment(new Timer_frag());
            }
        });


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


    List<CryingScaleResponse.MethodOne> methodOne;

    private void setupExpandableView(CryingScaleResponse method1) {

        levelList = new ArrayList<>();
        titleList = new ArrayList<>();

        method_List = method1.getCryingScaleData();
        for (CryingScaleResponse.CryingScaleDatum methods : method_List) {
            methodOne = methods.getMethodOne();
        }

        for (CryingScaleResponse.MethodOne dataMethodOne : methodOne) {
            levelList.add(dataMethodOne.getLevel());
            titleList.add(dataMethodOne.getTitle());

            suggestionList.add(dataMethodOne.getSuggestion());
        }

        suggestion = method1.getCryingScaleLabel().getLabels().getSuggestion();

        adapter = new ExpandableListAdapter(getContext(), levelList, titleList, suggestionList, suggestion);

//        expListView.setAdapter(listAdapter);
//        listAdapter.notifyDataSetChanged();
//        //setting up the recycler
//        layoutManager = new GridLayoutManager(getContext(), 1);
//        recyclerView.setHasFixedSize(true);

//        //put list in recycler
//        recyclerView.setLayoutManager(layoutManager);

//        //call the recycler adapter
//        recyclerAdapt = new Recycler_adapt(image, levelList, titleList, suggestionList, suggestion, this);
//        recyclerView.setAdapter(recyclerAdapt);


    }


    class ExpandableListAdapter extends BaseExpandableListAdapter {

        Context context;
        private List<String> levelList;
        private List<String> titleList;
        private List<List<String>> suggestionList;
        private String suggestion;

        public ExpandableListAdapter(Context context, List<String> levelList, List<String> titleList, List<List<String>> suggestionList, String suggestion) {
            this.context = context;
            this.levelList = levelList;
            this.titleList = titleList;
            this.suggestionList = suggestionList;
            this.suggestion = suggestion;
        }

        @Override
        public int getGroupCount() {
            return levelList.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return 0;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }


    private void setupCryingScale(View view) {
        proceed_timer = view.findViewById(R.id.proceed);
        tb_title_recomm.setText(method1.getCryingScaleLabel().getHeader().getCryingScale());
        tb_title_recomm.setTypeface(regular);

    }

    private void initCryingScale(View view) {
        fragmentManager = getFragmentManager();
        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");
        //ButterKnife Library
        unbinder = ButterKnife.bind(this, view);
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
        unbinder.unbind();
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