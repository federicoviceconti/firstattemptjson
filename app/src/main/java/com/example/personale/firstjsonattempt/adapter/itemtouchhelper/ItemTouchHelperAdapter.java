package com.example.personale.firstjsonattempt.adapter.itemtouchhelper;

/**
 * Created by personale on 27/02/2017.
 */

public interface ItemTouchHelperAdapter {
    /**
     *  What: When user drag the item across the screen
     *      - fromPos: the original location of the item
     *      - toPos: the final location of the item
     */
    void onItemMove(int fromPos, int toPos);
    /**
     *  What: When user dismiss the swipe motion
     *      - position: location of dismissed item
     */
    void onItemDismiss(int position);
}
