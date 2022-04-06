package com.example.contentproviderstudent;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;

public class StudentsProvider extends ContentProvider {

    static final String Provider_Name = "com.example.contentproviderstudent.StudentsProvider";
    static final String URL = "content://" + Provider_Name + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String ID = "id";
    static final String NAME = "name";
    static final String GRADE = "grade";

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;

    static  UriMatcher uriMatcher = null;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Provider_Name,"students",STUDENTS);
        uriMatcher.addURI(Provider_Name,"students/#",STUDENT_ID);
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "College";
    static final String STUDENTS_TABLE_NAME = "students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = " CREATE TABLE " + STUDENTS_TABLE_NAME +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " + " grade TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }


    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_DB_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public boolean onCreate(){
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db == null) ? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        long rowID = db.insert(STUDENTS_TABLE_NAME,"", values);

        if (rowID > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw new SQLException("failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;
            case STUDENT_ID:
                qb.appendWhere( ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
        }
        if (sortOrder == null ||  sortOrder == ""){
            sortOrder = NAME;
        }
        Cursor c = qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);

        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selecction, String[] selectionArgs){
        int count = 0;
        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.delete(STUDENTS_TABLE_NAME,selecction,selectionArgs);
                break;
            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(STUDENTS_TABLE_NAME, ID + "=" + id +
                        (!TextUtils.isEmpty(selecction)? "AND ( " + selecction + ")" : ""),selectionArgs );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs){
        int count = 0;
        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.update(STUDENTS_TABLE_NAME, values, selection,selectionArgs);
                break;
            case  STUDENT_ID:
                count = db.update(STUDENTS_TABLE_NAME, values, ID + "=" + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection)? "AND ( " + selection + ")" : ""),selectionArgs );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;

    }

    @Override
    public String getType(Uri uri){
        switch (uriMatcher.match(uri)){
            //Get all students records
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";
            // Get a particular student record
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupportes URI: " + uri);
        }
    }





}
