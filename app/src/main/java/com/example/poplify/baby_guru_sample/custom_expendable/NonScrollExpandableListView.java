package com.example.poplify.baby_guru_sample.custom_expendable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class NonScrollExpandableListView extends ExpandableListView {


    //Gets the property of non scrollable of expendbleLv
    //lv expand hoga sath main screen bhi scroll hogi
    //use inside Scrollview or NestedScrollView

    public NonScrollExpandableListView(Context context) {
        super(context);
    }

    public NonScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
