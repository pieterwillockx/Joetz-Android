package com.fabantowapi.joetz_android.adapters.itemdecorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Pieter on 18-8-2016.
 */
public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mPaddingLeftRight;

    public HorizontalSpaceItemDecoration(int mPaddingLeftRight) {
        this.mPaddingLeftRight = mPaddingLeftRight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = mPaddingLeftRight;
        outRect.right = mPaddingLeftRight;
    }
}
