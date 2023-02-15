package ru.shanin.mycontact.presentation.fragments.about_people;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;

public class AboutPeopleViewModel extends ViewModel {

    private MutableLiveData<People> peopleLiveData;

    private PeopleGetByIdUseCase getById;

    public AboutPeopleViewModel(PeopleGetByIdUseCase getById) {
        this.getById = getById;
    }

    protected MutableLiveData<People> getPeople(String peopleId) {
        peopleLiveData = getById.peopleGetById(peopleId);
        return peopleLiveData;
    }


}

