package com.Tedx.UofM;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EditNoteDBAdapter {
	public static String TITLE = "title";
	public static String BODY = "body";
	public static String ROW = "_id";
	
	 private static final String TAG = "EditNoteDbAdapter";
	    private DatabaseHelper NotesDBHelper;
	    private SQLiteDatabase NotesDB;

	    /**
	     * Database creation sql statement
	     */
	    private static final String DATABASE_CREATE =
	        "create table notes (_id integer primary key autoincrement, "
	        + "title text not null, body text not null);";

	    private static final String DATABASE_NAME = "data";
	    private static final String DATABASE_TABLE = "notes";
	    private static final int DATABASE_VERSION = 2;

	    private final Context mCtx;
	    
	    public EditNoteDBAdapter(Context ctx){
	    	this.mCtx = ctx;
	    }
	    
	    private static class DatabaseHelper extends SQLiteOpenHelper{

			public DatabaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
				
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
	            db.execSQL(DATABASE_CREATE);

			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
	            db.execSQL(DATABASE_CREATE);
	            onCreate(db);
				
			}
	    	
			
	    }
	    public EditNoteDBAdapter open() throws SQLException {
	        NotesDBHelper = new DatabaseHelper(mCtx);
	        NotesDB = NotesDBHelper.getWritableDatabase();
	        return this;
	    }

	    public void close() {
	        NotesDBHelper.close();
	    }
	    
	    public long createNote(String title, String body) {
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(TITLE, title);
	        initialValues.put(BODY, body);

	        return NotesDB.insert(DATABASE_TABLE, null, initialValues);
	    }
	    public boolean deleteNote(long rowId) {

	        return NotesDB.delete(DATABASE_TABLE, ROW + "=" + rowId, null) > 0;
	    }
	    public Cursor fetchAllNotes() {

	        return NotesDB.query(DATABASE_TABLE, new String[] {ROW, TITLE,
	                BODY}, null, null, null, null, null);
	    }
	    
	    public Cursor fetchNote(long rowId) throws SQLException {

	        Cursor mCursor =

	            NotesDB.query(true, DATABASE_TABLE, new String[] {ROW,
	                    TITLE, BODY}, ROW + "=" + rowId, null,
	                    null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;

	    }
	    
	    public boolean updateNote(long rowId, String title, String body) {
	        ContentValues args = new ContentValues();
	        args.put(TITLE, title);
	        args.put(BODY, body);

	        return NotesDB.update(DATABASE_TABLE, args, ROW + "=" + rowId, null) > 0;
	    }
	    
}
