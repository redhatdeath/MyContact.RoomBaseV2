package ru.shanin.mycontact.data.db_room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.shanin.mycontact.data.db_room.entity.RoomPeople;

@Dao
public interface RoomPeopleDao {
    // SELECT ALL
    @Query("SELECT * FROM Peoples ORDER BY Last_name,First_name,Second_name ASC")
    List<RoomPeople> roomPeopleGetAll();

    // SELECT ONE BY ID
    @Query("SELECT * FROM Peoples WHERE People_id = :id")
    RoomPeople roomPeopleGetById(String id);

    // INSERT NEW RECORD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void roomPeopleAddNew(RoomPeople roomPeople);

    // DELETE RECORD BY ID
    @Query("DELETE FROM Peoples WHERE People_id = :id")
    void roomPeopleDeleteById(String id);
}
