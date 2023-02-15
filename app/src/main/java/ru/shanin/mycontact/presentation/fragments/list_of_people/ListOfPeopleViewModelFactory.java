package ru.shanin.mycontact.presentation.fragments.list_of_people;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.mycontact.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mycontact.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mycontact.domain.usecases.PeopleGetByAllUseCase;

public class ListOfPeopleViewModelFactory implements ViewModelProvider.Factory {
    private PeopleDeleteByIdUseCase _delete;
    private PeopleAddNewUseCases _addNew;
    private PeopleGetByAllUseCase _getAll;


    public ListOfPeopleViewModelFactory(
            PeopleDeleteByIdUseCase delete,
            PeopleAddNewUseCases addNew,
            PeopleGetByAllUseCase getAll
    ) {
        this._delete = delete;
        this._addNew = addNew;
        this._getAll = getAll;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ListOfPeopleViewModel(_delete, _addNew, _getAll);
    }
}
