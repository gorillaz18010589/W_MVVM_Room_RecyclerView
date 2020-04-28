package com.example.w_mvvm_room_recyclerview;
//2.建立Entity表
/*
*
* @Entity:設定為表,裡面的屬性都會變成欄位
* @Entity(tableName = "user_table"):設定表明稱為
* @PrimaryKey(autoGenerate = true):設定主建,並且自動產生
* @ColumnInfo(name = "tit_le"):可以改變欄位名屬性名稱,如果沒寫,預設維銓小寫
* @Ignore:可以排除此屬性,讓屬性部會變成欄位
* */

/*A.特性:
* 1，一個實體對象代表數據表中的一行，一個實體類代表一張數據表。
  2，實體中的成員變量都是數據表中的列。
  3，一個Java類定義成實體只要加上實體註解就可以了。
* */

/*B.定義主建
*每一個實體至少定義一個主鍵（主鍵），哪怕實體中只有一個變量也指向這個變量定義主體鍵，
* 在房間數據庫中使用註解@PrimaryKey來定義主鍵，
* @ PrimaryKey的使用方式有兩種一種是 在類變量前面加，如果主鍵比較複雜可以加在@Entity註解的後面。
* */
import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//A.建立TableName
@Entity(tableName = "user_table")
public class User {

    //B. @PrimaryKey:設定主建,自動產生(true/false)
    @PrimaryKey(autoGenerate = true)
    private int id;

    //C. @ColumnInfo:可以改變欄位名屬性名稱,如果沒寫,預設維銓小寫ex:@ColumnInfo(name = "tit_le")
    private String title;

    private String description;

    private int priority;


    //D.排除此屬性,讓他不要蘭變成欄位
    @Ignore
//    Bitmap bitmap;  //@Ignore:可以排除此屬性,讓屬性部會變成欄位


    public User(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
