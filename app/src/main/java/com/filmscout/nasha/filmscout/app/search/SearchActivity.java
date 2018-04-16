package com.filmscout.nasha.filmscout.app.search;

import android.app.ListActivity;
import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.SearchData;
import com.filmscout.nasha.filmscout.api.models.SelectData;
import com.filmscout.nasha.filmscout.app.App;
import com.filmscout.nasha.filmscout.app.results.ResultsActivity;
import com.filmscout.nasha.filmscout.app.results.ResultsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.filmscout.nasha.filmscout.app.results.ResultsActivity.CERTIFICATION;

public class SearchActivity extends AppCompatActivity{


    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.title_search)
    EditText titleSearch;
    @BindView(R.id.genre_header)
    TextView genreHeader;
    @BindView(R.id.genres)
    TextView genreList;
    @BindView(R.id.mpaa_header)
    TextView mpaaHeader;
    @BindView(R.id.mpaas)
    TextView mpaaList;
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
    TextView errorView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private SearchComponent listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);



    }

    private void getListAdapter(){
        listAdapter = new SearchComponent();

        listAdapter.setListAdapter(new ArrayAdapter<String>
                (this, R.layout.activity_search,
                        R.id.genres, listAdapter.genres));
        listAdapter.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_search,
                R.id.mpaas, listAdapter.ratings));
    }

    @OnClick(R.id.search_btn)
    public void searchBtnClick(View view){
        EditText titleTxt = (EditText)findViewById(R.id.title_search);
        String movieTitle = titleTxt.getText().toString();
        TextView genreList = findViewById(R.id.genres);
        /**String genreChecked = genreList.
        if(movieTitle == ""){
            Intent i = new Intent(this, ResultsActivity.class);
            i.putExtra(CERTIFICATION,)

        }**/
    }
}
