package ru.shanin.mycontact.domain.usecases;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleGetByAllUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByAllUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<People>> peopleGetByAll() {
        return repository.peopleGetAll();
    }
}