package com.marvell.zoezhu.calculator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by zoezhu on 2015/10/20.
 */
public class KeypadAdapter extends BaseAdapter {

    private Context mContext;

    private View.OnClickListener mOnButtonClick;

    private KeypadButton[] mButtons = {
            KeypadButton.SEVEN,KeypadButton.EIGHT, KeypadButton.NINE, KeypadButton.C,
            KeypadButton.FOUR, KeypadButton.FIVE,KeypadButton.SIX, KeypadButton.DIV,
            KeypadButton.ONE, KeypadButton.TWO, KeypadButton.THREE,KeypadButton.MULTIPLY,
            KeypadButton.ZERO,KeypadButton.MINUS,KeypadButton.PLUS, KeypadButton.CALCULATE };

    public KeypadAdapter(Context context)
    {
        mContext = context;
    }
    public void setOnButtonClickListener(View.OnClickListener listener) {
        mOnButtonClick = listener;
    }
    @Override
    public int getCount() {
        return mButtons.length;
    }

    @Override
    public Object getItem(int position) {
        return mButtons[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Button btn;
        if (convertView == null)
        {
            btn = new Button(mContext);
            KeypadButton keypadButton = mButtons[position];
            btn.setOnClickListener(mOnButtonClick);
            btn.setBackgroundResource(R.drawable.keypadclear1);
            btn.setTag(keypadButton);
        }
        else
        {
            btn = (Button) convertView;
        }
        btn.setText(mButtons[position].getText());
        return btn;
    }

}
