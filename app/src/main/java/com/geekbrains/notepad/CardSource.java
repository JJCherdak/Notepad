package com.geekbrains.notepad;

public interface CardSource {

    CardSource init(CardSourceResponse response);

    CardNote getCardNote(int position);
    int size();
    void deleteCardNote (int position);
    void updateCardNote (int position, CardNote cardNote);
    void addCardNote (CardNote cardNote);
    void clearCardNote ();

}
