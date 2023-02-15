package ru.shanin.mycontact.app;

import android.app.Application;

import ru.shanin.mycontact.data.db_room.database.AppDatabase;
import ru.shanin.mycontact.data.repositoryImpl.PeopleRoomRepositoryImpl;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;


public class AppStart extends Application {
    private PeopleGetByIdUseCase getById;
    private PeopleAddNewUseCases addNew;
    private PeopleDeleteByIdUseCase delete;
    private PeopleGetByAllUseCase getAll;

    public PeopleGetByIdUseCase getGetById() {
        return getById;
    }

    public PeopleAddNewUseCases getAddNew() {
        return addNew;
    }

    public PeopleDeleteByIdUseCase getDelete() {
        return delete;
    }

    public PeopleGetByAllUseCase getGetAll() {
        return getAll;
    }

    private static AppStart instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppDatabase database = AppDatabase.getInstance(this);
        PeopleRoomRepositoryImpl peopleRoomRepositoryImpl =
                new PeopleRoomRepositoryImpl(database.roomPeopleDao());
        getById = new PeopleGetByIdUseCase(peopleRoomRepositoryImpl);
        addNew = new PeopleAddNewUseCases(peopleRoomRepositoryImpl);
        delete = new PeopleDeleteByIdUseCase(peopleRoomRepositoryImpl);
        getAll = new PeopleGetByAllUseCase(peopleRoomRepositoryImpl);
    }

    public static AppStart getInstance() {
        return instance;
    }
}
