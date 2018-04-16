package com.filmscout.nasha.filmscout.api.models;

public class SelectData extends SearchData {

    private boolean isSelected = false;

    public SelectData(SearchData data, boolean isSelected){
        super(data.getData());
        this.isSelected = isSelected;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
