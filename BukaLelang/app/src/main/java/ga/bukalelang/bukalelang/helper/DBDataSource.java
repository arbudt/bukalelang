package ga.bukalelang.bukalelang.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ga.bukalelang.bukalelang.model.User;

/**
 * Created by arbudt on 5/25/2017.
 */

public class DBDataSource {
    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allColumns ={
            DBHelper.column_id,
            DBHelper.column_user_id,
            DBHelper.column_user_name,
            DBHelper.column_confirmed,
            DBHelper.column_token,
            DBHelper.column_email,
            DBHelper.column_confirmed_phone,
            DBHelper.column_omnikey
    };

    public DBDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //insert user
    public long createUser(String user_id, String user_name, String confirmed, String token, String email, String confirmed_phone, String omnikey){
        ContentValues values = new ContentValues();
        values.put(DBHelper.column_user_id, user_id);
        values.put(DBHelper.column_user_name, user_name);
        values.put(DBHelper.column_confirmed, confirmed);
        values.put(DBHelper.column_token, token);
        values.put(DBHelper.column_email, email);
        values.put(DBHelper.column_confirmed_phone, confirmed_phone);
        values.put(DBHelper.column_omnikey, omnikey);

        long insertId = database.insert(DBHelper.table_name,null, values);

        return insertId;
    }

    public void getUserById(long idUser){
        Cursor cursor = database.query(DBHelper.table_name,
                allColumns, DBHelper.column_id + " = " + idUser, null,
                null, null, null);
        cursor.moveToFirst();

        if(cursor.getString(0) != null){
            User.setUser_id(cursor.getString(0));
            User.setUser_name(cursor.getString(1));
            User.setConfirmed(cursor.getString(2));
            User.setToken(cursor.getString(3));
            User.setEmail(cursor.getString(4));
            User.setConfirmed_phone(cursor.getString(5));
            User.setOmnikey(cursor.getString(6));
        }
    }

    public void getUser(){
        Cursor cursor = database.query(DBHelper.table_name,
                allColumns, DBHelper.column_id + " is not null", null,
                null, null, null);
        cursor.moveToFirst();
        if(cursor.getString(0) != null){
            User.setUser_id(cursor.getString(0));
            User.setUser_name(cursor.getString(1));
            User.setConfirmed(cursor.getString(2));
            User.setToken(cursor.getString(3));
            User.setEmail(cursor.getString(4));
            User.setConfirmed_phone(cursor.getString(5));
            User.setOmnikey(cursor.getString(6));
        }
    }
}
