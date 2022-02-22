package com.mxtech.demo.entity;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StyleAttr extends SkinAttr {
    protected List<SkinAttr> skinAttrs;

    public StyleAttr() {
        skinAttrs = new ArrayList<>();
    }

    @Override
    public void apply(View view) {

    }

    public List<SkinAttr> getSkinAttrs() {
        return skinAttrs;
    }
}
