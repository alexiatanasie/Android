package com.example.ticket1_songs_xml;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Database(entities={Song.class},version=1,exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SongsDB extends RoomDatabase {

    public static SongsDB INSTANCE;
    public abstract SongsDAO getSongsDao();
    public static SongsDB getInstance(Context context){
        if(INSTANCE==null){
            synchronized (SongsDB.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,
                            SongsDB.class,"songs.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}