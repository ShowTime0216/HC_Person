package com.hsy.hc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView_Sl extends ListView {

	public MyListView_Sl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	 @Override
	    /**
	     * 重写该方法，达到使ListView适应ScrollView的效果
	     */
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	        MeasureSpec.AT_MOST);
	        super.onMeasure(widthMeasureSpec, expandSpec);
	    }

}
