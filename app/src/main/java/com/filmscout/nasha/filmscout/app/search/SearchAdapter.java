package com.filmscout.nasha.filmscout.app.search;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.SearchData;
import com.filmscout.nasha.filmscout.api.models.SelectData;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>
          {


    private List<String> searchData;
    private Activity activity;
    private ItemClickListener itemClickListener;

    private SparseBooleanArray selectedItems;

    public SearchAdapter(List<String> searchData, Activity activity,
                         ItemClickListener itemClickListener){

        this.searchData = searchData;
        this.activity = activity;
        this.itemClickListener = itemClickListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        String data = searchData.get(position);
        holder.typeView.setText(data);
        holder.itemView.setOnClickListener((view -> {
            itemClickListener.onItemClick(data, data);
        }));

    }

    @Override
    public int getItemCount(){
        return searchData.size();
    }



    public void clear(){
        searchData.clear();
    }

    public void addAll(List<String> searchData){
        this.searchData.addAll(searchData);
    }

    public void toggleSelection(int pos){
        if(selectedItems.get(pos, false)){
            selectedItems.delete(pos);
        }
        else{
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections(){
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public List<Integer> getSelectedItems(){
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for(int i = 0; i < selectedItems.size(); i++){
            items.add(selectedItems.keyAt(i));
        }

        return items;
    }

    public int getSelectdItemCount(){
        return selectedItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        View itemView;
        @BindView(R.id.type)
        TextView typeView;

        ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);

        }


    }



    public interface ItemClickListener{

        void onItemClick(String genre, String mpaa);
    }



}
