package com.geekbrains.notepad;

import android.content.Context;
import android.content.res.Resources;

public class CardSourceImpl implements CardSource {

    private Context context;

    public CardSourceImpl(Context context) {
        this.context = context;
    }

    @Override
    public CardNote getCardNote(int position) {
        return NoteProvider.getData(context.getResources())[position];
    }

    @Override
    public int size() {
        return NoteProvider.getData(context.getResources()).length;
    }
}
