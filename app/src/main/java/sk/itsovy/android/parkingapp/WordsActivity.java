package sk.itsovy.android.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class WordsActivity extends AppCompatActivity implements OnWordClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        WordsAdapter adapter = new WordsAdapter();
        //adapter.setOnPlateClickListener(this);
        adapter.setOnWordClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewModelProvider provider = new ViewModelProvider(this);
        WordsViewModel wordsViewModel = provider.get(WordsViewModel.class);
        wordsViewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> vehicles) {
                adapter.setCachedWords(vehicles);
            }
        });

    }
   /* private void processFabClick() {
        DialogFragment insertDialogFragment = new InsertDialogFragment();
        insertDialogFragment.show(getSupportFragmentManager(), "insert");

    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnWordClick(Word word) {
        ViewModelProvider provider = new ViewModelProvider(this);
        WordsViewModel wordsViewModel = provider.get(WordsViewModel.class);
        wordsViewModel.delete(word);
    }
}