package ga.bukalelang.bukalelang.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by arbudt on 5/25/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String db_name = "bukalelang.db";
    public static final String table_name = "user";
    public static final String column_id = "_id";
    public static final String column_user_id = "user_id";
    public static final String column_user_name = "user_name";
    public static final String column_confirmed = "confirmed";
    public static final String column_token = "token";
    public static final String column_email = "email";
    public static final String column_confirmed_phone = "confirmed_phone";
    public static final String column_omnikey = "omnikey";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + table_name + " ("
            + column_id +" integer primary key autoincrement, "
            + column_user_id+ " varchar(200) not null, "
            + column_user_name+ " varchar(200) not null, "
            + column_confirmed+ " varchar(200), "
            + column_token+ " varchar(200), "
            + column_email+ " varchar(200) , "
            + column_confirmed_phone+ " varchar(200), "
            + column_omnikey+ " varchar(200));";
    public DBHelper(Context context){
        super(context, db_name,null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
}
