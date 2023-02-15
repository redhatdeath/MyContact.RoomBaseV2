package ru.shanin.mycontact.presentation.fragments.list_of_people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.snackbar.Snackbar;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.domain.entity.People;

public class Adapter
        extends ListAdapter<People, ViewHolder> {

    public static final int MAX_POOL_SIZE = 10;
    public static final int VIEW_TYPE_PEOPLE_AGE_1 = 100;
    public static final int VIEW_TYPE_PEOPLE_AGE_2 = 200;
    public static final int VIEW_TYPE_PEOPLE_AGE_3 = 300;
    public static final int VIEW_TYPE_PEOPLE_AGE_4 = 400;
    public static final int VIEW_TYPE_PEOPLE_AGE_DEFAULT = 500;

    public OnPeopleClickListener peopleClickListener = null;

    public Adapter(DiffCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_PEOPLE_AGE_1:
                layout = R.layout.people_age_1;
                break;
            case VIEW_TYPE_PEOPLE_AGE_2:
                layout = R.layout.people_age_2;
                break;
            case VIEW_TYPE_PEOPLE_AGE_3:
                layout = R.layout.people_age_3;
                break;
            case VIEW_TYPE_PEOPLE_AGE_4:
                layout = R.layout.people_age_4;
                break;
            case VIEW_TYPE_PEOPLE_AGE_DEFAULT:
                layout = R.layout.people_age_default;
                break;
            default:
                throw new RuntimeException("Unknown view type " + viewType);
        }
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        People people = getItem(position);
        String ln = String.valueOf(people.getPeopleInfo().getLastName());
        viewHolder.tvFirstName.setText(ln);
        String fn_sn = people.getPeopleInfo().getFirstName() + " " + people.getPeopleInfo().getSecondName();
        viewHolder.tvSecondName.setText(fn_sn);
        String mDrawableName = people.getPeopleInfo().getPathToPhoto();
        Context context = viewHolder.itemView.getContext();
        int resID = context.getResources().getIdentifier(
                mDrawableName,
                "drawable",
                context.getPackageName()
        );
        viewHolder.imPhoto.setImageResource(resID);
        viewHolder.itemView.setOnClickListener(
                v -> {
                    Snackbar.make(viewHolder.itemView,
                            "people position = " + position + "\n" +
                                    "people _id = " + people.getId(),
                            Snackbar.LENGTH_LONG).show();
                    peopleClickListener.onPeopleClick(getCurrentList().get(position));
                });
    }

    @Override
    public int getItemViewType(int position) {
        People people = getItem(position);
        if (people.getPeopleInfo().getAge() / 10 == 1)
            return VIEW_TYPE_PEOPLE_AGE_1;
        if (people.getPeopleInfo().getAge() / 10 == 2)
            return VIEW_TYPE_PEOPLE_AGE_2;
        if (people.getPeopleInfo().getAge() / 10 == 3)
            return VIEW_TYPE_PEOPLE_AGE_3;
        if (people.getPeopleInfo().getAge() / 10 == 4)
            return VIEW_TYPE_PEOPLE_AGE_4;
        else return VIEW_TYPE_PEOPLE_AGE_DEFAULT;
    }

    interface OnPeopleClickListener {
        void onPeopleClick(People people);
    }
}