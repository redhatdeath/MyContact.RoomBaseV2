package ru.shanin.mycontact.data.db_room.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;

@Database(entities = {RoomPeople.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract RoomPeopleDao roomPeopleDao();

    public static AppDatabase getInstance(Context context) {
        AppDatabase tempInstance = instance;
        if (tempInstance != null)
            return tempInstance;
        else tempInstance = Room
                .databaseBuilder(
                        context,
                        AppDatabase.class,
                        AppDatabase.class.getSimpleName()
                )
                .build();
        instance = tempInstance;
        return tempInstance;
    }
}