package com.example.gamebacklog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "game_table")
public class Game implements Parcelable {
    @PrimaryKey(autoGenerate = true)

    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "platform")
    private String platform;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "date")
    private String date;

    public Game(String title, String platform, String status, String date){
        this.title = title;
        this.platform = platform;
        this.status = status;
        this.date = date;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }



    public int describeContents(){ return 0;}

    public void writeToParcel(Parcel parcel, int id){
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.platform);
        parcel.writeString(this.status);
        parcel.writeString(this.date);
    }
    protected Game(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.platform = in.readString();
        this.status = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        // needs above method in order to work
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
