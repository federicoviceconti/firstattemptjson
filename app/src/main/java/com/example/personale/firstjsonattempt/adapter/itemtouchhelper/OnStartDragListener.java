package com.example.personale.firstjsonattempt.adapter.itemtouchhelper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by personale on 28/02/2017.
 */

public interface OnStartDragListener {
    /**
     * What: when user begins a drag and drop interaction with the touchscreen
     * @param viewHolder :
     *                   - Viewholder: the dragged object
     *
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
