package sk.itsovy.android.parkingapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

        //    @Entity(tableName = "weather_result",indices = {@Index(value = {"id"}, unique = true)}
//@Entity(tableName = "words")
@Entity(tableName = "words", indices = {@Index(value = {"word"}, unique = true)})
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String nameWord;


    @NonNull
    @ColumnInfo(name = "example")
    private String exampleValue;


    @NonNull
    public String getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(@NonNull String exampleValue) {
        this.exampleValue = exampleValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNameWord() {
        return nameWord;
    }

    public void setNameWord(@NonNull String nameWord) {
        this.nameWord = nameWord;
    }
}
