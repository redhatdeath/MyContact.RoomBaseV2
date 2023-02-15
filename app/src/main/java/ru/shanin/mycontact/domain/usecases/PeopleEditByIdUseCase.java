package ru.shanin.mycontact.domain.usecases;


import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleEditByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleEditByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleEditById(People people) {
        repository.peopleEditById(people);
    }

}
