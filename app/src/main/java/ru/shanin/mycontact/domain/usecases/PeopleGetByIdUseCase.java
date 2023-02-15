package ru.shanin.mycontact.domain.usecases;

import androidx.lifecycle.MutableLiveData;

import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleGetByIdUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<People> peopleGetById(String _id) {
        return repository.peopleGetById(_id);
    }
}