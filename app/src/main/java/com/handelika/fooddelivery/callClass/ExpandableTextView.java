package com.handelika.fooddelivery.callClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.handelika.fooddelivery.R;

@SuppressLint("AppCompatCustomView")
public class ExpandableTextView extends TextView implements View.OnClickListener {

    private Context mContext;
    private static final int MAX_LINES = 3;
    private int currentMaxLines = Integer.MAX_VALUE;
    private  int color;

    public ExpandableTextView(Context context)
    {
        super(context);
        setOnClickListener(this);
        mContext = context;

    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setOnClickListener(this);
        mContext = context;
    }

    public ExpandableTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setOnClickListener(this);
        mContext=context;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter)
    {
        /* If text longer than MAX_LINES set DrawableBottom - I'm using '...' icon */
        post(new Runnable()
        {
            public void run()
            {
                if (getLineCount()>MAX_LINES) {
                    color = ThemeColors.getThemeColor(mContext);

                    @SuppressLint("UseCompatLoadingForDrawables")
                    Drawable dots = getResources().getDrawable(R.drawable.ic_baseline_more_dots_24);
                    dots.setTint(Color.BLUE);

                   // setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, dots);

                    setCompoundDrawables(null,null, null, dots);

                }
                else{
                setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

                setMaxLines(MAX_LINES);
            }
        });
    }


    @Override
    public void setMaxLines(int maxLines)
    {
        currentMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    /* Custom method because standard getMaxLines() requires API > 16 */
    public int getMyMaxLines()
    {
        return currentMaxLines;
    }

    @Override
    public void onClick(View v)
    {
        /* Toggle between expanded collapsed states */
        if (getMyMaxLines() == Integer.MAX_VALUE)
            setMaxLines(MAX_LINES);
        else
            setMaxLines(Integer.MAX_VALUE);
    }
}
