package ru.shanin.mycontact.data.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ru.shanin.mycontact.data.db_room.entity.RoomPeople;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.entity.PeopleInfo;

public class EntityMapper {

    public static RoomPeople toRoomPeople(People people) {
        return new RoomPeople(
                people.getId() + "",
                people.getPeopleInfo().getLastName() + "",
                people.getPeopleInfo().getFirstName() + "",
                people.getPeopleInfo().getSecondName() + "",
                people.getPeopleInfo().getAge() + 0,
                people.getPeopleInfo().getPhone() + "",
                people.getPeopleInfo().getEmail() + "",
                people.getPeopleInfo().getPathToPhoto() + "",
                (new Gson()).toJson(people.getPeopleInfo().getListOfKnowledge()) + ""
        );
    }

    public static People toPeople(RoomPeople roomPeople) {
        return new People(
                roomPeople.getPeopleId() + "",
                new PeopleInfo(
                        roomPeople.getLastName() + "",
                        roomPeople.getFirstName() + "",
                        roomPeople.getSecondName() + "",
                        roomPeople.getAge() + 0,
                        roomPeople.getEmail() + "",
                        roomPeople.getPhone() + "",
                        roomPeople.getPathToPhoto() + "",
                        (new Gson()).fromJson(
                                roomPeople.getListOfKnowledge(),
                                new TypeToken<ArrayList<String>>() {
                                }.getType())));
    }
}
