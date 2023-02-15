package ru.shanin.mycontact.presentation.fragments.about_people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import ru.shanin.mycontact.R;
import ru.shanin.mycontact.app.AppStart;
import ru.shanin.mycontact.domain.entity.People;

public class AboutPeople extends Fragment {

    private AboutPeopleViewModel viewModel;

    private static final String ARGUMENT_PEOPLE_GSON = "people Gson";
    private static final String ARGUMENT_PEOPLE_ID = "people id";
    private People people;
    private String peopleId;

    private int[] color = {0xAA55FF00, 0xAA550033, 0xAA550077, 0xAA5500AA, 0xAA5500FF};

    public static AboutPeople newInstance(
            String peopleGson,
            String peopleId
    ) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_PEOPLE_GSON, peopleGson);
        args.putString(ARGUMENT_PEOPLE_ID, peopleId);
        AboutPeople fragment = new AboutPeople();
        fragment.setArguments(args);
        return fragment;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_PEOPLE_ID))
            throw new RuntimeException("Argument '''People Id''' is absent");
        if (!args.containsKey(ARGUMENT_PEOPLE_GSON))
            throw new RuntimeException("Argument '''People Gson''' is absent");
        String peopleGson = args.getString(ARGUMENT_PEOPLE_GSON);
        people = (new Gson()).fromJson(peopleGson, People.class);
        peopleId = args.getString(ARGUMENT_PEOPLE_ID);
    }

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        parseParams();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_about_people,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initViewModel();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(
                this,
                new AboutPeopleViewModelFactory(
                        AppStart.getInstance().getGetById()
                ))
                .get(AboutPeopleViewModel.class);
        viewModel.getPeople(peopleId).observe(
                getViewLifecycleOwner(),
                _people -> people = _people
        );
    }

    private void initView(View view) {
        int colorId = (people.getPeopleInfo().getAge()) / 10;
        if (colorId > 0 && colorId < 5)
            view.findViewById(R.id.layout_about_people).setBackgroundColor(color[colorId]);
        else
            view.findViewById(R.id.layout_about_people).setBackgroundColor(color[0]);
        String mDrawableName = people.getPeopleInfo().getPathToPhoto();
        int resID = view.getContext().getResources().getIdentifier(
                mDrawableName,
                "drawable",
                view.getContext().getPackageName()
        );
        ((ImageView) view.findViewById(R.id.photo))
                .setImageResource(resID);
        String ln = String.valueOf(people.getPeopleInfo().getLastName());
        String fn_sn = people.getPeopleInfo().getFirstName() + " " + people.getPeopleInfo().getSecondName();
        ((TextView) view.findViewById(R.id.tv_fn)).setText(ln);
        ((TextView) view.findViewById(R.id.tv_sn)).setText(fn_sn);
        ((TextView) view.findViewById(R.id.tv_age))
                .setText(String.valueOf(people.getPeopleInfo().getAge()));
        ((TextView) view.findViewById(R.id.tv_email))
                .setText(people.getPeopleInfo().getEmail());
        ((TextView) view.findViewById(R.id.tv_phone))
                .setText(people.getPeopleInfo().getPhone());
        ((TextView) view.findViewById(R.id.tv_list_of_knowledge))
                .setText(people.getPeopleInfo().getListOfKnowledge().toString());
    }
}