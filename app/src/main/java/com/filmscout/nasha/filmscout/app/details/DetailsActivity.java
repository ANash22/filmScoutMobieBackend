package com.filmscout.nasha.filmscout.app.details;

import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.ConfigurationLanguages;
import com.filmscout.nasha.filmscout.api.models.CreditsResponse;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.Video;
import com.filmscout.nasha.filmscout.app.App;
import com.filmscout.nasha.filmscout.app.search.SearchActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_RATING ="rating";

    @Inject
    DetailsPresenter detailsPresenter;

    @BindView(R.id.poster_details)
    View contentView;
    @BindView(R.id.credits_trailer)
    View creditsView;
    @BindView(R.id.poster)
    ImageView imageView;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.genre_header)
    TextView genreHeader;
    @BindView(R.id.genreTextView)
    TextView genreTextView;
    @BindView(R.id.duration_header)
    TextView durationHeader;
    @BindView(R.id.durationTextView)
    TextView durationTextView;
    @BindView(R.id.language_header)
    TextView languageHeader;
    @BindView(R.id.languageTextView)
    TextView languageTextView;
    @BindView(R.id.release_date_header)
    TextView releaseDataHeader;
    @BindView(R.id.releaseDateTextView)
    TextView releaseDateTextView;
    @BindView(R.id.rating_header)
    TextView ratingHeader;
    @BindView(R.id.ratingTextView)
    TextView ratingTextView;
    @BindView(R.id.overview_header)
    TextView overviewHeader;
    @BindView(R.id.overviewTextView)
    TextView overviewTextView;
    @BindView(R.id.cast_header)
    TextView castHeader;
    @BindView(R.id.castTextView)
    TextView castTextView;
    @BindView(R.id.crew_header)
    TextView crewHeader;
    @BindView(R.id.crewTextView)
    TextView crewTextView;
    @BindView(R.id.webView)
    WebView trailerView;
    @BindView(R.id.watched_btn)
    Button watchedBtn;
    @BindView(R.id.will_watch_btn)
    Button willWatchBtn;
    @BindView(R.id.rating_edit)
    EditText editRating;
    @BindView(R.id.enter_rating_btn)
    Button ratingBtn;
    @BindView(R.id.error)
    TextView errorView;
    @BindView(R.id.progressBar)
    ProgressBar loadingView;

    private int movieId = -1;
    private Images images;
    private MovieDetails mDetails;
    private List<Video> videos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        DaggerDetailsComponent.builder()
                .applicationComponent(App.getApplicationComponent(getApplication()))
                .detailsModule(new DetailsModule(this))
                .build()
                .inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            movieId = extras.getInt(MOVIE_ID);
            String movieTitle = extras.getString(MOVIE_TITLE);

            setTitle(movieTitle);
        }


    }

    @Override
    protected void onResume(){
        super.onResume();
        detailsPresenter.start(movieId);
    }

    @Override
    public void showLoading(){
        loadingView.setVisibility(View.VISIBLE);
        showContent(false);
        editRating.setVisibility(View.INVISIBLE);
        ratingBtn.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.GONE);

    }

    public void showContent(MovieDetails movieDetails){
        String fullImageUrl = getFullImageUrl(movieDetails);
        int mId = movieDetails.id;
        CreditsResponse creditsResponse = new CreditsResponse();
        creditsResponse.id = mId;

        if(!fullImageUrl.isEmpty()){
            Glide.with(this )
                    .load(fullImageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(withCrossFade())
                    .into(imageView);
        }

        titleTextView.setText(getTitle(movieDetails.title));
        genreTextView.setText(getGenres(movieDetails));
        durationTextView.setText(getDuration(movieDetails));
        languageTextView.setText(getLanguage(movieDetails));
        releaseDateTextView.setText(getReleaseDate(movieDetails.releaseDate));
        ratingTextView.setText(getRating(movieDetails.voteAverage));
        overviewTextView.setText(getOverview(movieDetails.overview));
        castTextView.setText(getCast(creditsResponse));
        crewTextView.setText(getCrew(creditsResponse));
        trailerView.getSettings().setJavaScriptEnabled(true);
        trailerView.getSettings().setLoadsImagesAutomatically(true);
        trailerView.loadUrl("https://www.youtube.com/embed/" + getTrailerUrl());
        trailerView.setWebChromeClient(new WebChromeClient());

        loadingView.setVisibility(View.GONE);
        showContent(true);
        editRating.setVisibility(View.INVISIBLE);
        ratingBtn.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.GONE);

    }

    private String getTitle(String title){
        return TextUtils.isEmpty(title) ? "-": title;
    }

    private String getGenres(MovieDetails movieDetails){
        String genres = "";
        for (int i = 0; i< movieDetails.genres.size(); i++){
            Genre genre = movieDetails.genres.get(i);
            genres += genre.name + ", ";
        }

        genres = removeTrailingComma(genres);
        return genres.isEmpty() ? "-" : genres;
    }

    private String getDuration(MovieDetails movieDetails){
        int runtime = movieDetails.runtime;
        return runtime <= 0 ? "-": getResources().getQuantityString(R.plurals.duration, runtime, runtime);
    }

    private String getLanguage(MovieDetails movieDetails){
        String languages = "";
        for (int i = 0; i < movieDetails.spokenLanguages.size(); i++){
            ConfigurationLanguages language = movieDetails.spokenLanguages.get(i);
            languages += language.name + ", ";
        }

        languages = removeTrailingComma(languages);
        return languages.isEmpty() ? "-": languages;
    }

    private String getReleaseDate(String releaseDate){
        return TextUtils.isEmpty(releaseDate) ? "-": releaseDate;
    }

    private String getRating(Double ratings){
        String rating = ratings.toString();
        return TextUtils.isEmpty(rating)? "-": rating;
    }

    private String getOverview(String overview){
        return TextUtils.isEmpty(overview)? "-": overview;
    }

    private String getCast(CreditsResponse creditsResponse){
        String cast = creditsResponse.cast;
        return TextUtils.isEmpty(cast)? "-":cast;
    }

    private String getCrew(CreditsResponse creditsResponse){
        String crew = creditsResponse.cast;
        return TextUtils.isEmpty(crew)? "-":crew;
    }

    @NonNull
    private String getFullImageUrl(MovieDetails movieDetails){
        String imagePath;

        if(movieDetails.posterPath != null && !movieDetails.posterPath.isEmpty()){
            imagePath = movieDetails.posterPath;
        }
        else{
            imagePath = movieDetails.backdropPath;
        }

        if(images != null && images.baseUrl != null &&!images.baseUrl.isEmpty()){
            if(images.posterSizes != null){
                if(images.posterSizes.size() > 4){
                    return   images.baseUrl + images.posterSizes.get(4) + imagePath;
                }
                else {
                    return images.baseUrl + "w500" + imagePath;
                }
            }
        }
        return "";
    }

    @NonNull
    private String getTrailerUrl(){
        String v = "";
        Integer movieId = 0;
        if(videos != null){
            for(int i = 0; i < videos.size(); i++){
                Video video = videos.get(i);
                v = video.key;

            }
        }
        return v;

    }

    @NonNull
    private String removeTrailingComma(String text){
        text = text.trim();
        if(text.endsWith(",")){
            text = text.substring(0, text.length() - 1);

        }
        return text;
    }

    @Override
    public void showError(){
        loadingView.setVisibility(View.GONE);
        showContent(false);
        editRating.setVisibility(View.INVISIBLE);
        ratingBtn.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConfigurationSet(Images images){
        this.images = images;
    }

    @Override
    public void setTrailer(List<Video> videos){
        this.videos = videos;
    }

    private void showContent(boolean show){
        int visibility = show ? View.VISIBLE: View.INVISIBLE;

        contentView.setVisibility(visibility);
        overviewHeader.setVisibility(visibility);
        overviewTextView.setVisibility(visibility);
        creditsView.setVisibility(visibility);
        watchedBtn.setVisibility(visibility);
        willWatchBtn.setVisibility(visibility);


    }

    @OnClick(R.id.watched_btn)
    public void addWatchedBtnClick(View view){
        showContent(false);
        editRating.setVisibility(View.VISIBLE);
        ratingBtn.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.will_watch_btn)
    public void willWatchBtnClick(View view){
        int movieId = mDetails.id;
        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra(MOVIE_ID, movieId);
        startActivity(i);

    }

    @OnClick(R.id.enter_rating_btn)
    public void rateMovieBtnClick(View view){
        int movieId = mDetails.id;
        EditText ratingTxt = (EditText)findViewById(R.id.rating_edit);
        Double movieRating = Double.valueOf(ratingTxt.getText().toString());
        editRating.setVisibility(View.INVISIBLE);
        ratingBtn.setVisibility(View.INVISIBLE);
        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra(MOVIE_ID, movieId);
        i.putExtra(MOVIE_RATING, movieRating);
        startActivity(i);


    }
}
