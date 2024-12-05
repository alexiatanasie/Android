package parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab2_1108.util.DateConverter;
import eu.ase.ro.lab2_1108.util.Movie;

public class JsonParser {
    public static final String MOVIE_KEY="movies";
    public static final String TITLE_KEY="title";
    public static final String PROFIT_KEY="profit";
    public static final String RELEASE_DATE_KEY="releaseDate";
    public static final String MOVIE_GENRE_KEY="movieGenre";
    public static final String PLATFORM_KEY="platform";
    List<Movie> parseMovieList=new ArrayList<>();


    public List<Movie> getParseMovieList(String jsonString) {

        if(jsonString!=null){
            try {
                JSONObject jsonObject=new JSONObject(jsonString);
                JSONArray moviesArray=jsonObject.getJSONArray(MOVIE_KEY);
                for(int i=0;i<moviesArray.length();i++){
                    JSONObject moviesJson=moviesArray.getJSONObject(i);
                    String title=moviesJson.getString(TITLE_KEY);
                    String releaseDate=moviesJson.getString(RELEASE_DATE_KEY);
                    int profit=moviesJson.getInt(PROFIT_KEY);
                    String movieGenre=moviesJson.getString(MOVIE_GENRE_KEY);
                    String platform=moviesJson.getString(PLATFORM_KEY);
                    Movie movie=new Movie(title, DateConverter.fromString(releaseDate),profit,movieGenre,platform);
                    parseMovieList.add(movie);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        else{
            Log.e("JsonParser","the data to be parsed is null");
        }
        return parseMovieList;
    }

}
