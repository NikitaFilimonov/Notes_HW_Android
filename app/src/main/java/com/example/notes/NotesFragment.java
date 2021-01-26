package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesFragment extends Fragment {

    private boolean isLandscape;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showLandNoteExpanded(0);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notes);
        Context context = getContext();
        for (int i = 0; i < notes.length; i++) {
            if (context != null) {
                String note = notes[i];
                TextView textView = new TextView(context);
                textView.setText(note);
                textView.setTextSize(30);
                layoutView.addView(textView);
                final int fi = i;
                textView.setOnClickListener(v -> showNoteExpanded(fi));
            }
        }
    }

    private void showNoteExpanded(int index) {
        if (isLandscape) {
            showLandNoteExpanded(index);
        } else {
            showPortNoteExpanded(index);
        }
    }

    private void showLandNoteExpanded(int index) {
        NoteExpandedFragment detail = NoteExpandedFragment.newInstance(index);
        FragmentActivity context = getActivity();
        if (context != null) {
            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.note_expanded, detail);
            fragmentTransaction.commit();
        }
    }

    private void showPortNoteExpanded(int index) {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, NoteExpandedActivity.class);
            intent.putExtra(NoteExpandedFragment.ARG_INDEX, index);
            startActivity(intent);
        }
    }
}