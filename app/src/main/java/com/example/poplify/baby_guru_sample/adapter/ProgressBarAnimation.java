package com.example.poplify.baby_guru_sample.adapter;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class ProgressBarAnimation extends Animation {
    private RoundCornerProgressBar mProgressBar;


    /**
     * @param fullDuration - time required to fill progress from 0% to 100%
     */
  /*
   private int mFrom;
    private int  mTo;
    private long mStepDuration;


    public ProgressBarAnimation(ProgressBar progressBar, long fullDuration) {
        super();
        mProgressBar = progressBar;
        mStepDuration = fullDuration / progressBar.getMax();
    }


    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }

        if (progress > mProgressBar.getMax()) {
            progress = mProgressBar.getMax();
        }

        mTo = progress;

        mFrom = mProgressBar.getProgress();
        setDuration(Math.abs(mTo - mFrom) * mStepDuration);
        mProgressBar.startAnimation(this);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float value = mFrom + (mTo - mFrom) * interpolatedTime;
        mProgressBar.setProgress((int) value);
    }*/



        private float from;
        private float  to;

        public ProgressBarAnimation(RoundCornerProgressBar progressBar, float from, int to) {
            super();
            this.mProgressBar = progressBar;
            this.from = from;
            this.to = to;
        }

    @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            if(from<to) {
                float value = from + (to - from) * interpolatedTime;
                mProgressBar.setProgress((int) value);
            }
                    }


}
