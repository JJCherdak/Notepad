package com.geekbrains.notepad;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardSourceImpl implements CardSource {

    private List <CardNote> cards;
    private  Context context;


    public CardSourceImpl(Context context) {
        this.context = context;


    }

    @Override
    public CardSource init(CardSourceResponse response) {
        cards = new ArrayList<>(Arrays.asList(
                new CardNote(
                        context.getResources().getString(R.string.note_title_1),
                        context.getResources().getString(R.string.note_description_1),
                        context.getResources().getString(R.string.note_date_1)

                ),
                new CardNote(
                        context.getResources().getString(R.string.note_title_2),
                        context.getResources().getString(R.string.note_description_2),
                        context.getResources().getString(R.string.note_date_2)
                ),
                new CardNote(
                        context.getResources().getString(R.string.note_title_3),
                        context.getResources().getString(R.string.note_description_3),
                        context.getResources().getString(R.string.note_date_3)

                ),
                new CardNote(
                        context.getResources().getString(R.string.note_title_4),
                        context.getResources().getString(R.string.note_description_4),
                        context.getResources().getString(R.string.note_date_4)

                ))
        );

        if (response != null){
            response.initialized(this);

        }

        return this;
    }

    @Override
    public CardNote getCardNote(int position) {
        return cards.get(position);
//        return cards[position];
    }

    @Override
    public int size() {
        return cards.size(); }

    @Override
    public void deleteCardNote(int position) {
    cards.remove(position);
    }

    @Override
    public void updateCardNote(int position, CardNote cardNote) {
    cards.set(position, cardNote);
    }

    @Override
    public void addCardNote(CardNote cardNote) {
    cards.add(cardNote);
    }

    @Override
    public void clearCardNote() {
    cards.clear();
    }
}
