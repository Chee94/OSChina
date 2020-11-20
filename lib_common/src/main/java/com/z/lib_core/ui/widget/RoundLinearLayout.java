package com.z.lib_core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.z.lib_core.R;


public class RoundLinearLayout extends LinearLayout {

    private float mAspectRatio;
    private int mColor;
    private float mRound, mRoundLeftTop, mRoundLeftBottom, mRoundRightTop, mRoundRightBottom;

    public RoundLinearLayout(Context context) {
        this(context, null);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        mColor = typedArray.getColor(R.styleable.RoundView_Color, Color.TRANSPARENT);
        mAspectRatio = typedArray.getFloat(R.styleable.RoundView_AspectRatio, 0f);
        mRound = typedArray.getDimension(R.styleable.RoundView_Round, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mRoundLeftTop = typedArray.getDimension(R.styleable.RoundView_RoundLeftTop, 0);
        mRoundRightTop = typedArray.getDimension(R.styleable.RoundView_RoundRightTop, 0);
        mRoundLeftBottom = typedArray.getDimension(R.styleable.RoundView_RoundLeftBottom, 0);
        mRoundRightBottom = typedArray.getDimension(R.styleable.RoundView_RoundRightBottom, 0);
        typedArray.recycle();

        float[] outerRadian = new float[]{
                getRound(mRoundLeftTop), getRound(mRoundLeftTop),
                getRound(mRoundRightTop), getRound(mRoundRightTop),
                getRound(mRoundLeftBottom), getRound(mRoundLeftBottom),
                getRound(mRoundRightBottom), getRound(mRoundRightBottom),
        };
        RoundRectShape shape = new RoundRectShape(outerRadian, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(mColor);
        setBackground(shapeDrawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mAspectRatio != 0) {
            float height = width * mAspectRatio;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float getRound(float round) {
        return round == 0 ? mRound : round;
    }

    public void updateRound(float round) {
        mRound = round;
        setBackgroundColor(mColor);
    }

    public void updateRound(float leftTopRound, float rightTopRound, float leftBottomRound, float rightBottomRound) {
        if (leftTopRound >= 0) {
            mRoundLeftTop = leftTopRound;
        }

        if (rightTopRound >= 0) {
            mRoundRightTop = rightTopRound;
        }

        if (leftBottomRound >= 0) {
            mRoundLeftBottom = leftBottomRound;
        }
        if (rightBottomRound >= 0) {
            mRoundRightBottom = rightBottomRound;
        }

        setBackgroundColor(mColor);
    }

    @Override
    public void setBackgroundColor(int color) {
        float[] outerRadian = new float[]{
                getRound(mRoundLeftTop), getRound(mRoundLeftTop),
                getRound(mRoundRightTop), getRound(mRoundRightTop),
                getRound(mRoundRightBottom), getRound(mRoundRightBottom),
                getRound(mRoundLeftBottom), getRound(mRoundLeftBottom),
        };
        RoundRectShape shape = new RoundRectShape(outerRadian, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(color);
        setBackground(shapeDrawable);
    }
}
