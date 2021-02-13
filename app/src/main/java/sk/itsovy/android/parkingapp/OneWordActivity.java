package sk.itsovy.android.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OneWordActivity extends AppCompatActivity {

    private String selectedWord;
    private String examples;
    private Button btnDelete;
    private ListView listView;

    private TextView oneWord;

    ArrayAdapter exampleArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word);
                                            
        oneWord = findViewById(R.id.selectedWord);
        btnDelete = findViewById(R.id.btnDelete);
        listView = findViewById(R.id.examplesWordView);

        //get the intent extra from the ListDataActivity


        Intent receivedIntent = getIntent();
        selectedWord = receivedIntent.getStringExtra("name_word");
        examples = receivedIntent.getStringExtra("examples");

        //todo save also intent id for deleting

        toastMessage("sending word is " + selectedWord);

        oneWord.setText(selectedWord);


        /// todo show also id of this word


        jsonStringToList(examples);

        //btnDelete.setOnClickListener(v -> toastMessage("delete word function "));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("Click delete word ");
                
            }
        });
    }


    private void jsonStringToList(String jsonArrayString) {
        JSONArray jsonArray2 = null;
        try {
            jsonArray2 = new JSONArray(jsonArrayString);


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


            exampleArrayAdapter = new ArrayAdapter<>(OneWordActivity.this, android.R.layout.simple_list_item_1, resultList);
            listView.setAdapter(exampleArrayAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * customizable toast
     *
     * @param message
     * qwq
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

