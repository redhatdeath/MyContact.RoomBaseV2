package ru.shanin.mycontact.presentation.fragments.about_people;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.mycontact.domain.usecases.PeopleGetByIdUseCase;

public class AboutPeopleViewModelFactory implements ViewModelProvider.Factory {
    private PeopleGetByIdUseCase _getById;


    public AboutPeopleViewModelFactory(PeopleGetByIdUseCase getById) {
        this._getById = getById;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AboutPeopleViewModel(_getById);
    }
}

