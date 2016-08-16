package com.fabantowapi.joetz_android.adapters.itemdecorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Pieter on 15-8-2016.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mPaddingTopBottom;
    private final int mPaddingLeftRight;

    public VerticalSpaceItemDecoration(int mPaddingTopBottom, int mPaddingLeftRight) {
        this.mPaddingTopBottom = mPaddingTopBottom;
        this.mPaddingLeftRight = mPaddingLeftRight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.top = mPaddingTopBottom;
        outRect.bottom = mPaddingTopBottom;
        outRect.left = mPaddingLeftRight;
        outRect.right = mPaddingLeftRight;
    }

}
