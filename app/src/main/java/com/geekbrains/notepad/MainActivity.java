package com.geekbrains.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private CardSource cardSource;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cardSource = new CardSourceImpl(this);

        adapter = new ItemAdapter(cardSource);

 //       ItemAdapter adapter = new ItemAdapter(getResources().getStringArray(R.array.fields));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setListener(position -> {
//            Toast.makeText(this, "click" +
//                    getResources().getStringArray(R.array.fields)[position], Toast.LENGTH_SHORT).show();
            FillInTheFieldsFragment details = new FillInTheFieldsFragment();
          details.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.fragment_container,details).commit();
        });
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
                cardSource.addCardNote(new CardNote("новая заметка", "текст заметки", "установите дату"));
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
}