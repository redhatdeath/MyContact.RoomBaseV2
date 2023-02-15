package ru.shanin.mycontact.domain.usecases;


import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.repository.PeopleDomainRepository;

public class PeopleDeleteByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleDeleteByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleDeleteById(String _id) {
        repository.peopleDeleteById(_id);
    }

}
