package sk.itsovy.android.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OneWordActivity extends AppCompatActivity {

    private String selectedWord;
    private Button btnDelete;

    private TextView oneWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_word);

        oneWord = findViewById(R.id.selectedWord);
        btnDelete = findViewById(R.id.btnDelete);
        //get the intent extra from the ListDataActivity


        Intent receivedIntent = getIntent();
        selectedWord = receivedIntent.getStringExtra("name_word");
           //todo save also intent id for deleting
        toastMessage("sending word is " + selectedWord);
        oneWord.setText(selectedWord);


      /// todo show also id of this word


        btnDelete.setOnClickListener(v -> toastMessage("delete word function "));
    }

    /**
     * customizable toast
     *
     * @param message
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

