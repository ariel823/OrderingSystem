package com.example.ariel.aimclothing.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ariel.aimclothing.R;

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
    private static final int DATABASE_VERSION = 5;

    //table names
    private static final String TABLE_USER = "tbl_user";
    private static final String TABLE_PRODUCT = "tbl_product";
    private static final String TABLE_TRANS = "tbl_trans";
    private static final String TABLE_CATEGORY = "tbl_category";
    private static final String TABLE_BRAND = "tbl_brand";

    //Common columns
    private static final String KEY_ID = "ID";
    private static final String KEY_CREATED_AT = "CREATED_AT";

    //Column: User table
    private static final String KEY_FULLNAME = "FULLNAME";
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_USERTYPE = "TYPE";

    //Column: Product table
    private static final String KEY_CATEGORY_ID = "CAT_ID";
    private static final String KEY_PROD_NAME = "PRODUCT_NAME";
    private static final String KEY_BRAND_ID = "BRAND_ID";
    private static final String KEY_PRICE = "PRICE";
    private static final String KEY_IMAGE = "IMAGE";

    //Column: transaction table
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_TRANS_DATE = "TRANSACTION_DATE";
    private static final String KEY_PAYMENT = "PAYMENT";
    private static final String KEY_TOTAL = "TOTAL";
    private static final String KEY_RECEIPT = "RECEIPT";

    //Column: category table
    private static final String KEY_CATEGORY_NAME = "CATEGORY_NAME";

    //Column: brand table
    private static final String KEY_BRAND_NAME = "BRAND_NAME";
    private static final String KEY_BRAND_IMAGE = "BRAND_IMAGE";

    /* TABLE CREATE STATEMENTS */
    //USER TABLE create statement
    private static final String CREATE_TABLE_USERS = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT, " +       //KEY_FULLNAME
            "%s TEXT, " +       //KEY_USERNAME
            "%s TEXT, " +       //KEY_PASS
            "%s INTEGER, " +    //KEY_USERTYPE
            "%s DATETIME)",     //KEY_CREATED_AT
            TABLE_USER, KEY_ID, KEY_FULLNAME, KEY_USERNAME, KEY_PASSWORD, KEY_USERTYPE, KEY_CREATED_AT);

    //PRODUCT TABLE create
    private static final String CREATE_TABLE_PRODUCT = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s INTEGER, " +        //KEY_CATEGORY_ID
            "%s INTEGER, " +        //KEY_BRAND_ID
            "%s TEXT, " +           //KEY_PROD_NAME
            "%s TEXT)",           //KEY_PRICE
            TABLE_PRODUCT, KEY_ID, KEY_CATEGORY_ID, KEY_BRAND_ID, KEY_PROD_NAME, KEY_PRICE);

    //CATEGORY TABLE create
    private static final String CREATE_TABLE_CATEGORY = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT)",         //KEY_CATEGORY_NAME
            TABLE_CATEGORY, KEY_ID, KEY_CATEGORY_NAME);

    //BRAND TABLE create
    private static final String CREATE_TABLE_BRAND = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT, " +           //KEY_BRAND_NAME
            "%s INTEGER)",             //KEY_BRAND_IMAGE
            TABLE_BRAND, KEY_ID, KEY_BRAND_NAME, KEY_BRAND_IMAGE);

    //TRANSACTION TABLE create
    private static final String CREATE_TABLE_TRANS = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s INTEGER, " +    //USER_ID
            "%s TEXT, " +       //PAYMENT
            "%s TEXT, " +       //TOTAL
            "%s TEXT)",         //RECEIPT
            TABLE_TRANS, KEY_ID, KEY_USER_ID, KEY_PAYMENT, KEY_TOTAL, KEY_RECEIPT);


    public DBTools(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //This method will perform on the first run of the app
    //insert default data is included
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_TRANS);
        db.execSQL(CREATE_TABLE_BRAND);
        insertCategoryData(db);
        insertBrandData(db);
        insertDefaultProducts(db);
        insertUserData(db);
        Log.d(TAG, "Initializing tables and default records");
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


    /************************* INSERT METHODS **********************/

//    Register User
//    Program will return -1 if the insertion of data is failed
    public long registerUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAME, user.getName());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_USERTYPE, user.getUserType());
        values.put(KEY_CREATED_AT, getDateTime());

        long result = db.insert(TABLE_USER, null, values);
        Log.d(TAG, "Value of database result(User) is : " + result);
        return result;
    }

    public void deleteUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbl_user WHERE USERNAME = '" + user + "'";
        db.execSQL(query);
    }

    public void deleteProduct(String product){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_PRODUCT +" WHERE " + KEY_PROD_NAME + " = '" + product + "'";
        db.execSQL(query);
    }

