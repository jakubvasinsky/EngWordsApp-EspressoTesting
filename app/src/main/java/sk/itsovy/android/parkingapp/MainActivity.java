package sk.itsovy.android.parkingapp;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Button btnAdd, btnViewData, btnSearch;
    private EditText inputParameter,pronounceParamter;

    private TextView textViewResult, searchingParameter;
    private String jsonArrayString;
    private NestedScrollView scrollView;
    ListView listView;
    ArrayAdapter wordArrayAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);                           

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        //textViewResult = findViewById(R.id.text_view_result);    // response json
        inputParameter = findViewById(R.id.inputParameter);
       // pronounceParamter = findViewById(R.id.pronounceParameter);
        searchingParameter = (TextView)findViewById(R.id.searchingParameter);
      //  scrollView = findViewById(R.id.nestedScrollView);


        listView = findViewById(R.id.wordsRecyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // search word api
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputWord = inputParameter.getText().toString();
                System.out.println("input paramter " + inputParameter.getText().toString());
                System.out.println("input paramter " + inputWord);

                if (inputWord.length() < 1) {
                    searchingParameter.setText("Empty input");
                    toastMessage("Empty parameter");

                    


                    return;
                }

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<ResponseBody> call = jsonPlaceHolderApi.getStringResponse2(inputWord);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            System.out.println("code " + response.code());
                            //textViewResult.setText("Code: " + response.code());
                            //textViewResult.setText("Nothing");

                            searchingParameter.setText("INVALID");
                            List<String> resultList = new ArrayList<>();
                            resultList.add("Parameter does not exist ");
                            resultList.add("no examples");
                            

                            wordArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, resultList);
                            listView.setAdapter(wordArrayAdapter);
                            return;
                        }

                        Object obj = null;
                        obj = response.body(); // try to string


                        try {
                            //      JSONArray jsonArray1 = new JSONArray(response.body().toString());
                         //  json array2 begin here
                           jsonArrayString =    response.body().string();
                            JSONArray jsonArray2 = new JSONArray(jsonArrayString);

                            Object phonetics = jsonArray2.getJSONObject(0).get("phonetics");
                            Object meanings = jsonArray2.getJSONObject(0).get("meanings");

                            JSONArray jsonArray = (JSONArray) jsonArray2.getJSONObject(0).get("phonetics");
                            JSONArray jsonArrMeanings = (JSONArray) jsonArray2.getJSONObject(0).get("meanings");

                            String inputPronounce = (String) jsonArray.getJSONObject(0).get("text");


                            System.out.println("size meanings");
                            System.out.println(jsonArrMeanings.length());
                            System.out.println("size meanings");

                            System.out.println("Phonetics ---> text " + jsonArray.getJSONObject(0).get("text"));
                            System.out.println("Meanings ---> partOfSpeech " + jsonArrMeanings.getJSONObject(0).get("partOfSpeech"));
                            System.out.println("Meanings ---> definitions " + jsonArrMeanings.getJSONObject(0).get("definitions"));
                            // System.out.println("" + jsonArray.getJSONObject(0).get("text"));
                            //
                            JSONArray jsonArrMeaningsDefinitions = (JSONArray) jsonArrMeanings.getJSONObject(0).get("definitions");

                            System.out.println("length of jsonArraymeinanig definitions is " + jsonArrMeaningsDefinitions.length());
                            int lengthOfDefinitions = jsonArrMeaningsDefinitions.length();


                            JSONObject jsonObject2 = new JSONObject();
                            JSONArray jsonArray1 = new JSONArray();
                            List<JSONObject> list = new ArrayList<>();
                            Object[] ArrayObject = new Object[lengthOfDefinitions];
                            JSONArray jsonArraySynonyms = new JSONArray();


                            //
                            for (int i = 0; i < lengthOfDefinitions; i++) {


                                JSONObject jsonObject = new JSONObject();
                                System.out.println("-------------------------------------");
                                System.out.println("dlzka " + jsonArrMeaningsDefinitions.getJSONObject(i).get("definition").toString().length());
                                //  int number = ;
                                if (!jsonArrMeaningsDefinitions.getJSONObject(i).isNull("definition")) {

                                    jsonObject.put("Definition", jsonArrMeaningsDefinitions.getJSONObject(i).get("definition"));
                                    System.out.println("-- Definition  [" + i + "]" + jsonArrMeaningsDefinitions.getJSONObject(i).get("definition"));
                                    System.out.println("number is bigger than 10");
                                }

                                if (!jsonArrMeaningsDefinitions.getJSONObject(i).isNull("example")) {
                                    jsonObject.put("Example", jsonArrMeaningsDefinitions.getJSONObject(i).get("example"));
                                    System.out.println("dlzka " + jsonArrMeaningsDefinitions.getJSONObject(i).get("example").toString().length());
                                    System.out.println("-- Example  [" + i + "]" + jsonArrMeaningsDefinitions.getJSONObject(i).get("example"));
                                }
                                if (!jsonArrMeaningsDefinitions.getJSONObject(i).isNull("synonyms")) {
                                    //JSONArray jsonArrSynonyms= (JSONArray) jsonArrMeanings.getJSONObject(0).get("synonyms");
                                    System.out.println("synonyms");
                                    System.out.println("synonyms");
                                    System.out.println(jsonArrMeaningsDefinitions.getJSONObject(i).get("synonyms"));
                                    jsonArraySynonyms = (JSONArray) jsonArrMeaningsDefinitions.getJSONObject(i).get("synonyms");

                                    System.out.println("synonyms are here man");

                                    for (int j = 0; j <jsonArraySynonyms.length() ; j++) {
                                        System.out.println("=[=[=[=[=[=[=[=");
                                        System.out.println(jsonArraySynonyms.get(j));
                                        System.out.println("=[=[=[=[=[=[=[=");
                                    }
                                    System.out.println(jsonArraySynonyms);
                                }
                                System.out.println("-------------------------------------");
                                System.out.println("vklad poziciiu " + i);
                                System.out.println(jsonObject);
                                list.add(jsonObject);
                                ArrayObject[i] = jsonObject;

                                System.out.println(jsonObject);
                                jsonArray1.put(jsonObject);
                                System.out.println("=========================================");
                                System.out.println(jsonArray1);
                                System.out.println(jsonArray1.getJSONObject(0));

                                System.out.println("=========================================");
                            }


                            System.out.println("result " + jsonArray1);
                            System.out.println("list " + list);


                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");

                            for (int i = 0; i < ArrayObject.length; i++) {
                                System.out.println(ArrayObject[i]);
                            }
                            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''");

                            List<String> resultList = new ArrayList<>();
                            for (int i = 0; i <list.size() ; i++) {

                                System.out.println(list.get(i).toString().replaceAll("([{,}\"])", ""));
                                resultList.add(list.get(i).toString().replaceAll("([{,}\"])", ""));

                            }

                            resultList.add("");
                            resultList.add("Synonyms  " + jsonArraySynonyms.toString().replaceAll("([{}\"])", " "));

                            /*
                            for (int j = 0; j <jsonArraySynonyms.length() ; j++) {
                                resultList.add(jsonArraySynonyms.get(j).toString());
                            }*/
                            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''");

                            //textViewResult.setText(jsonArray2.toString());

                           // textViewResult.setText(list.toString().replaceAll("([{,}\"])", ""));

                            wordArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, resultList);
                            listView.setAdapter(wordArrayAdapter);

                            String mixInputWord = inputWord + "  " + inputPronounce;
                            searchingParameter.setText(mixInputWord.toUpperCase());

                            //   pronounceParamter.setText(inputPronounce);


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                                              // todo list save Json Objects into database   , saving into  database
                        // todo show saving data into Words Activity

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //textViewResult.setText(t.getMessage());

                    }

                });

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = inputParameter.getText().toString();
                if (inputParameter.length() != 0) {
                    //AddData(newEntry);
                    // input into data base


                    ViewModelProvider provider = new ViewModelProvider(MainActivity.this);

                    WordsViewModel viewModel = provider.get(WordsViewModel.class);

                    String inputWord = inputParameter.getText().toString();
                    Word word = new Word();
                    word.setNameWord(inputWord.toUpperCase());
                    word.setExampleValue(jsonArrayString);
                    System.out.println("ukladanie json string" + jsonArrayString);

                    viewModel.insert(word);

                    //inputParameter.setText("");
                    toastMessage("Save word  " + inputWord);

                } else {
                    toastMessage("You must put something in the text field!");
                    searchingParameter.setText("Empty input");
                    
                }

            }
        });


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("view data");
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                startActivity(intent);
                
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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
        WordsAdapter adapter = new VehiclesAdapter();
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