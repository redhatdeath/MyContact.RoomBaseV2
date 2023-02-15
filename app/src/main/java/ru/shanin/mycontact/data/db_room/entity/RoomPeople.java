package ru.shanin.mycontact.data.db_room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = RoomPeople.NAME_TABLE,
        indices = {@Index(RoomPeople.COL_PEOPLE_ID)}
)
public class RoomPeople {

    public static final String NAME_TABLE = "Peoples";

    public static final String COL_PEOPLE_ID = "People_id";
    public static final String COL_LAST_NAME = "Last_name";
    public static final String COL_FIRST_NAME = "First_name";
    public static final String COL_SECOND_NAME = "Second_name";
    public static final String COL_AGE = "Age";
    public static final String COL_PHONE = "Phone";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PATH_TO_PHOTO = "Path_to_photo";
    public static final String COL_LIST_OF_KNOWLEDGE = "List_of_knowledge";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COL_PEOPLE_ID)
    private final String peopleId;

    @ColumnInfo(name = COL_LAST_NAME)
    private final String lastName;
    @ColumnInfo(name = COL_FIRST_NAME)
    private final String firstName;

    @ColumnInfo(name = COL_SECOND_NAME)
    private final String secondName;

    @ColumnInfo(name = COL_AGE)
    private final int age;

    @ColumnInfo(name = COL_PHONE)
    private final String phone;

    @ColumnInfo(name = COL_EMAIL)
    private final String email;

    @ColumnInfo(name = COL_PATH_TO_PHOTO)
    private final String pathToPhoto;

    @ColumnInfo(name = COL_LIST_OF_KNOWLEDGE)
    private final String listOfKnowledge;

    public RoomPeople(
            String peopleId,
            String lastName,
            String firstName,
            String secondName,
            int age,
            String phone,
            String email,
            String pathToPhoto,
            String listOfKnowledge
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.pathToPhoto = pathToPhoto;
        this.listOfKnowledge = listOfKnowledge;
        this.peopleId = peopleId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public String getListOfKnowledge() {
        return listOfKnowledge;
    }

}
