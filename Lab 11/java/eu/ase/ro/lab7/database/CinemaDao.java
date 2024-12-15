package eu.ase.ro.lab7.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.ase.ro.lab7.utils.Cinema;

@Dao
public interface CinemaDao {
    @Insert
    void insert(Cinema cinema);

    @Query("SELECT * FROM cinemas")
    List<Cinema>getAll();

    @Query("DELETE FROM cinemas")
    void deleteAll();


}
