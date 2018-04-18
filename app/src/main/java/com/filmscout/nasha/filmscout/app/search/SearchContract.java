package com.filmscout.nasha.filmscout.app.search;

import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.ListView;

import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.Images;

import java.util.List;

public interface SearchContract {

    interface View{

        void showLoading();

        void showContent();

        //void getGenres(Integer genreId, String name);

        List<Genre> getGenres(List<Genre> genre);

        List<Certification> getRatings(List<Certification> certification);

        //void getRatings(String name);

        //void getInfo();

        void showError();



    }

    interface Presenter{

        void start();

        void finish();
    }




}
