package tj.iskandar.roomdemo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAll();

    @Insert
    void insert(Recipe recipe);

}
