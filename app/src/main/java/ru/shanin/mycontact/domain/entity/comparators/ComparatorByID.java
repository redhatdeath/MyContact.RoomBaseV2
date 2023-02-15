package ru.shanin.mycontact.domain.entity.comparators;

import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.People;

public class ComparatorByID implements Comparator<People> {

    @Override
    public int compare(People people1, People people2) {
        String id1 = people1.getId();
        String id2 = people2.getId();
        return id1.compareTo(id2);
    }
}