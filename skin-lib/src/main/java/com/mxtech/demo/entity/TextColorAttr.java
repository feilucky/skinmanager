package com.mxtech.demo.entity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mxtech.demo.loader.SkinManager;


public class TextColorAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            Log.d("zhanfei","TextColorAttr.attrValueTypeName......" + attrValueTypeName );
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                Log.d("zhanfei","TextColorAttr.colorId....." + attrValueRefId);
                tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
            }
        }
    }
}
