package com.example.experiment3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private final List<Animal> data = new ArrayList<>();
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        data.add(new Animal("Lion",     R.drawable.lion));
        data.add(new Animal("Tiger",    R.drawable.tiger));
        data.add(new Animal("Monkey",   R.drawable.monkey));
        data.add(new Animal("Dog",      R.drawable.dog));
        data.add(new Animal("Cat",      R.drawable.cat));
        data.add(new Animal("Elephant", R.drawable.elephant));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AnimalAdapter(data, position -> {
            Animal clicked = data.get(position);
            Toast.makeText(this, "点击: " + clicked.getName(), Toast.LENGTH_SHORT).show();

            data.add(new Animal(clicked.getName() + " Copy", clicked.getImageRes()));
            adapter.notifyItemInserted(data.size() - 1);
        });

        recyclerView.setAdapter(adapter);
    }
}