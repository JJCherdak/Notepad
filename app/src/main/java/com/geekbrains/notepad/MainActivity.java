package com.geekbrains.notepad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private CardSource cardSource;
    private RecyclerView recyclerView;
    private int currentPosition =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        addFragment(FillInTheFieldsFragment.newInstance(null));

        recyclerView = findViewById(R.id.recyclerView);
        cardSource = new CardNoteFirestoreImpl();

        adapter = new ItemAdapter(cardSource);

        cardSource.init(cardSource -> adapter.notifyDataSetChanged());

//        ItemAdapter adapter = new ItemAdapter(getResources().getStringArray(R.array.detailed_fields));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider, null));
        recyclerView.addItemDecoration(itemDecoration);


        adapter.setListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentPosition = position;
                view.showContextMenu();
            }
        });
        registerForContextMenu(recyclerView);


//        adapter.setListener(new ItemAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                FillInTheFieldsFragment details = new FillInTheFieldsFragment();
//                details.setArguments(getIntent().getExtras());
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container,details).commit();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:

                CardNote cardNote = new CardNote("новая заметка", "текст заметки", "установите дату");
                cardNote.setId(UUID.randomUUID().toString());
                cardSource.addCardNote(cardNote);
                adapter.notifyItemChanged(cardSource.size()-1);
                recyclerView.scrollToPosition(cardSource.size()-1);
                return true;
            case R.id.action_clear:
                cardSource.clearCardNote();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.card_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_delete:
                cardSource.deleteCardNote(currentPosition);
                adapter.notifyItemRemoved(currentPosition);
                return true;
            case R.id.action_update:

                CardNote cardNote = new CardNote("новая заметка", "текст заметки", "установите дату");
                cardNote.setId(cardSource.getCardNote(currentPosition).getId());
                cardSource.updateCardNote(currentPosition,cardNote);
                adapter.notifyItemChanged(currentPosition);
                return true;
        }


        return super.onContextItemSelected(item);
    }

//    private void addFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
}