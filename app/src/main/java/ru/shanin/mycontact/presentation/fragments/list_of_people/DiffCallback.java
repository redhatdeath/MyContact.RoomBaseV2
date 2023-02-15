package ru.shanin.mycontact.presentation.fragments.list_of_people;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.shanin.mycontact.domain.entity.People;

public class DiffCallback extends DiffUtil.ItemCallback<People> {

    @Override
    public boolean areItemsTheSame(@NonNull People oldPeople, @NonNull People newPeople) {
        return (oldPeople.getId()).equals(newPeople.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull People oldPeople, @NonNull People newPeople) {
        return oldPeople.equals(newPeople);
    }
}
