package com.z.lib_core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.z.lib_core.R;


public class RoundTextView extends AppCompatTextView {

    private float mAspectRatio;
    private float mRound, mRoundLeftTop, mRoundLeftBottom, mRoundRightTop, mRoundRightBottom;
    private int mStyle;
    private int mStrokeWidth;
    private int mStrokeColor;
    private int mColor;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        boolean hasColor = typedArray.hasValue(R.styleable.RoundView_Color);
        mColor = typedArray.getColor(R.styleable.RoundView_Color, Color.TRANSPARENT);
        mStrokeColor = typedArray.getColor(R.styleable.RoundView_StrokeColor, Color.TRANSPARENT);
        mStyle = typedArray.getInt(R.styleable.RoundView_StyleType, 0);
        mStrokeWidth = (int) typedArray.getDimension(R.styleable.RoundView_StrokeWidth, 0);
        mAspectRatio = typedArray.getFloat(R.styleable.RoundView_AspectRatio, 0f);
        mRound = typedArray.getDimension(R.styleable.RoundView_Round, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mRoundLeftTop = typedArray.getDimension(R.styleable.RoundView_RoundLeftTop, 0);
        mRoundRightTop = typedArray.getDimension(R.styleable.RoundView_RoundRightTop, 0);
        mRoundLeftBottom = typedArray.getDimension(R.styleable.RoundView_RoundLeftBottom, 0);
        mRoundRightBottom = typedArray.getDimension(R.styleable.RoundView_RoundRightBottom, 0);
        typedArray.recycle();

        if (hasColor) {
            setBackgroundColor(mColor);
        }
    }

    private float getRound(float round) {
        return round == 0 ? mRound : round;
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
        mColor = color;
        float[] outerRadian = new float[]{
                getRound(mRoundLeftTop), getRound(mRoundLeftTop),
                getRound(mRoundRightTop), getRound(mRoundRightTop),
                getRound(mRoundRightBottom), getRound(mRoundRightBottom),
                getRound(mRoundLeftBottom), getRound(mRoundLeftBottom),
        };

        if (mStyle == Style.FILL) {
            RoundRectShape shape = new RoundRectShape(outerRadian, null, null);
            ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
            shapeDrawable.getPaint().setColor(mColor);
            setBackground(shapeDrawable);
        } else if (mStyle == Style.STROKE) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadii(outerRadian);
            drawable.setStroke(mStrokeWidth, mStrokeColor);
            drawable.setColor(mColor);
            setBackground(drawable);
        }
    }
}
