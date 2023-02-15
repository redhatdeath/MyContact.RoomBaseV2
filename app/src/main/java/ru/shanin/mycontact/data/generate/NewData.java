package ru.shanin.mycontact.data.generate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.domain.entity.People;
import ru.shanin.mycontact.domain.entity.PeopleInfo;

public class NewData {

    public static String genPathToPhoto() {
        Field[] drawablesFields = R.drawable.class.getFields();
        ArrayList<String> imageName = new ArrayList<>();
        for (Field field : drawablesFields)
            if (field.getName().length() == 4)
                imageName.add(field.getName());
        // Из имени ресурса получить идентификатор
        // String mDrawableName = "name"; // файл name.png в папке drawable
        // int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        return imageName.get((int) (Math.random() * imageName.size()));
    }


    public static String genNumber(int length) {
        String digits = "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++)
            result.append(digits.charAt((int) (Math.random() * 10)));
        return result.toString();
    }

    public static String genPhoneNumber() {
        return "+79" + genNumber(9);
    }

    public static String genString(int length) {
        String alphabetInUpperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String alphabetInLowerCase = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder result = new StringBuilder();
        result.append(alphabetInUpperCase.charAt((int) (Math.random() * 26)));
        for (int i = 0; i < length - 1; i++)
            result.append(alphabetInLowerCase.charAt((int) (Math.random() * 26)));
        return result.toString();
    }

    public static String genLastName() {
        return genString(6);
    }

    public static String genFirstName() {
        return genString(6);
    }

    public static String genSecondName() {
        return genString(8);
    }

    public static ArrayList<String> genListOfKnowledge() {
        String[] knowledge = {
                " C++/CLI ", " C# ", " Object Pascal ", " Groovy ",
                " Java ", " JavaScript ", " Objective-C ", " Perl ",
                " PHP ", " Python ", " Ruby ", " Swift ",
                " Visual Basic ", " Ada ", " Erlang ", " Gentee ",
                " Haskell ", " Scheme ", " Лисп ", " Kotlin ",
                " Curry ", " Delphi ", " Rust ", " Scala "
        };
        ArrayList<String> listOfKnowledge = new ArrayList<>();
        for (int i = 0; i < (int) (Math.random() * 3 + 1); i++)
            listOfKnowledge.add(knowledge[(int) (Math.random() * knowledge.length)]);
        Collections.sort(listOfKnowledge);
        return listOfKnowledge;
    }

    public static People newPeople() {
        String ln = genLastName();
        String fn = genFirstName();
        String sn = genSecondName();
        return new People(new PeopleInfo(
                ln, fn, sn, ln + "_" + fn + "@gmail.com",
                genPhoneNumber(), genPathToPhoto(), genListOfKnowledge()));
    }
}