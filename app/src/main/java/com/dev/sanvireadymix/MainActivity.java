package com.dev.sanvireadymix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager1, viewPager2, viewPager3;
    Adapter adapter1, adapter2, adapter3;
    List<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();

        models.add(new Model(R.drawable.aluparatha, "Alu Paratha", "48", "Punjabi"));
        models.add(new Model(R.drawable.idlimix, "Idli Mix", "48", "South Indian"));
        models.add(new Model(R.drawable.batatawada, "Batata Wada", "48", "Upwas"));
        models.add(new Model(R.drawable.dhokla, "Upwas Dhokla", "52", "Upwas"));
        models.add(new Model(R.drawable.daalvade, "Daal Vade", "52", "Marathi"));
        models.add(new Model(R.drawable.konkanivade, "Konkani Vada", "48", "Marathi"));
        models.add(new Model(R.drawable.methiparatha, "Methi Paratha", "48", "Punjabi"));
        models.add(new Model(R.drawable.satupith, "Satu Pith", "52", "Flour"));
        models.add(new Model(R.drawable.dhapataflour, "Dhapata Flour", "52", "Flour"));

        adapter1 = new Adapter(models, this);
        adapter2 = new Adapter(models, this);
        adapter3 = new Adapter(models, this);

        viewPager1 = findViewById(R.id.viewpager1);
        viewPager2 = findViewById(R.id.viewpager2);
        viewPager3 = findViewById(R.id.viewpager3);

        viewPager1.setClipToPadding(false);
        viewPager1.setPadding(10, 0, 400, 0);

        viewPager2.setClipToPadding(false);
        viewPager2.setPadding(10, 0, 400, 0);

        viewPager3.setClipToPadding(false);
        viewPager3.setPadding(10, 0, 400, 0);

        viewPager1.setAdapter(adapter1);
        viewPager2.setAdapter(adapter2);
        viewPager3.setAdapter(adapter3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.searchBarMenu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cartMenu) {
            Intent i = new Intent(MainActivity.this, CartActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
