package com.mxtech.demo.listener;

import android.view.View;

import com.mxtech.demo.entity.DynamicAttr;

import java.util.List;


public interface IDynamicNewView {
	void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
