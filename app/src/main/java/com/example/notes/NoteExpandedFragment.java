package com.example.notes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteExpandedFragment extends Fragment {

    static final String ARG_INDEX = "index";
    private int index;

    public NoteExpandedFragment() {
        // Required empty public constructor
    }

    public static NoteExpandedFragment newInstance(int index) {
        NoteExpandedFragment fragment = new NoteExpandedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_expanded, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatTextView textNoteExpanded = view.findViewById(R.id.note_expanded);
        TypedArray texts = getResources().obtainTypedArray(R.array.note_expanded);
        textNoteExpanded.setBackgroundResource(texts.getResourceId(index, -1));
    }
}
