package ru.shanin.mycontact.data.repositoryImpl;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ru.shanin.mycontact.data.db_room.dao.RoomPeopleDao;
import ru.shanin.mycontact.data.db_room.entity.RoomPeople;
import ru.shanin.mycontact.data.mapper.EntityMapper;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleRoomRepositoryImpl implements PeopleDomainRepository {
    private final RoomPeopleDao roomPeopleDao;
    private final MutableLiveData<ArrayList<People>> peoplesListLiveData;
    private final MutableLiveData<People> peopleLiveData;

    private static int autoIncrementId = 0;

    public PeopleRoomRepositoryImpl(
            RoomPeopleDao roomPeopleDao
    ) {
        this.roomPeopleDao = roomPeopleDao;
        peoplesListLiveData = new MutableLiveData<>();
        peopleLiveData = new MutableLiveData<>();
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleAddNew(People people) {
        AsyncTask.execute(() -> {
            RoomPeople rp = EntityMapper.toRoomPeople(people);
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleAddNew(rp);
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleEditById(People people) {
        AsyncTask.execute(() -> {
            RoomPeople roomPeople_new = EntityMapper.toRoomPeople(
                    new People(people.getPeopleInfo()));
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleDeleteById(people.getId());
                roomPeopleDao.roomPeopleAddNew(roomPeople_new);
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleDeleteById(String id) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleDeleteById(id);
            }
        });
        updatePeopleListAsyncTask();
    }

    private void updatePeopleListAsyncTask() {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                List<RoomPeople> roomPeopleData = roomPeopleDao.roomPeopleGetAll();
                ArrayList<People> data = new ArrayList<>();
                for (RoomPeople roomPeople : roomPeopleData)
                    data.add(EntityMapper.toPeople(roomPeople));
                peoplesListLiveData.postValue(new ArrayList<>(data));
            }
        });
    }


    private void findPeopleById(String id) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                People people = EntityMapper.toPeople(roomPeopleDao.roomPeopleGetById(id));
                peopleLiveData.postValue(people);
            }
        });
    }

    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return peoplesListLiveData;
    }

    @Override
    public MutableLiveData<People> peopleGetById(String id) {
        findPeopleById(id);
        return peopleLiveData;
    }
}
