package com.moutamid.sra.database;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.moutamid.sra.models.TasksModel;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert(onConflict = REPLACE)
    void insert(TasksModel taskModel);

    @Query("SELECT * FROM TaskTable ORDER BY id ASC")
    List<TasksModel> getAll();

    @Query("UPDATE TaskTable SET name = :name, amount = :amount, income= :income, image= :image, isLock= :isLock where id = :id")
    void update(int id, String name, int amount, int income, int image, boolean isLock);

    @Delete
    void Delete(TasksModel taskModel);
}
