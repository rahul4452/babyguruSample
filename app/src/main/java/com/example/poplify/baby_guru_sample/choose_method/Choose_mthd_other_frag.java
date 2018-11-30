package com.example.poplify.baby_guru_sample.choose_method;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
public class Choose_mthd_other_frag extends Fragment implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {


    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;
    private @BindView(R.id.music_bar_other)
    SeekBar seekbar;
    private @BindView(R.id.start_time_other)
    TextView start_txt;
    private @BindView(R.id.end_time_other)
    TextView end_txt;

    private Handler myHandler = new Handler();
    ;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;


    private @BindView(R.id.recycler_other)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Recycler_adapt recyclerAdapt;
    private @BindView(R.id.play_btn_other)
    ImageButton btn_4_music;
    private int image = R.drawable.choose_method_bullet;
    private List<String> list;
    Button timer_btn;
    FragmentManager fragmentManager;


    public Choose_mthd_other_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_mthd_other_frag, container, false);

        fragmentManager = getFragmentManager();

        //ButterKnife Library
        unbinder = ButterKnife.bind(this, view);

        //setting up the recycler
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        //put list in recycler
        list = Arrays.asList(getResources().getStringArray(R.array.b));
        recyclerView.setLayoutManager(layoutManager);
        //call the recycler adapter
//        recyclerAdapt = new Recycler_adapt(image, list, this);
//        recyclerView.setAdapter(recyclerAdapt);


        //Timer Fragment Button
        timer_btn = view.findViewById(R.id.proceed_other);
        timer_btn.setOnClickListener(new View.OnClickListener() {
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

    @OnClick(R.id.play_btn_other)
    void play_music_on_click() {
        Toast.makeText(getContext(), "Playing  sound", Toast.LENGTH_SHORT).show();

        if (!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.start();

                //mediaPlayer.setOnPreparedListener(this);
                //mediaPlayer.prepareAsync();
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

                seekbar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //getActivity().finish();

    }

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
