package com.geekbrains.notepad;

import androidx.annotation.NonNull;
import android.content.res.Resources;

public class NoteProvider {

    public static CardNote[] getData(@NonNull Resources resources){
    return new CardNote[]{
                new CardNote(
                       resources.getString(R.string.note_title_1),
                       resources.getString(R.string.note_description_1),
                       resources.getString(R.string.note_date_1)

                ),
                new CardNote(
                    resources.getString(R.string.note_title_2),
                    resources.getString(R.string.note_description_2),
                    resources.getString(R.string.note_date_2)
                    ),
                 new CardNote(
                    resources.getString(R.string.note_title_3),
                    resources.getString(R.string.note_description_3),
                    resources.getString(R.string.note_date_3)

                    ),
                 new CardNote(
                    resources.getString(R.string.note_title_4),
                    resources.getString(R.string.note_description_4),
                    resources.getString(R.string.note_date_4)

                    )

    };


    }
}
