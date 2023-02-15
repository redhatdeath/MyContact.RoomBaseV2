package ru.shanin.mycontact.presentation.fragments.list_of_people;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class ListOfPeopleViewModel extends ViewModel {

    private MutableLiveData<ArrayList<People>> peoplesListLiveData;

    private PeopleDeleteByIdUseCase delete;
    private PeopleAddNewUseCases addNew;
    private PeopleGetByAllUseCase getAll;


    public ListOfPeopleViewModel(
            PeopleDeleteByIdUseCase delete,
            PeopleAddNewUseCases addNew,
            PeopleGetByAllUseCase getAll
    ) {
        this.delete = delete;
        this.addNew = addNew;
        this.getAll = getAll;
    }

    protected MutableLiveData<ArrayList<People>> getPeopleList() {
        peoplesListLiveData = getAll.peopleGetByAll();
        return peoplesListLiveData;
    }


    protected void addNewPeople(People people) {
        addNew.peopleAddNew(people);
    }

    protected void deletePeople(String id) {
        delete.peopleDeleteById(id);
    }

}