package com.geekbrains.notepad;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CardNoteFirestoreImpl implements CardSource {

    public static final String COLLECTION = "CARDS";
    public static final String TAG = "CardNoteFirestoreImpl";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection = db.collection(COLLECTION);
    private List<CardNote> cards = new ArrayList<>();

    @Override
    public CardSource init(CardSourceResponse response) {

        collection.get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        cards = new ArrayList<>();
                        for (QueryDocumentSnapshot document: task.getResult()){
                        CardNote note = document.toObject(CardNote.class);
                        note.setId(document.getId());

                        cards.add(note);
                        }

                        response.initialized(this);

                        Log.d(TAG, "success" + cards.size());
                    }
                    else {
                        Log.d(TAG, "failed", task.getException());
                    }

                })

                .addOnFailureListener(e -> {

                    Log.d(TAG, "failed", e);

                });



        return this;
    }

    @Override
    public CardNote getCardNote(int position) {
        return cards.get(position);
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public void deleteCardNote(int position) {
        collection.document(cards.get(position).getId()).delete();
        cards.remove(position);

    }

    @Override
    public void updateCardNote(int position, CardNote cardNote) {

        cards.set(position, cardNote);
        collection.document(cardNote.getId()).set(cardNote);
    }

    @Override
    public void addCardNote(CardNote cardNote) {
        collection.add(cardNote).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
               cardNote.setId(documentReference.getId());
            }
        });

    }

    @Override
    public void clearCardNote() {
        for(CardNote cardNote: cards){
        collection.document(cardNote.getId()).delete();

        }

        cards.clear();

    }
}
