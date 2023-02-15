package ru.shanin.mycontact.domain.usecases;


import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleAddNewUseCases {
    private final PeopleDomainRepository repository;

    public PeopleAddNewUseCases(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleAddNew(People people) {
        repository.peopleAddNew(people);
    }
}
