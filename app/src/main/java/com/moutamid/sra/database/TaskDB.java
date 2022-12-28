package com.moutamid.sra.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.moutamid.sra.models.TasksModel;

@Database(entities = TasksModel.class, version = 1, exportSchema = false)
public abstract class TaskDB extends RoomDatabase {
    private static TaskDB database;
    private static String DATABASE_NAME = "SRAAPP";

    public synchronized static TaskDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), TaskDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract TaskDAO TaskDao();

}
