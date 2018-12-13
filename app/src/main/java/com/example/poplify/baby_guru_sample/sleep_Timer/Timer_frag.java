package com.example.poplify.baby_guru_sample.sleep_Timer;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.CustomViewPager;
import com.example.poplify.baby_guru_sample.adapter.MyAdapter;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.crying_scale.Crying_Scale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Timer_frag extends Fragment {

    @BindView(R.id.chronomter) Chronometer cmTimer;
    @BindView(R.id.btnstart) Button btnStart;
    @BindView(R.id.cry_sleep) Button cry_sleep_act;
    Boolean resume = false;
    long elapsedTime;
    private final Handler handler = new Handler();
    String TAG = "TAG";
    SaveData sd;
    String time_on_Pause,time_on_Resume;
    long minutes,seconds,diff;
    Date pauseDate,resumeDate;

    CustomViewPager viewPager;
    Typeface regular,regularMon;
    CircularProgressBar pb ;
    private int progressStatus = 0;
     Handler handler2;

    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    int page =0;

    @BindView(R.id.openGurutips) ImageButton guru_btn;

    MyAdapter md;



    public Timer_frag() {
        // Required empty public constructor
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//



    Thread timer_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {


                if (viewPager.getAdapter().getCount() == page) {
                    page = 0;
                }
                else
                {
                    page++;

                    viewPager.setCurrentItem(page);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_frag, container, false);

        FragmentManager fm =getChildFragmentManager();
        ButterKnife.bind(this,view);
        sd = new SaveData(getContext());


        handler2 = new Handler();


        viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        viewPager.setScrollDurationFactor(30);
       // viewPager.setPageTransformer(false, new SlideUpTransformer());
        md = new MyAdapter(fm,getContext());

        md.setFragments(this.getActivity());
        viewPager.setAdapter(md);
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount()-1);







        //*****************--------------------------------------------*****************************++++
        pb = view.findViewById(R.id.inner_timer);



        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(),"Montserrat-Regular.otf");

        view.setTag("Timer_frag");

        return view;
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //----------------------------------------------------------------setting up the progress bar thread---------------------------------------------------------------------------//



    public void initprogressBar()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    progressStatus += seconds;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            pb.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (minutes != 10 );
            }
        }).start();
    }



    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------start time on button click----------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


    @OnClick(R.id.btnstart)
    void startTimer(){

        try {
            runnable.run();


            timer_thread.start();
            initprogressBar();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @OnClick(R.id.cry_sleep)
    void startCryActivity()
    {
        Intent ol = new Intent(getContext(), Crying_Scale.class);

        startActivity(ol);
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------------------------clock timer setting thread-------------------------------------------------------------------//

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {


            if (!resume) {
                cmTimer.setBase(SystemClock.elapsedRealtime());
                cmTimer.start();
            } else {
                cmTimer.start();
            }
            //


  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
  //-------------------------------------setting up the chronometer-------------------------------------------------------------------------------------------------------------//


            cmTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
            {
                @Override
                public void onChronometerTick(Chronometer chronometer)
                {
                    try
                    {
                        if(!resume){
                            minutes = ((SystemClock.elapsedRealtime() - cmTimer.getBase())/1000) / 60;
                            seconds = ((SystemClock.elapsedRealtime() - cmTimer.getBase())/1000) % 60;
                            elapsedTime = SystemClock.elapsedRealtime();
                        }
                        else {
                            long minutes = ((elapsedTime - cmTimer.getBase())/1000) / 60;
                            long seconds = ((elapsedTime - cmTimer.getBase())/1000) % 60;
                            elapsedTime = elapsedTime + 1000;
                            Log.d(TAG, "onChronometerTick: " + minutes + " : " + seconds);
                        }

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            // act = getActivity();

            // handler.postDelayed(this, 1000);
        }
    };


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public void onResume() {





        DateFormat df = new SimpleDateFormat("HH:mm:ss"); //format time
        time_on_Resume = df.format(Calendar.getInstance().getTime());
        try {
            Log.d(TAG, "onResume: time onpause"+time_on_Resume);
            if(time_on_Pause!=null) {
                resumeDate = df.parse(time_on_Pause);
                long diff = resumeDate.getTime() - pauseDate.getTime();
                long Hours = diff/(1000 * 60 * 60);
                long Mins = diff/(1000*60) % 60;
                long Secs = (diff / 1000) % 60;

                minutes+=Mins;
                seconds+=Secs;
            }
            Log.d(TAG, "Differ IN Time : "+diff);
        } catch (Exception e) {
            e.printStackTrace();
        }


        super.onResume();
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public void onPause()
    {

        DateFormat df = new SimpleDateFormat("HH:mm:ss"); //format time
        time_on_Pause = df.format(Calendar.getInstance().getTime());
        try
        {
            pauseDate = df.parse(time_on_Pause);
            Log.d(TAG, "onChronometerTick: " + minutes + " : " + seconds);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        super.onPause();
    }




//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------Setting up the Guru Tips------------------------------------------------------


    @OnClick(R.id.openGurutips)
    public void tips()
    {

        android.support.v7.app.AlertDialog.Builder dia_builder = new android.support.v7.app.AlertDialog.Builder(getContext(),R.style.GuruTheme);

        View mview = getLayoutInflater().inflate(R.layout.dialog_guru,null);

        Button ok_guru = mview.findViewById(R.id.guru_ok);
        Button contact_guru =  mview.findViewById(R.id.guru_contact);
        TextView head_guru  =  mview.findViewById(R.id.guru_heading);


        //setting the text Font
        head_guru.setTypeface(regular);
        ok_guru.setTypeface(regularMon);
        contact_guru.setTypeface(regularMon);



        //Setting Up the Recycler View
        RecyclerView recyclerView = mview.findViewById(R.id.guru_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        List<String> headlist = Arrays.asList(getResources().getStringArray(R.array.c));
        List<String> bodylist = Arrays.asList(getResources().getStringArray(R.array.d));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Recycle_Guru recycle_guru = new Recycle_Guru(headlist,bodylist,mview);


        //call the recycler adapter

        recyclerView.setAdapter(recycle_guru);


        //show in the dialog box
        dia_builder.setView(mview);
        android.support.v7.app.AlertDialog dialog = dia_builder.create();
        dialog.show();

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
            TextView head_txt,body_txt;
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
                holder.body_txt.setText(s3);
            }
        }


        @Override
        public int getItemCount() {
            return head_list.size();
        }


    }




    private class SlideUpTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (-1 < position && position < 0) {
                float scaleFactor = 1 - Math.abs(position) * 0.1f;
                float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
                float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horizontalMargin - verticalMargin / 2);
                } else {
                    view.setTranslationX(-horizontalMargin + verticalMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            }

            view.setTranslationX(view.getWidth() * -position);

            if (position > 0) {
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
            }
        }
    }
}