//    Add products method
//    result will return -1 if the insertion of data is failed
    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_ID, product.getCategory());
        values.put(KEY_BRAND_ID, product.getBrand());
        values.put(KEY_PROD_NAME, product.getProduct_name());
        values.put(KEY_PRICE, product.getPrice());

        long result = db.insert(TABLE_PRODUCT, null, values);
        Log.d(TAG, "Value of database result(Product) is : " + result);

        return result;
    }



    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERTYPE + " = 1";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            User user = new User();
            user.setName(res.getString(res.getColumnIndex(KEY_FULLNAME)));
            user.setUsername(res.getString(res.getColumnIndex(KEY_USERNAME)));
            user.setPassword(res.getString(res.getColumnIndex(KEY_PASSWORD)));
            user.setUserType(1);
            user.setDateRegistered(res.getString(res.getColumnIndex(KEY_CREATED_AT)));
            userList.add(user);
            Log.d(TAG, user.toString());
        }

        return userList;
    }

    public List<User> getAdminList(){
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERTYPE + " = 2";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            User user = new User();
            user.setName(res.getString(res.getColumnIndex(KEY_FULLNAME)));
            user.setUsername(res.getString(res.getColumnIndex(KEY_USERNAME)));
            user.setPassword(res.getString(res.getColumnIndex(KEY_PASSWORD)));
            user.setUserType(2);
            user.setDateRegistered(res.getString(res.getColumnIndex(KEY_CREATED_AT)));
            userList.add(user);
            Log.d(TAG, user.toString());
        }

        return userList;
    }

//    Return all the products data in the db
//    Cursor will move to the next record until the last record
//    Program will return a list
    public List<Product> getProductList(){
        List<Product> prodList = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCT;
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            Product prod = new Product();
            prod.setProduct_name(res.getString(res.getColumnIndex(KEY_PROD_NAME)));
            prod.setPrice(Double.parseDouble(res.getString((res.getColumnIndex(KEY_PRICE)))));
            prod.setBrand(Integer.parseInt(res.getString(res.getColumnIndex(KEY_BRAND_ID))));
            prod.setCategory(Integer.parseInt(res.getString(res.getColumnIndex(KEY_CATEGORY_ID))));
            prodList.add(prod);
            Log.d(TAG, prod.toString());
        }

        return prodList;
    }

    public List<Product> getCpuList(){
        List<Product> prodList = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCT  +
                " WHERE " + KEY_CATEGORY_ID + " = 3 " +
                "OR " + KEY_CATEGORY_ID + " = 5 " +
                "OR " + KEY_CATEGORY_ID + " = 6 " +
                "OR " + KEY_CATEGORY_ID + " = 7 ";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            Product prod = new Product();
            prod.setProduct_name(res.getString(res.getColumnIndex(KEY_PROD_NAME)));
            prod.setPrice(Double.parseDouble(res.getString((res.getColumnIndex(KEY_PRICE)))));
            prod.setBrand(Integer.parseInt(res.getString(res.getColumnIndex(KEY_BRAND_ID))));
            prod.setCategory(Integer.parseInt(res.getString(res.getColumnIndex(KEY_CATEGORY_ID))));
            prodList.add(prod);
            Log.d(TAG, prod.toString());
        }

        return prodList;
    }

    public List<Product> getAccessoriesList(){
        List<Product> prodList = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCT  +
                " WHERE " + KEY_CATEGORY_ID + " = 1 " +
                "OR " + KEY_CATEGORY_ID + " = 2 " +
                "OR " + KEY_CATEGORY_ID + " = 8 ";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            Product prod = new Product();
            prod.setProduct_name(res.getString(res.getColumnIndex(KEY_PROD_NAME)));
            prod.setPrice(Double.parseDouble(res.getString((res.getColumnIndex(KEY_PRICE)))));
            prod.setBrand(Integer.parseInt(res.getString(res.getColumnIndex(KEY_BRAND_ID))));
            prod.setCategory(Integer.parseInt(res.getString(res.getColumnIndex(KEY_CATEGORY_ID))));
            prodList.add(prod);
            Log.d(TAG, prod.toString());
        }

        return prodList;
    }

    public List<Product> getMonitorList(){
        List<Product> prodList = new ArrayList<Product>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCT  + " WHERE " + KEY_CATEGORY_ID + " = 4";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            Product prod = new Product();
            prod.setProduct_name(res.getString(res.getColumnIndex(KEY_PROD_NAME)));
            prod.setPrice(Double.parseDouble(res.getString((res.getColumnIndex(KEY_PRICE)))));
            prod.setBrand(Integer.parseInt(res.getString(res.getColumnIndex(KEY_BRAND_ID))));
            prod.setCategory(Integer.parseInt(res.getString(res.getColumnIndex(KEY_CATEGORY_ID))));
            prodList.add(prod);
            Log.d(TAG, prod.toString());
        }

        return prodList;
    }


    public String getUserFullName(String username){
        String fullname = "";

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME + " = '" + username + "'";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            fullname = res.getString(res.getColumnIndex(KEY_FULLNAME));
        }

        return fullname;
    }




