package ru.shanin.mycontact.domain.entity;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;

public class PeopleInfo {
    private final String lastName;
    private final String firstName;
    private final String secondName;
    private final int age;
    private final String email;
    private final String phone;
    private final ArrayList<String> listOfKnowledge;
    private final String pathToPhoto;

    public PeopleInfo(
            String lastName,
            String firstName,
            String secondName,
            String email,
            String phone,
            String pathToPhoto,
            ArrayList<String> listOfKnowledge

    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.listOfKnowledge = listOfKnowledge;
        this.pathToPhoto = pathToPhoto;
        this.age = (int) (Math.random() * 61 + 5);
    }

    public PeopleInfo(
            String lastName,
            String firstName,
            String secondName,
            int age,
            String email,
            String phone,
            String pathToPhoto,
            ArrayList<String> listOfKnowledge
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.listOfKnowledge = listOfKnowledge;
        this.pathToPhoto = pathToPhoto;
    }

    @NonNull
    @Override
    public String toString() {
        return lastName + ", " + firstName + " " + secondName;
    }

    private String createSHA256String() {
        return lastName + firstName + secondName + age +
                email + phone +
                (new Gson()).toJson(listOfKnowledge);
    }

    public String getToSHA256() {
        return createSHA256String();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || object.getClass() != getClass()) return false;
        PeopleInfo peopleInfo = (PeopleInfo) object;
        return
                this.firstName.equals(peopleInfo.getFirstName()) &&
                        this.secondName.equals(peopleInfo.getSecondName()) &&
                        this.email.equals(peopleInfo.getEmail()) &&
                        this.phone.equals(peopleInfo.getPhone()) &&
                        this.pathToPhoto.equals(peopleInfo.getPathToPhoto()) &&
                        this.age == peopleInfo.getAge() && (
                        this.listOfKnowledge == peopleInfo.getListOfKnowledge() ||
                                this.listOfKnowledge.stream()
                                        .collect(groupingBy(k -> k, counting()))
                                        .equals(peopleInfo.getListOfKnowledge().stream()
                                                .collect(groupingBy(k -> k, counting()))));
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<String> getListOfKnowledge() {
        return listOfKnowledge;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }


}








