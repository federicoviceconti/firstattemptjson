package com.example.personale.firstjsonattempt.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

/**
 * Created by personale on 27/02/2017.
 */

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{
    private SparseBooleanArray selectedNote;

    public SelectableAdapter(){
        selectedNote = new SparseBooleanArray();
    }

    public boolean isSelected(int pos){
        return selectedNote.get(pos);
    }

    public void setSelected(int pos){
        if(!selectedNote.get(pos)){
            selectedNote.put(pos, true);
        }else{
            selectedNote.delete(pos);
        }

        notifyItemChanged(pos);
    }

    public int[] getSelectedItem(){
        int res[] = new int[selectedNote.size()];

        if(selectedNote.size() == 0){
            return null;
        }else{
            for(int i = 0; i < selectedNote.size(); i++){
                res[i] = selectedNote.keyAt(i);
            }
        }

        return res;
    }

    public void clearSelection(){
        selectedNote.clear();
    }

    public int getSize(){
        return selectedNote.size();
    }
}
