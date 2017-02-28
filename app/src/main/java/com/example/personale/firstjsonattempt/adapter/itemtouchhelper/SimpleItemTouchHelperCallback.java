package com.example.personale.firstjsonattempt.adapter.itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by personale on 28/02/2017.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter tAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adaper){
        this.tAdapter = adaper;
    }

    /**
     * What: Supported movement direction
     * @return create movement flag
     *          - dragFlags: The directions in which the item can be dragged.
     *          - swipeFlags: The directions in which the item can be swiped.
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * What: notifies adapter that an item has moved
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }

        tAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        tAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
