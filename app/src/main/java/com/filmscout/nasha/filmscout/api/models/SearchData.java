package com.filmscout.nasha.filmscout.api.models;

public class SearchData {

    private String data;

    public SearchData(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        SearchData itemCompare = (SearchData) obj;
        if(itemCompare.getData().equals(this.getData()))
            return true;

        return false;
    }

}
