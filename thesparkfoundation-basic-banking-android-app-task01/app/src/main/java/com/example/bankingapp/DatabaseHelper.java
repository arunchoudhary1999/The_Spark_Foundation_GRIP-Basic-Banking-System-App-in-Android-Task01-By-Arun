package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9778333353,'Naresh Apat',501.00,'nareshapat79@gmail.com','1002010445874987','INDB0000487')");
        db.execSQL("insert into user_table values(9438449875,'Sumanta Sahu',5902.67,'sumantasahu420@gmail.com','2010045871523442','SBIN0000912')");
        db.execSQL("insert into user_table values(8763475547,'Kunal Tripathy',15045.56,'kunaltripathy471@gmail.com','1005478900103417','BKID000641')");
        db.execSQL("insert into user_table values(9948754841,'Swayansu Abhisek',30500.01,'swayansuabhisek91@gmail.com','1002004578454124','BIO0BHU213')");
        db.execSQL("insert into user_table values(8895741548,'Rajanikanta Sahu',96003.48,'sahurajanikanta@gmail.com','1001245717879854','ICICI000208')");
        db.execSQL("insert into user_table values(9899345287,'Chinmay Kumar Das',6945.16,'daskuchinmay@gmail.com','1547815429345948','AXIS0000124')");
        db.execSQL("insert into user_table values(8547815798,'Srikanta Rout',59306.00,'routsrikanta47@gmail.com','1002045987445250','BIO000184')");
        db.execSQL("insert into user_table values(9012234567,'Gadadhar Pradhan',8570.22,'pradhangadadhar45@gmail.com','1000108974516455','INDB000029')");
        db.execSQL("insert into user_table values(7014554678,'Preeti Mahapatra',4398.46,'preeti.2000@gmail.com','1025487151045820','BIO0000218')");
        db.execSQL("insert into user_table values(6548456780,'Abhishek Singh',27300.90,'Abhishek.01@gmail.com','1004587041451460','BKIK0000154')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
