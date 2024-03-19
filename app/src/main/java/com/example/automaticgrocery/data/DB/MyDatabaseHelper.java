package com.example.automaticgrocery.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MyDataBase.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME = "my_users";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_PASS = "user_pass";

    private static final String TABLE_NAME2 = "my_products";
    private static final String COLUMN_INTERNAL_REFERENCE = "internal_reference";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_BARCODE = "barcode";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_FILL_DATE = "fill_date";
    private static final String COLUMN_LAST_DATE = "last_date";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_TARGET_AMOUNT = "target_amount";
    private static final String COLUMN_LAST_DATE_AMOUNT = "last_date_amount";


    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_NAME + " TEXT PRIMARY KEY, " +
                        COLUMN_PASS + " TEXT);";
        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_INTERNAL_REFERENCE + " TEXT PRIMARY KEY, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_BARCODE + " TEXT, " +
                COLUMN_AMOUNT + " INTEGER, " +
                COLUMN_FILL_DATE + " TEXT, " +
                COLUMN_LAST_DATE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_TARGET_AMOUNT + " INTEGER, " +
                COLUMN_LAST_DATE_AMOUNT + " INTEGER);";
        db.execSQL(query);
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }


    //users actions//
    public void addUser(String name, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PASS, pass);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean updateUserData(String current_name, String name, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PASS, pass);

        long result = db.update(TABLE_NAME, cv, "user_name=?", new String[]{current_name});
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        }else
        {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    public void deleteOneRowUser(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "user_name=?", new String[]{username});
        if(result == -1)
        {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    //#################//










    //products actions//
    public void addProduct(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date, String category,int target_amount,int last_date_amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INTERNAL_REFERENCE, internal_reference);
        cv.put(COLUMN_PRODUCT_NAME, name);
        cv.put(COLUMN_BARCODE,barcode);
        cv.put(COLUMN_AMOUNT,amount);
        cv.put(COLUMN_FILL_DATE,fill_date);
        cv.put(COLUMN_LAST_DATE,last_date);
        cv.put(COLUMN_CATEGORY,category);
        cv.put(COLUMN_TARGET_AMOUNT,target_amount);
        cv.put(COLUMN_LAST_DATE_AMOUNT,last_date_amount);


        long result = db.insert(TABLE_NAME2,null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateProductFullData(String internal_reference, String name, String barcode, int amount, String fill_date,String last_date,String category,int target_amount,int last_date_amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, name);
        cv.put(COLUMN_BARCODE,barcode);
        cv.put(COLUMN_AMOUNT,amount);
        cv.put(COLUMN_FILL_DATE,fill_date);
        cv.put(COLUMN_LAST_DATE,last_date);
        cv.put(COLUMN_CATEGORY,category);
        cv.put(COLUMN_TARGET_AMOUNT,target_amount);
        cv.put(COLUMN_LAST_DATE_AMOUNT,last_date_amount);

        long result = db.update(TABLE_NAME2, cv, "internal_reference=?", new String[]{internal_reference});
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    public void deleteOneProduct(String internal_reference)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, "internal_reference=?", new String[]{internal_reference});
        if(result == -1)
        {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllProducts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2);
    }

    public Cursor getAllProducts()
    {
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getProductsByCategory(String category)
    {
        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE " + COLUMN_CATEGORY + " LIKE '%" + category + "%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


















    public void updateProductExpPart1(String internal_reference, int new_amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AMOUNT,new_amount);

        long result = db.update(TABLE_NAME2, cv, "internal_reference=?", new String[]{internal_reference});
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateProductExpPart2(String internal_reference, int last_date_amount, String last_date, int other_expired_removed_items_amount , int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AMOUNT,amount - other_expired_removed_items_amount);
        cv.put(COLUMN_LAST_DATE,last_date);
        cv.put(COLUMN_LAST_DATE_AMOUNT,last_date_amount);

        long result = db.update(TABLE_NAME2, cv, "internal_reference=?", new String[]{internal_reference});
        if(result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


}
