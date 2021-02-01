package sk.itsovy.android.parkingapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private Button btnAdd, btnViewData, btnSearch;
    private EditText inputParameter;
    private TextView textViewResult, searchingParameter;
    private NestedScrollView scrollView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        textViewResult = findViewById(R.id.text_view_result);    // response json
        inputParameter = findViewById(R.id.inputParameter);
        searchingParameter = findViewById(R.id.searchingParameter);
        scrollView = findViewById(R.id.nestedScrollView);



        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("click button view data");
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                startActivity(intent);

            }
        });
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFabClick();
            }
        });*/

/*
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        VehiclesAdapter adapter = new VehiclesAdapter();
        adapter.setOnPlateClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewModelProvider provider = new ViewModelProvider(this);
        VehiclesViewModel vehiclesViewModel = provider.get(VehiclesViewModel.class);
        vehiclesViewModel.getVehicles().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                adapter.setCachedVehicles(vehicles);
            }
        });*/
    }

    /*private void processFabClick() {
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
/*
    @Override
    public void onPlateClick(Vehicle vehicle) {
        ViewModelProvider provider = new ViewModelProvider(this);
        VehiclesViewModel vehiclesViewModel = provider.get(VehiclesViewModel.class);

        //TODO urobit select a spocitat cenu parkovania

        vehiclesViewModel.delete(vehicle);
    }*/
    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}