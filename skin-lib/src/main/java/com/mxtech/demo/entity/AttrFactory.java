package com.mxtech.demo.entity;


import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;

import java.util.ArrayList;
import java.util.List;

public class AttrFactory {

    public static final String TAG = "AttrFactory";
    public static final String BACKGROUND = "background";
    public static final String TEXT_COLOR = "textColor";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String DIVIDER = "divider";
    public static final String STYLE = "style";

    public static SkinAttr get(Context context,String attrName, int attrValueRefId, String attrValueRefName, String typeName) {

        SkinAttr mSkinAttr = null;

        if (BACKGROUND.equals(attrName)) {
            mSkinAttr = new BackgroundAttr();
        } else if (TEXT_COLOR.equals(attrName)) {
            mSkinAttr = new TextColorAttr();
        } else if (LIST_SELECTOR.equals(attrName)) {
            mSkinAttr = new ListSelectorAttr();
        } else if (DIVIDER.equals(attrName)) {
            mSkinAttr = new DividerAttr();
        } else if (STYLE.equalsIgnoreCase(attrName)) {
            mSkinAttr = new StyleAttr();
            paraseStyleNode(context, (StyleAttr) mSkinAttr,attrValueRefId);
            return mSkinAttr;
        } else {
            return null;
        }

        mSkinAttr.attrName = attrName;
        mSkinAttr.attrValueRefId = attrValueRefId;
        mSkinAttr.attrValueRefName = attrValueRefName;
        mSkinAttr.attrValueTypeName = typeName;
        return mSkinAttr;
    }

    /**
     * Check whether the attribute is supported
     *
     * @param attrName
     * @return true : supported <br>
     * false: not supported
     */
    public static boolean isSupportedAttr(String attrName) {
        if (BACKGROUND.equals(attrName)) {
            return true;
        }
        if (TEXT_COLOR.equals(attrName)) {
            return true;
        }
        if (LIST_SELECTOR.equals(attrName)) {
            return true;
        }
        if (DIVIDER.equals(attrName)) {
            return true;
        }

        if (STYLE.equals(attrName)) {
            return true;
        }
        return false;
    }

    public static List<SkinAttr> paraseStyleNode(Context context, StyleAttr styleAttr,int id) {
        //typedArray.string: #ff000000
        //typedArray.resourceId: 2131034161
        //typedArray.typeName: color
        //typedArray.resourceEntryName: color_text_1
        List<SkinAttr> skinAttrs = new ArrayList<>();
        SkinAttr skinAttr;
        TypedArray typedArray = context.getResources().obtainTypedArray(id);
        for (int j = 0; j < typedArray.length(); j++) {
            String string = typedArray.getString(j);
            Log.d("zhanfei", TAG + ".typedArray.string: " + string);
            int resourceId = typedArray.getResourceId(j, 0);
            Log.d("zhanfei", TAG + ".typedArray.resourceId: " + resourceId);
            if (resourceId > 0) {
                String typeName = context.getResources().getResourceTypeName(resourceId);
                Log.d("zhanfei", TAG + ".typedArray.typeName: " + typeName);
                if ("color".equalsIgnoreCase(typeName)) {
                    skinAttr = new TextColorAttr();
                    skinAttr.attrName = "";
                    skinAttr.attrValueTypeName = "color";
                    skinAttr.attrValueRefId = resourceId;
                    skinAttr.attrValueRefName = "";
                    skinAttrs.add(skinAttr);
                }
            }

        }
        styleAttr.skinAttrs = skinAttrs;

        //===========================================//
        String pkg = context.getResources().getResourcePackageName(id);
        String layout = context.getResources().getResourceEntryName(id);
        Log.d("zhanfei",TAG + ". paraseStyleNode.pkg: " + pkg + ",id= " + id);
        Log.d("zhanfei",TAG + ". paraseStyleNode.layout: " + layout);
        XmlResourceParser parser = context.getResources().getLayout(id);
        AttributeSet attrs = Xml.asAttributeSet(parser);
        Log.d("zhanfei",TAG + ". paraseStyleNode.attrs: " + attrs);

        if(attrs != null) {
            for(int i=0;i<attrs.getAttributeCount();i++) {
                String attrName = attrs.getAttributeName(i);
                String attrValue = attrs.getAttributeValue(i);
                Log.d("zhanfei",TAG + ". paraseStyleNode.attrName: " + attrName);
                Log.d("zhanfei",TAG + ". paraseStyleNode.attrValue: " + attrValue);
            }
        }
        //===========================================//
        return skinAttrs;
    }
}
