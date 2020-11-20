package com.z.lib_core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.z.lib_core.R;


public class RoundConstraintLayout extends ConstraintLayout {

    protected float mAspectRatio;
    private int mColor;
    private float mRound, mRoundLeftTop, mRoundLeftBottom, mRoundRightTop, mRoundRightBottom;

    public RoundConstraintLayout(Context context) {
        this(context, null);
    }

    public RoundConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        mAspectRatio = typedArray.getFloat(R.styleable.RoundView_AspectRatio, 0f);
        mColor = typedArray.getColor(R.styleable.RoundView_Color, Color.TRANSPARENT);
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
        if (heightMeasureSpec == 0) {
            if (mAspectRatio != 0) {
                heightMeasureSpec = (int) (widthMeasureSpec * mAspectRatio);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);//父控件的测量模式
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);//父控件的测量模式

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int out_with = width;
        int out_height = height;
        if (widthSpecMode != MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
            //宽度非精确，高度精确
            if (mAspectRatio != 0) {
                out_with = (int) (height / mAspectRatio);
            }
        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode != MeasureSpec.EXACTLY) {
            //宽度精确，高度非精确
            if (mAspectRatio != 0) {
                out_height = (int) (width * mAspectRatio);
            }
        }
        setMeasuredDimension(out_with, out_height);
    }

    protected void setRound(float round) {
        this.mRound = round;
    }

    protected void setRound(float roundLeftTop, float roundRightTop, float roundLeftBottom, float roundRightBottom) {
        this.mRoundLeftTop = roundLeftTop;
        this.mRoundRightTop = roundRightTop;
        this.mRoundLeftBottom = roundLeftBottom;
        this.mRoundRightBottom = roundRightBottom;
    }

    private float getRound(float round) {
        return round == 0 ? mRound : round;
    }

    public void updateRound(float round) {
        mRound = round;
        setBackgroundColor(mColor);
    }

    public void updateRound(float roundLeftTop, float roundRightTop, float roundLeftBottom, float roundRightBottom) {
        setRound(roundLeftTop, roundRightTop, roundLeftBottom, roundRightBottom);
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