//    IF THERE IS NO RECORD
//    ROW COUNT IN CURSOR WILL RETURN 0
    public int checkCredentials(String username, String password){
        String dbusername = "";
        String dbpassword = "";
        int db_usertype = -1;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME + " = '" + username + "'";
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        if(res.getCount()==0){
            return -1;
        }

        else{
            while(res.moveToNext()){
                dbusername = res.getString(res.getColumnIndex(KEY_USERNAME));
                dbpassword = res.getString(res.getColumnIndex(KEY_PASSWORD));
                db_usertype = Integer.parseInt(res.getString(res.getColumnIndex(KEY_USERTYPE)));
            }
        }

        if(username.equalsIgnoreCase(dbusername) && password.equalsIgnoreCase(dbpassword)) {
            return db_usertype;
        }
        else if(username.equalsIgnoreCase(dbusername) && !password.equalsIgnoreCase(dbpassword)){
            return 3;
        } else if(!username.equalsIgnoreCase(dbusername)){
            return -1;
        }

        return -1;
    }


//    Return brand image base from the brand id
//    data will be coming from brand table
    public int getImageResource(int brand_id){
        int resourceId = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_BRAND + " WHERE " + KEY_ID + " = " + brand_id;
        Log.e(TAG,query);

        Cursor res = db.rawQuery(query, null);
        while(res.moveToNext()){
            resourceId = Integer.parseInt(res.getString(res.getColumnIndex(KEY_BRAND_IMAGE)));
        }
     
        return resourceId;
    }



    /**
     *
     *
     * INSERTION OF DEFAULT VALUES IN THE DATABASE
     * DEFAULT USERS
     * DEFAULT PRODUCT
     * DEFAULT CATEGORY
     * DEFAULT BRAND
     *
     *
     */

    public void insertUserData(SQLiteDatabase db){
        String query =
                String.format(
                        "INSERT INTO %s (%s, %s, %s, %s, %s, %s) " +
                        "VALUES(1, 'Administrator', 'admin', 'admin', 2, '" +getDateTime() + "')",
                        TABLE_USER, KEY_ID, KEY_FULLNAME, KEY_USERNAME, KEY_PASSWORD, KEY_USERTYPE, KEY_CREATED_AT
                );
        Log.w(TAG, query);
        db.execSQL(query);
    }

    public void insertCategoryData(SQLiteDatabase db){
        String query =
                "INSERT INTO tbl_category (ID, CATEGORY_NAME) " +
                "VALUES(1, 'Keyboard'), " +
                "(2, 'Mouse'), " +
                "(3, 'Processor'), " +
                "(4, 'Monitor'), " +
                "(5, 'Motherboard')," +
                "(6,'Memory')," +
                "(7, 'Video Card')," +
                "(8, 'Accessories')";
        Log.w(TAG, query);
        db.execSQL(query);

    }

    public void insertBrandData(SQLiteDatabase db){
        String query =
                "INSERT INTO tbl_brand (ID, BRAND_NAME, BRAND_IMAGE) " +
                "VALUES " +
                "(1, 'Amd', '" + R.drawable.amd + "'), " +
                "(2, 'Asus', '" + R.drawable.asus + "'), " +
                "(3, 'Intel', '" + R.drawable.intel + "'), " +
                "(4, 'Logitech', '" + R.drawable.logitech + "'), " +
                "(5, 'MSI', '" + R.drawable.msi + "'), " +
                "(6, 'Samsung', '" + R.drawable.samsung + "'), " +
                "(7, 'A4Tech', '" + R.drawable.a4tech + "'), " +
                "(8, 'Others', '" + + R.drawable.no_image + "')";

        Log.w(TAG, query);
        db.execSQL(query);


    }

    public void insertDefaultProducts(SQLiteDatabase db){
        String query = String.format(
                "INSERT INTO %s (%s, %s, %s, %s)",
                TABLE_PRODUCT, KEY_CATEGORY_ID, KEY_BRAND_ID, KEY_PROD_NAME, KEY_PRICE) +
                "VALUES" +
                //CPU
                "(3, 3, 'Core i3 4170', '6100'), " +
                "(3, 3, 'Core i5 4460', '9720'), " +
                "(3, 3, 'Core i7 6700', '16200'), " +
                "(3, 1, 'A4-7300' , '1780'), " +
                "(3, 1, 'A6-6400' , '2110'), " +
                "(3, 1, 'A8-7600' , '3350'), " +
                "(5, 7, 'RX570 Gaming RGB 4GB', '16420'),  " +
                "(5, 7, 'RX580 Armor OC 8GB' , '17010'), " +
                "(5, 7, 'GTX1060 GamingX OC' , '19440'), " +

                //Accessories
                "(1, 7, 'TK-5 USB', '320'),  " +
                "(1, 4, 'K100' , '260'), " +
                "(1, 4, 'N2500' , '1780'), " +
                "(2, 7, 'OP-320D USB' , '210'), " +
                "(2, 7, 'V5', '1030'),  " +
                "(2, 4, 'V7' , '1070'), " +
                "(2, 7, 'OP-320D USB' , '210'), " +
                "(2, 7, 'V5', '1030'),  " +
                "(2, 4, 'V7' , '1070'), " +

                //Monitors
                "(4, 6, '18.5\" F350', '4700'),  " +
                "(4, 6, '18.5\" ER10 HDMI' , '4860'), " +
                "(4, 6, '20\" S20C150' , '5080');";


        Log.w(TAG, query);
        db.execSQL(query);

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}

