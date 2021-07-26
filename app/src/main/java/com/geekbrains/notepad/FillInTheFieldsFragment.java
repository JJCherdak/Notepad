package com.geekbrains.notepad;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class FillInTheFieldsFragment extends Fragment {

    public FillInTheFieldsFragment(){

    }

    public static final String ARG_FIELDS = "field";
    private Fields field;


    public static FillInTheFieldsFragment newInstance(Fields field) {
        FillInTheFieldsFragment f = new FillInTheFieldsFragment();


        Bundle args = new Bundle();
        args.putParcelable(ARG_FIELDS, field);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            field = getArguments().getParcelable(ARG_FIELDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillin_the_fields, container, false);
        AppCompatImageView imageFields = view.findViewById(R.id.fill_in_the_fields);
        TypedArray images = getResources().obtainTypedArray(R.array.detailed_fields);
        imageFields.setImageResource(images.getResourceId(field.getImageIndex(), -1));
        TextView fieldNameView = view.findViewById(R.id.textView);
        fieldNameView.setText(field.getFieldName());
        return view;
    }
}
