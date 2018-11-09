package com.example.poplify.baby_guru_sample.choose_method;


import android.app.Service;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.Recycler_adapt;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;
//import com.example.poplify.baby_guru_sample.adapter.Service_class_music;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
    public class Choose_mthd_recomm_frag extends Fragment implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener
{



    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;
    @BindView(R.id.music_bar)  SeekBar seekbar;
    @BindView(R.id.start_time)  TextView start_txt;
    @BindView(R.id.end_time)  TextView end_txt;
    @BindView(R.id.toolbar_title) TextView tb_title_recomm;

    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private int startTime = 0;
    private int finalTime = 0;
    public static int oneTimeOnly = 0;
    Typeface regular, regularMon;

    @BindView(R.id.recycler)  RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Recycler_adapt recyclerAdapt;
    @BindView(R.id.play_btn)  ImageButton btn_4_music;
    private int image = R.drawable.choose_method_bullet;
    private List<String> list;

    Button proceed_timer;
    FragmentManager fragmentManager;

    private static final String TAG = "Choose_mthd_recomm_frag";
    public Choose_mthd_recomm_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_mthd_recomm_frag, container, false);

        fragmentManager = getFragmentManager();
        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        //ButterKnife Library
        unbinder = ButterKnife.bind(this, view);

        //setting up the recycler
        layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setHasFixedSize(true);
        //put list in recycler
        list = Arrays.asList(getResources().getStringArray(R.array.a));
        recyclerView.setLayoutManager(layoutManager);
        //call the recycler adapter
        recyclerAdapt = new Recycler_adapt(image,list,this);
        recyclerView.setAdapter(recyclerAdapt);


        tb_title_recomm.setText(getResources().getString(R.string.recommeded));
        tb_title_recomm.setTypeface(regular);

        //Button to proceed the Timer Fragment

        proceed_timer = view.findViewById(R.id.proceed);
        proceed_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacementFragment(new Timer_frag());
            }
        });



        try {

            mediaPlayer = MediaPlayer.create(getContext(),R.raw.maroon);
            mediaPlayer.setOnCompletionListener( this);
            seekbar.setClickable(false);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //playMusic = new Intent(getActivity(), Service_class_music.class);

        return view;
    }

    private void replacementFragment(Fragment fragment)
    {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack,0);

        Log.d("", "replacementFragment: fragmentPopped"+fragmentPopped);
        try {
            if (fragmentPopped!=true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //=====================================================================================//
    //================================Setting up the Music Player==========================//

    @OnClick(R.id.play_btn)
    void play_music_on_click()
    {
        Toast.makeText(getContext(), "Playing  sound",Toast.LENGTH_SHORT).show();

        if(!mediaPlayer.isPlaying())
        {
            try
            {
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

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        //getActivity().finish();

    }
    //------------------------------------------------------------------------------------------------------//




    //=====================================================================================//
    //================================Setting up the runnable for music Player==========================//


    private Runnable UpdateSongTime = new Runnable()
    {
        public void run()
        {
            try {


                if(mediaPlayer.isPlaying())
                {
                    startTime = mediaPlayer.getCurrentPosition();
                    finalTime = mediaPlayer.getDuration();
                    updatePlayer(startTime,finalTime);

                    seekbar.setProgress((int) startTime);
                    myHandler.postDelayed(this, 100);

                }
               /* start_txt.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) startTime)))
                );*/

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };
//------------------------------------------------------------------------------------------------------//






    //=====================================================================================//
    //========================method For updating start time and Runtime of media player=========================//

    private void updatePlayer(int currentDuration,int final_time){
        start_txt.setText("" + milliSecondsToTimer((long) currentDuration));

        Log.d(TAG, "updatePlayer: ennd duration of song"+mediaPlayer.getDuration());
        end_txt.setText(""+ milliSecondsToTimer((long) final_time));
    }

    public  String milliSecondsToTimer(long milliseconds) {
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
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        //mediaPlayer.release();

        super.onPause();
    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if(mediaPlayer.isPlaying())
        {
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
}
/*
class Service_class_music extends Service implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        try {

            mediaPlayer = MediaPlayer.create(this, R.raw.maroon);
            mediaPlayer.setOnCompletionListener( this);
            seekbar.setClickable(false);
            super.onCreate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!mediaPlayer.isPlaying())
        {
          try
            {
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                start_txt.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                end_txt.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();

    }

    @Override
    public void onDestroy() {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        super.onDestroy();
    }



    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopSelf();
    }
}

*/