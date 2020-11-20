package com.z.lib_core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.z.lib_core.R;


/**
 * Desc:
 * Created by 庞承晖
 * Date: 2018-09-20.
 * Time: 17:30
 */
public class RoundRelativeLayout extends RelativeLayout {

    private int mColor;
    private float mRound, mRoundLeftTop, mRoundLeftBottom, mRoundRightTop, mRoundRightBottom;

    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        int color = typedArray.getColor(R.styleable.RoundView_Color, Color.TRANSPARENT);
        mRound = typedArray.getDimension(R.styleable.RoundView_Round, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mRoundLeftTop = typedArray.getDimension(R.styleable.RoundView_RoundLeftTop, 0);
        mRoundRightTop = typedArray.getDimension(R.styleable.RoundView_RoundRightTop, 0);
        mRoundLeftBottom = typedArray.getDimension(R.styleable.RoundView_RoundLeftBottom, 0);
        mRoundRightBottom = typedArray.getDimension(R.styleable.RoundView_RoundRightBottom, 0);
        typedArray.recycle();
        setBackgroundColor(color);
    }

    private float getRound(float round) {
        return round == 0 ? mRound : round;
    }

    public void setRound(float round) {
        mRoundLeftTop = round;
        mRoundRightTop = round;
        mRoundLeftBottom = round;
        mRoundRightBottom = round;
        invalidate();
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
        mColor = color;
        float[] outerRadian = new float[]{
                getRound(mRoundLeftTop), getRound(mRoundLeftTop),
                getRound(mRoundRightTop), getRound(mRoundRightTop),
                getRound(mRoundRightBottom), getRound(mRoundRightBottom),
                getRound(mRoundLeftBottom), getRound(mRoundLeftBottom),
        };
        RoundRectShape shape = new RoundRectShape(outerRadian, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(mColor);
        setBackground(shapeDrawable);
    }
}
