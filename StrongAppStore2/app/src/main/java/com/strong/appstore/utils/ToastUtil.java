package com.strong.appstore.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.appstore.R;

public class ToastUtil {
    private static ToastUtil mInstance = new ToastUtil();
    private Context mContext;
    private Toast mToast;
    private TextView mTextView;

    private ToastUtil() {
    }

    public static ToastUtil getInstance() {
        return mInstance;
    }

    public void initialize(Context context) {
        mContext = context;
    }

    public void showToast(int resID) {
        if (mToast == null) {
            initToast();
        }
        mTextView.setText(resID);
        mToast.show();
    }

    public void showToast(String message) {
        if (mToast == null) {
            initToast();
        }
        mTextView.setText(message);
        mToast.show();
    }

    private void initToast() {
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 100);

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_toast, null);
        mTextView = (TextView) view.findViewById(R.id.text_toast);
        mToast.setView(view);
    }

    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
//        return input;
    }
}
