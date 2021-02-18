package sk.itsovy.android.parkingapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> words;


    WordRepository(Application application) {

        WordsDatabase database = WordsDatabase.getDatabase(application);
        //kontextom nieje iba aktivita laeo uz aj aplikacia

        wordDao = database.wordDao();

        words = wordDao.getAllWords();// hned ako sa pripojime vypitam si slovicka
    }


    LiveData<List<Word>> getAllWords() {
        return words;
    }


    void insert(Word word) {
        WordsDatabase.databaseWriteExecutor.execute(
                () -> {
                    wordDao.insert(word);
                }
        );

    }

    void delete(Word word) {
        WordsDatabase.databaseWriteExecutor.execute(
                () -> {
                    wordDao.deleteWord(word);
                }
        );
    }

    public void deleteWord(String nameWord) {
        WordsDatabase.databaseWriteExecutor.execute(
                () -> {
                    wordDao.deleteWordString(nameWord);
                }
        );

    }
}
