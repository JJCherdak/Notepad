package com.geekbrains.notepad;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotepadFragment extends Fragment {

    private boolean isLandscape;
    public static final String CURRENT_FIELD = "CurrentField";
    private Fields currentField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notepad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] fields = getResources().getStringArray(R.array.fields);

        LayoutInflater ltInflater = getLayoutInflater();

        for(int i=0; i < fields.length; i++){
            String field = fields[i];
            View item = ltInflater.inflate(R.layout.item, layoutView, false);
            TextView tv = item.findViewById(R.id.textView);
            tv.setText(field);
            layoutView.addView(item);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentField = new Fields(fi, getResources().getStringArray(R.array.fields)[fi]);
                showFields(currentField);
            });
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_FIELD, currentField);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentField = savedInstanceState.getParcelable(CURRENT_FIELD);
        } else {
            currentField = new Fields(0, getResources().getStringArray(R.array.fields)[0]);
        }



        if (isLandscape) {
        showFields(currentField);
        }
    }

    private void showFields (Fields currentField) {
        if (isLandscape) {
            showLandFields(currentField);
        } else {
            showPortFields(currentField);
        }
    }


    private void showLandFields (Fields currentField) {
            FillInTheFieldsFragment detail = FillInTheFieldsFragment.newInstance(currentField);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fill_in_the_fields, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


        private void showPortFields (Fields currentFields) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), Detailed_fields_Activity.class);
        intent.putExtra(FillInTheFieldsFragment.ARG_FIELDS, currentFields);
        startActivity(intent);
    }
}
