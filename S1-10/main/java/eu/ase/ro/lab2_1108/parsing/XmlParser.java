package eu.ase.ro.lab2_1108.parsing;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab2_1108.util.DateConverter;
import eu.ase.ro.lab2_1108.util.Movie;

public class XmlParser {
    private List<Movie> movies=new ArrayList<>();
    private Movie movie;
    private String text;
    public List<Movie> parseXmlContent(InputStream is){
        XmlPullParser pullParser= Xml.newPullParser();
        try {
            pullParser.setInput(is,"utf-8");

            int eventType=pullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName=pullParser.getName();

                switch(eventType){
                    case XmlPullParser.START_TAG:
                        if(tagName.equalsIgnoreCase("Movie")){
                            movie=new Movie();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text=pullParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(tagName.equalsIgnoreCase("Movie")){
                            movies.add(movie);

                        }else if(tagName.equalsIgnoreCase("title")){
                            movie.setTitle(text);

                        } else if(tagName.equalsIgnoreCase("releaseDate")){
                            movie.setReleaseDate(DateConverter.fromString(text));
                        }
                        else if(tagName.equalsIgnoreCase("profit")){
                            movie.setProfit(Integer.parseInt(text));
                        }
                        else if(tagName.equalsIgnoreCase("movieGenre")){
                            movie.setMovieGenre(text);
                        }
                        else if(tagName.equalsIgnoreCase("platform")){
                            movie.setPlatform(text);
                        }
                        break;
                    default:
                        break;

                }
                eventType=pullParser.next();
            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }
}
