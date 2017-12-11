package com.example.ariel.aimclothing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ian on 12/8/2017.
 */

public class DBTools extends SQLiteOpenHelper {

    //database info
    private static final String TAG = "AimStore";
    private static final String DATABASE_NAME = "aimstore.db";
    private static final int DATABASE_VERSION = 1;

    //table names
    private static final String TABLE_USER = "tbl_user";
    private static final String TABLE_PRODUCT = "tbl_product";
    private static final String TABLE_TRANS = "tbl_trans";
    private static final String TABLE_CATEGORY = "tbl_category";

    //Common columns
    private static final String KEY_ID = "ID";
    private static final String KEY_CREATED_AT = "CREATED_AT";

    //Column: User table
    private static final String KEY_FULLNAME = "FULLNAME";
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_CONTACT = "CONTACT";
    private static final String KEY_USERTYPE = "TYPE";

    //Column: Product table
    private static final String KEY_CATEGORY_ID = "CAT_ID";
    private static final String KEY_PROD_NAME = "PRODUCT_NAME";
    private static final String KEY_PRICE = "PRICE";
    private static final String KEY_IMAGE = "IMAGE";

    //Column: transaction table
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_TRANS_DATE = "TRANSACTION_DATE";
    private static final String KEY_TOTAL = "TOTAL";
    private static final String KEY_RECEIPT = "RECEIPT";

    //Column: category table
    private static final String KEY_CATEGORY_NAME = "CATEGORY_NAME";


    /* TABLE CREATE STATEMENTS */
    //USER TABLE create statement
    private static final String CREATE_TABLE_USERS = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s DATETIME)",
            TABLE_USER, KEY_ID, KEY_FULLNAME, KEY_USERNAME, KEY_PASSWORD, KEY_CONTACT, KEY_USERTYPE, KEY_CREATED_AT);

    //PRODUCT TABLE create
    private static final String CREATE_TABLE_PRODUCT = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s INTEGER, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            TABLE_PRODUCT, KEY_ID, KEY_CATEGORY_ID, KEY_PROD_NAME, KEY_PRICE, KEY_IMAGE);

    //CATEGORY TABLE create
    private static final String CREATE_TABLE_CATEGORY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT)",
            TABLE_CATEGORY, KEY_ID, KEY_CATEGORY_NAME);

    //TRANSACTION TABLE create
    private static final String CREATE_TABLE_TRANS = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s INTEGER, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            TABLE_TRANS, KEY_ID, KEY_USER_ID, KEY_TRANS_DATE, KEY_TOTAL, KEY_RECEIPT);


    public DBTools(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_TRANS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgraded db version from version " + oldVersion + " to version " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }


    public long registerUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAME, user.getName());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_CONTACT, user.getContactNo());
        values.put(KEY_CREATED_AT, getDateTime());

        long result = db.insert(TABLE_USER, null, values);
        Log.d(TAG, "Value of database result is : " + result);
        return result;
    }


    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_USER;
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);

        while(res.moveToNext()){
            User user = new User();
            user.setName(res.getString(res.getColumnIndex(KEY_FULLNAME)));
            user.setUsername(res.getString(res.getColumnIndex(KEY_USERNAME)));
            user.setPassword(res.getString(res.getColumnIndex(KEY_PASSWORD)));
            user.setDateRegistered(res.getString(res.getColumnIndex(KEY_CREATED_AT)));
            user.setContactNo(res.getString(res.getColumnIndex(KEY_CONTACT)));
            userList.add(user);
            Log.d(TAG, user.toString());
        }


        return userList;
    }


    public String checkDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor res = db.rawQuery(query, null);
        StringBuffer string = new StringBuffer();

        while (res.moveToNext()) {
            string.append("ID: " + res.getString(res.getColumnIndex(KEY_ID)) + "\n");
            string.append("Name: " + res.getString(res.getColumnIndex(KEY_FULLNAME)) + "\n\n");
        }

        Log.d(TAG, string.toString());
        return string.toString();
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}

