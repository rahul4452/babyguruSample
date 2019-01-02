package com.example.poplify.baby_guru_sample.sleep_Timer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.ankushgrover.hourglass.Hourglass;
import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.Common;
import com.example.poplify.baby_guru_sample.adapter.MyAdapter;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.response.GetTimerResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Timer_frag extends Fragment implements View.OnClickListener {


    TextView cmTimer;
    CircularProgressBar progressOuter, progressInner;
    TextView selfText, totalText;
    SaveData saveData;
    Button startTimer, closeTimer;
    LinearLayout showCryingScale;
    Button stopTimer, cryingScale;
    ProgressBar timerPb;
    Typeface regular, regularMon;
    Hourglass hourglass;
    Boolean resume = false;
    long elapsedTime;
    private final Handler handler = new Handler();
    String TAG = "TAG";
    SaveData sd;
    String time_on_Pause, time_on_Resume;

    Date pauseDate, resumeDate;
    RecyclerView viewPager;
    RecyclerView.Adapter timerAdapter;
    private int progressStatus = 0;
    Handler handler2;
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    int page = 0;
    ImageButton guru_btn;
    MyAdapter md;
    private TextView timerHeader;
    private int childId;
    private GetTimerResponse timerDetails;
    private LinearLayout layout,cryingLayout;
    private ImageView pauseTimer;
    private LinearLayoutManager mLayoutManager;


    public Timer_frag() {
        // Required empty public constructor

    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


    Thread timer_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {


//                if (viewPager.getAdapter().getCount() == page) {
//                    page = 0;
//                } else {
//                    page++;
//
//                   // viewPager.setCurrentItem(page);
//                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    });


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saveData = new SaveData(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            childId = bundle.getInt("childId");
        }

    }

    private void getDetailsFromServer(final View view) {

        try {
            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            timerPb.setVisibility(View.VISIBLE);

            String token_header = saveData.get("login_token");
            String email_header = saveData.get("login_email");
            Call<GetTimerResponse> responseCall = service.timerActivityDetail(token_header, email_header, childId);

            responseCall.enqueue(new Callback<GetTimerResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onResponse(@NonNull Call<GetTimerResponse> call, @NonNull Response<GetTimerResponse> response) {

                    boolean success = response.isSuccessful();
                    timerDetails = response.body();

                    String sa = timerDetails != null ? timerDetails.getBeforeYouStart().getDescription() : null;
                    if (response.code() == 404) {
                        try {
                            timerPb.setVisibility(View.VISIBLE);
                            JSONObject jObjError = new JSONObject(Objects.requireNonNull(response.errorBody()).string());
                            TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                            TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            textView.setGravity(Gravity.CENTER_HORIZONTAL);
                            Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                            snackbar.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        timerPb.setVisibility(View.GONE);
                        Log.d(TAG, "onResponse: response Body" + response.body());
                        setResponseFromServer(timerDetails);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<GetTimerResponse> call, @NonNull Throwable t) {
                    timerPb.setVisibility(View.GONE);
                    call.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    boolean isactive = false;
    Common common = new Common();
    Common common1 = new Common();
    Common common2 = new Common();
    Integer activeMethodid;
    List<GetTimerResponse.Card> cardList;
    List<GetTimerResponse.InfiniteCard> infiniteCards;
    List<Common> commons = new ArrayList<>();


    private void setResponseFromServer(GetTimerResponse timerDetails) {

        GetTimerResponse.Header header = timerDetails.getSleepCoachingLabels().getHeader();

        GetTimerResponse.Buttons button = timerDetails.getSleepCoachingLabels().getButtons();

        timerHeader.setText(header.getSleepCoaching());

        startTimer.setText(button.getStart());

        closeTimer.setText(button.getBabyIsAsleep());

        stopTimer.setText(button.getBabyIsAsleep());

        cryingScale.setText(button.getCryingScale());


        String intialCard = timerDetails.getSleepCoachingDetails().getInitialCard().getInitialCard();
        common.setIntialCard(intialCard);
        commons.add(common);
        timerAdapter = new TimerRecycle(getContext(), commons);
        viewPager.setAdapter(timerAdapter);



        List<GetTimerResponse.ChooseMethod> card = timerDetails.getSleepCoachingDetails().getChooseMethods();

        for (GetTimerResponse.ChooseMethod hi : card) {
            isactive = hi.getActive();
            if (isactive) {
                activeMethodid = hi.getId();
                cardList = hi.getCards();
                infiniteCards = hi.getInfiniteCards();
            }
        }

        for (GetTimerResponse.Card card2 : cardList) {

            common1.setIntialCard(card2.getDetails());

        }

        for (GetTimerResponse.InfiniteCard infiniteCard : infiniteCards) {
            // Common common = new Common();
            common2.setInfiniteCard(infiniteCard.getDetails());
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_frag, container, false);

        FragmentManager fm = getChildFragmentManager();

        sd = new SaveData(getContext());

        initView(view);

//        getActivity().startService(new Intent(getActivity(), BroadcastService.class));
        //handler2 = new Handler();

        //viewPager.setScrollDurationFactor(30);
        // viewPager.setPageTransformer(false, new SlideUpTransformer());
//        md = new MyAdapter(fm, getContext());
//
//        md.setFragments(this.getActivity());

//        viewPager.setAdapter(md);
//
//        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount() - 1);

        view.setTag("Timer_frag");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDetailsFromServer(view);
    }

    private void initView(View view) {
        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        viewPager = view.findViewById(R.id.pager);
        viewPager.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        viewPager.setLayoutManager(mLayoutManager);


        timerHeader = view.findViewById(R.id.toolbar_title);

        //*****************--------------------------------------------*****************************++++
        progressOuter = view.findViewById(R.id.outer_timer);
        progressInner = view.findViewById(R.id.inner_timer);

        cmTimer = view.findViewById(R.id.chronomter);

        totalText = view.findViewById(R.id.total_text);
        totalText.setTypeface(regularMon);

        selfText = view.findViewById(R.id.self_text);
        selfText.setTypeface(regularMon);

        showCryingScale = view.findViewById(R.id.buttonLayout);

        startTimer = view.findViewById(R.id.btnstart);
        closeTimer = view.findViewById(R.id.btn_baby_sleep);

        if(saveData.getBoolean("timerStart")){
            startTimer.setVisibility(View.GONE);
            closeTimer.setVisibility(View.VISIBLE);
        }



//        if (startTimer.getVisibility()==View.GONE) {
//            startTimer.setVisibility(View.GONE);
//        } else {
//            startTimer.setVisibility(View.VISIBLE);
//        }

        //        if (startTimer.getVisibility()==View.VISIBLE) {
//            startTimer.setVisibility(View.GONE);
//        } else {
//            startTimer.setVisibility(View.VISIBLE);
//        }
        startTimer.setTypeface(regular);
        startTimer.setOnClickListener(this);


        pauseTimer = view.findViewById(R.id.pauseTimer);
        pauseTimer.setOnClickListener(this);


//        if (startTimer.getVisibility()==View.GONE) {
//            closeTimer.setVisibility(View.VISIBLE);
//        } else {
//            closeTimer.setVisibility(View.GONE);
//        }

//        if(closeTimer.getVisibility()==View.VISIBLE){
//            closeTimer.setVisibility(View.GONE);
//        }
//        else{
//            closeTimer.setVisibility(View.VISIBLE);
//        }


        closeTimer.setTypeface(regular);
        closeTimer.setOnClickListener(this);

        stopTimer = view.findViewById(R.id.btn_baby_sleep2);
        stopTimer.setOnClickListener(this);
        stopTimer.setTypeface(regular);

        cryingScale = view.findViewById(R.id.cry_sleep);
        cryingScale.setOnClickListener(this);
        cryingScale.setTypeface(regular);

        timerPb = view.findViewById(R.id.timerProgressbar);
        timerPb.setVisibility(View.VISIBLE);

        layout = view.findViewById(R.id.linearLayout7);
        cryingLayout = view.findViewById(R.id.twoButtonLayout);

        if(startTimer.getVisibility()==View.GONE && closeTimer.getVisibility()==View.GONE){
            cryingLayout.setVisibility(View.VISIBLE);
        }

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnstart:

                commons.clear();
                commons.add(common1);
                timerAdapter.notifyDataSetChanged();
                saveData.save("timerStart",true);
                //timerPb.setVisibility(View.VISIBLE);
                if(saveData.getBoolean("timerStart"))
                {
                    startTimer.setVisibility(View.GONE);
                    closeTimer.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                }
//                if (startTimer.getVisibility() == View.VISIBLE) {
//                    startTimer.setVisibility(View.GONE);
//                }
//
//                if (closeTimer.getVisibility() == View.GONE) {
//                    closeTimer.setVisibility(View.VISIBLE);
//                }
//
//                pauseTimer.setImageResource(R.mipmap.pause_timer);
//
//                if (layout.getVisibility() == View.GONE) {
//                    layout.setVisibility(View.VISIBLE);
//                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            timerPb.setVisibility(View.GONE);
                            startTimerfunc();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);

                break;

            case R.id.btn_baby_sleep:

                timerStop();


                break;

            case R.id.pauseTimer:
                timerPause();


                break;

            case R.id.btn_baby_sleep2:

                break;

        }

    }

    private void timerPause() {

        hourglass.pauseTimer();

    }

    private void timerStop() {

        hourglass.stopTimer();

    }


    void startTimerfunc() {
        try {

            hourglass = new Hourglass(14400000, 1000) {
                @SuppressLint("SetTextI18n")
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onTimerTick(long millis) {
                    try {
                        final NumberFormat f = new DecimalFormat("00");
                        final long hour = (millis / 3600000) % 24;
                        final long min = (millis / 60000) % 60;
                        final long sec = (millis / 1000) % 60;

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        Date startDate = simpleDateFormat.parse(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                        Date endDate = simpleDateFormat.parse("04:00:00");

                        long difference = endDate.getTime() - startDate.getTime();

                        //seconds
                        int just_seconds = (int) (difference / 1000);

                        //minutes
                        int just_minutes = just_seconds / 60;

                        if (just_minutes >= 1) {
                            just_seconds = (int) (difference - ((60 * 1000) * just_minutes)) / 1000;
                        }

                        //hours
                        int just_hours = just_minutes / 60;

                        new HMS(just_seconds, just_minutes, just_hours);
                        cmTimer.setText(f.format(just_hours) + ":" + f.format(just_minutes) + ":" + f.format(just_seconds));
                    } catch (ParseException p) {
                        p.printStackTrace();
                    }

                }

                @Override
                public void onTimerFinish() {

                    cmTimer.setText("done");
                } //14400000 milisecond for 4 hours

//                @RequiresApi(api = Build.VERSION_CODES.N)
//                @SuppressLint("SetTextI18n")
//                public void onTick(long millis) {
//                }
//
//                public void onFinish() {
//
//                }

            };

            hourglass.startTimer();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    private class TimerProgress extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... sUrl) {
            try {

            } catch (Exception e) {
                // Error Log
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

    class HMS {
        int sec, min, hours;

        public HMS(int sec, int min, int hours) {
            this.sec = sec;
            this.min = min;
            this.hours = hours;
        }

        public int getSec() {
            return sec;
        }

        public int getMin() {
            return min;
        }

        public int getHours() {
            return hours;
        }
    }

    class TimerRecycle extends RecyclerView.Adapter<TimerRecycle.TimerHolder> {


        List<Common> initial_card;
        Context context;

        public TimerRecycle(Context context, List<Common> cards) {

            this.context = context;
            this.initial_card = cards;

        }

        class TimerHolder extends RecyclerView.ViewHolder {

            TextView cardText;

            public TimerHolder(@NonNull View itemView) {
                super(itemView);
                cardText = itemView.findViewById(R.id.pager_inner_text);
            }
        }


        @NonNull
        @Override
        public TimerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pager_frag_1, viewGroup, false);

            return new TimerHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull TimerHolder timerHolder, int i) {

//            if(initial_card.get(i).getIntialCard() != null) {
                timerHolder.cardText.setText(initial_card.get(i).getIntialCard());
//            }else {
//                timerHolder.cardText.setText();
//            }

        }


        @Override
        public int getItemCount() {
            return initial_card.size();
        }
    }

    //    public class Recycle_Guru extends RecyclerView.Adapter<Recycle_Guru.Guru_holder> {
//
//        List<String> head_list, body_list;
//        View view;
//        Resources res;
//
//
//        public Recycle_Guru(List<String> head_list, List<String> body_list, View view) {
//            this.head_list = head_list;
//            this.body_list = body_list;
//            this.view = view;
//        }
//
//        public class Guru_holder extends RecyclerView.ViewHolder {
//            TextView head_txt, body_txt;
//            View v1, v2;
//
//            public Guru_holder(@NonNull View itemView) {
//                super(itemView);
//                head_txt = itemView.findViewById(R.id.text_head);
//                body_txt = itemView.findViewById(R.id.text_body);
//                v1 = itemView.findViewById(R.id.view);
//                v2 = itemView.findViewById(R.id.view2);
//            }
//        }
//
//
//        @NonNull
//        @Override
//        public Guru_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_guru_layout, viewGroup, false);
//            Guru_holder guru_holder = new Guru_holder(view);
//            return guru_holder;
//        }
//
//
//        @Override
//        public void onBindViewHolder(@NonNull Guru_holder holder, int i) {
//            res = holder.itemView.getContext().getResources();
//            String s2 = head_list.get(i);
//            String s3 = body_list.get(i);
//
//            int size = head_list.size() - 1;
//
//            if (i == size) {
//                holder.head_txt.setVisibility(View.GONE);
//                holder.v1.setVisibility(View.GONE);
//                holder.v2.setVisibility(View.GONE);
//            } else {
//                holder.head_txt.setText(s2);
//                holder.body_txt.setText(s3);
//            }
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return head_list.size();
//        }
//
//
//    }
//
//
//    private class SlideUpTransformer implements ViewPager.PageTransformer {
//        @Override
//        public void transformPage(View view, float position) {
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//
//            if (-1 < position && position < 0) {
//                float scaleFactor = 1 - Math.abs(position) * 0.1f;
//                float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horizontalMargin - verticalMargin / 2);
//                } else {
//                    view.setTranslationX(-horizontalMargin + verticalMargin / 2);
//                }
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//            }
//
//            view.setTranslationX(view.getWidth() * -position);
//
//            if (position > 0) {
//                float yPosition = position * view.getHeight();
//                view.setTranslationY(yPosition);
//            }
//        }
//    }
}
