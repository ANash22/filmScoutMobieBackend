package com.filmscout.nasha.filmscout.app.search;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.GenreResponse;
import com.filmscout.nasha.filmscout.app.App;
import com.filmscout.nasha.filmscout.app.details.DetailsActivity;
import com.filmscout.nasha.filmscout.app.results.ResultsActivity;
import com.google.common.collect.ArrayListMultimap;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.CAST;
import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.CREW;
import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.KEYWORDS;
import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.PRIMARY_RELEASE_GTE;
import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.PRIMARY_RELEASE_LTE;
import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.TITLE;


public class SearchActivity extends AppCompatActivity implements SearchContract.View{

    @Inject
    SearchPresenter presenter;

    @BindView(R.id.intro_txt)
    TextView introHeader;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.title_search)
    EditText titleSearch;
    @BindView(R.id.genre_header)
    TextView genreHeader;
    @BindView(R.id.genres)
    ListView genreList;
    @BindView(R.id.mpaa_header)
    TextView mpaaHeader;
    @BindView(R.id.mpaas)
    ListView mpaaList;
    @BindView(R.id.keyword_header)
    TextView keywordHeader;
    @BindView(R.id.keyword_search)
    EditText keywordSearch;
    @BindView(R.id.actor_header)
    TextView actorHeader;
    @BindView(R.id.actor_search)
    EditText actorSearch;
    @BindView(R.id.director_header)
    TextView directorHeader;
    @BindView(R.id.director_search)
    EditText directorSearch;
    @BindView(R.id.year_header)
    TextView yearHeader;
    @BindView(R.id.before)
    RadioButton beforeBtn;
    @BindView(R.id.after)
    RadioButton afterBtn;
    @BindView(R.id.year_search)
    EditText yearSearch;
    @BindView(R.id.rating_header)
    TextView ratingHeader;
    @BindView(R.id.rating_search)
    EditText ratingSearch;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.error)
    View errorView;
    @BindView(R.id.progressBar)
    View loadingView;
    @BindView(R.id.genre_mpaa)
    View genreMpaa;
    @BindView(R.id.keyword_actor_director)
    View keywordActorDirector;
    @BindView(R.id.made_rating)
    View madeRating;
    @BindView(R.id.search_btn_layout)
    View searchBtnLayout;


    private List<Genre> gList;
    private List<Certification> rList;

    ListView genreView;
    ListView mpaaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        DaggerSearchComponent.builder()
                .applicationComponent(App.getApplicationComponent(getApplication()))
                .searchModule(new SearchModule(this))
                .build()
                .inject((this));



    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.start();
    }

    @Override
    public List<Genre> getGenres(List<Genre> genres){


        genres = new ArrayList<>();
        genreView = findViewById(R.id.genres);
        ArrayList<String> g = new ArrayList<>();
        String name = "";
        Integer genreId;
        int size = genres.size();


        for(int i = 0; i < size; i++){
            Genre genre = genres.get(i);
             name = genre.name;
             genreId = genre.id;
             g.add(genreId,name);
             gList.add(genre);

        }


        ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(this,
                R.layout.list_view_row, R.id.listText, g);

        genreView.setAdapter(genreAdapter);

        return gList;

    }

    @Override
    public List<Certification> getRatings(List<Certification> ratings){
        mpaaView = findViewById(R.id.mpaas);
        ArrayList<String> r = new ArrayList<>();
        ratings = new ArrayList<>();
        String name = "";
        int size = ratings.size();

        for(int i = 0; i < size; i++){
            Certification rating = ratings.get(i);
            name = rating.certification;
            r.add(name);
            rList.add(rating);
        }


        ArrayAdapter<String> mpaaAdapter = new ArrayAdapter<String>(this,
                R.layout.list_view_row, R.id.listText, r);
        mpaaView.setAdapter(mpaaAdapter);

        return rList;

    }

    public void toastMsg(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void showLoading(){
        loadingView.setVisibility(View.VISIBLE);
        showContent(false);
        errorView.setVisibility(View.GONE);
    }
    @Override
    public void showContent(){

        getGenres(gList);
        getRatings(rList);
        loadingView.setVisibility(View.GONE);
        showContent(true);

        errorView.setVisibility(View.GONE);
    }
    @Override
    public void showError(){
        loadingView.setVisibility(View.GONE);
        showContent(false);
        errorView.setVisibility(View.VISIBLE);

    }

    private void showContent(boolean show){
        int visibility = show ? View.VISIBLE : View.INVISIBLE;

        introHeader.setVisibility(visibility);
        titleHeader.setVisibility(visibility);
        titleSearch.setVisibility(visibility);
        genreMpaa.setVisibility(visibility);
        keywordActorDirector.setVisibility(visibility);
        madeRating.setVisibility(visibility);
        searchBtnLayout.setVisibility(visibility);



    }





    /**private void getListAdapter(){
        listAdapter = new SearchComponent();

        listAdapter.setListAdapter(new ArrayAdapter<String>
                (this, R.layout.activity_search,
                        R.id.genres, listAdapter.genres));
        listAdapter.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_search,
                R.id.mpaas, listAdapter.ratings));
    }**/

    @OnClick(R.id.search_btn)
    public void searchBtnClick(View view){
        EditText titleTxt = findViewById(R.id.title_search);
        String movieTitle = titleTxt.getText().toString();
        EditText actorTxt = findViewById(R.id.actor_search);
        String actor = actorTxt.getText().toString();
        EditText directorText = findViewById(R.id.director_search);
        String director = directorText.getText().toString();
        EditText keywordText = findViewById(R.id.keyword_search);
        String keyword = parseString(keywordText.getText().toString());
        EditText yearTxt = findViewById(R.id.year_search);
        String year = yearTxt.getText().toString();
        EditText ratingTxt = findViewById(R.id.rating_search);
        Double rating = parseDouble(ratingTxt.getText().toString());


        //TextView certTxt = (TextView) findViewById(R.id.mpaas)
        //String certification = certTxt.getText.toString()
        //TextView genreText = (TextView) findViewbyId(R.id.genres)
        //String genres = genreText.getText.toString();
        //make map of genre ids and names
        //Corrspeond String genres to its id
        //Value named genreId



        Intent i = new Intent(this, ResultsActivity.class);
        if (beforeBtn.isChecked()){
            i.putExtra(PRIMARY_RELEASE_LTE, year);

        }else{
            i.putExtra(PRIMARY_RELEASE_GTE, year);
        }
        i.putExtra(CAST, actor);
        i.putExtra(CREW, director);
        i.putExtra(TITLE, movieTitle);
        i.putExtra(KEYWORDS, keyword);
        i.putExtra("vote_average", rating);
        //i.putExtra(CERTIFICATION, certification)
        //i.putExtra("genre", genreId)
        startActivity(i);
        //toastMsg(movieTitle);
        //ListView genreList = findViewById(R.id.genres);
        /**String genreChecked = genreList.
        if(movieTitle == ""){
            Intent i = new Intent(this, ResultsActivity.class);
            i.putExtra(CERTIFICATION,)

        }**/
    }

    public double parseDouble(String strNumber){
        if(strNumber != null && strNumber.length() > 0){
            try{
                return Double.parseDouble(strNumber);
            }catch (Exception e){
                return -1;
            }
        }
        else return 0;
    }

    public String parseString(String string){
        if(string != null && string.length() > 0){
            return string;
        }
        else return "";
    }
}
