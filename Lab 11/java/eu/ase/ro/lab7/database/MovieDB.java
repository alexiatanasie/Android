package eu.ase.ro.lab7.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import eu.ase.ro.lab7.utils.Cinema;
import eu.ase.ro.lab7.utils.DateConverter;
import eu.ase.ro.lab7.utils.Movie;

@Database(entities = {Movie.class, Cinema.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MovieDB extends RoomDatabase {
    public static final String DB_NAME="movies.db";
    public static MovieDB INSTANCE;

    public abstract CinemaDao getCinemaDao();
    public abstract MovieDao getMovieDao();

    public static MovieDB getInstance(Context context){
        if(INSTANCE==null){
            synchronized (MovieDB.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,MovieDB.class,DB_NAME).fallbackToDestructiveMigration()
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
