package com.filmscout.nasha.filmscout.app.results;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.filmscout.nasha.filmscout.Config;
import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.app.App;
import com.filmscout.nasha.filmscout.app.details.DetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.filmscout.nasha.filmscout.app.details.DetailsActivity.MOVIE_ID;
import static com.filmscout.nasha.filmscout.app.details.DetailsActivity.MOVIE_TITLE;

public class ResultsActivity extends AppCompatActivity implements
        ResultsContract.View, SwipeRefreshLayout.OnRefreshListener,
        EndlessScrollListener.ScrollToEndListener,
        ResultsAdapter.ItemClickListener {

    //public static final String TAG = "Results";
    public static final String CERTIFICATION = "certification";
    public static final String PRIMARY_RELEASE_GTE = "primary_release_gte";
    public static final String PRIMARY_RELEASE_LTE = "primary_release_lte";
    //public static final Double VOTE_AVERAGE ; just do intent.putExtra("vote-average", vote);
    //Double voteAverage = intent.getDouble("vote-average")
    public static final String CAST = "cast";
    public static final String CREW = "crew";
    public static final String GENRE = "genre";
    public static final String  KEYWORDS = "keywords";
    public static final String TITLE = "title";


    @Inject
    ResultsPresenter presenter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView contentView;
    @BindView(R.id.error)
    View errorView;
    @BindView(R.id.progressBar)
    View loadingView;

    private ResultsAdapter resultsAdapter;
    private EndlessScrollListener endlessScrollListener;
    private Images images;
    private String apiKey = Config.API_KEY_URL;

    private int page = 1;
    private String certification;
    private String primaryReleaseGTE;
    private String primaryReleaseLTE;
    private Double voteAverage;
    private String cast;
    private String crew;
    private String genre;
    private String keywords;
    private Boolean isRefresh;
    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        setupContentView();
        DaggerResultsComponent.builder()
                .applicationComponent(App.getApplicationComponent(getApplication()))
                .resultsModule(new ResultsModule(this))
                .build()
                .inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            if(extras.getString(PRIMARY_RELEASE_LTE ) != null){
                primaryReleaseLTE = extras.getString(PRIMARY_RELEASE_LTE);
            }
            else{
                primaryReleaseGTE = extras.getString(PRIMARY_RELEASE_GTE);
            }
            movieTitle = extras.getString(MOVIE_TITLE);
            certification = extras.getString(CERTIFICATION);
            voteAverage = extras.getDouble("vote_average");
            cast = extras.getString(CAST);
            crew = extras.getString(CREW);
            keywords = extras.getString(KEYWORDS);

            //genre = extras.get




        }
    }

    private void setupContentView(){
        swipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this);
        endlessScrollListener = new EndlessScrollListener(layoutManager, this);
        contentView.setLayoutManager(layoutManager);
        contentView.addOnScrollListener(endlessScrollListener);

    }

    @Override
    public  void onRefresh(){
        endlessScrollListener.onRefresh();
        presenter.onPullToRefresh(apiKey, movieTitle, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords);
    }

    @Override
    public void onScrollToEnd(){
        presenter.onScrollToEnd(apiKey, movieTitle, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords);
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.start(apiKey, movieTitle, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords);
    }

    @Override
    public void showLoading(boolean isRefresh){
        if(isRefresh){
            if(!swipeRefreshLayout.isRefreshing()){
                swipeRefreshLayout.setRefreshing(true);
            }
            else{
                loadingView.setVisibility(View.VISIBLE);
                contentView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showContent(List<Movie> movies, boolean isRefresh){

        resultsAdapter = new ResultsAdapter(movies, this, images, this);
        contentView.setAdapter(resultsAdapter);
        resultsAdapter.addAll(movies);
        resultsAdapter.notifyDataSetChanged();
        if (isRefresh){
            resultsAdapter.clear();
        }



        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            swipeRefreshLayout.setRefreshing(false);
        }, 1500);

        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError(){
        swipeRefreshLayout.setRefreshing(false);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    public void onConfigurationSet(Images images){
        this.images = images;

        if(resultsAdapter != null){
            resultsAdapter.setImages(images);
        }
    }

    @Override
    public void onItemClick(int movieId, String movieTitle){
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra(MOVIE_ID, movieId);
        i.putExtra(MOVIE_TITLE, movieTitle);
        startActivity(i);
    }

    @OnClick(R.id.error)
    void onClickErrorView(){
        presenter.start(apiKey, movieTitle,certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords);
    }
}
