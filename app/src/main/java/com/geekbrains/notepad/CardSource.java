package com.geekbrains.notepad;

public interface CardSource {

    CardNote getCardNote(int position);
    int size();
}
