/**
 * 
 */
package br.snt.app.adapters;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author mvalencaa
 * @since 09/07/2011
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "DatabaseHelper";
	private static final String DATABASE_NAME = "arena_mobile";
	private static final int DATABASE_VERSION = 1;
	
	private static DatabaseHelper mInstance;

	/**
	 * Verses database creation sql statement
	 */
	private static final String VERSES_DATABASE_CREATE = "CREATE TABLE verses "
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, book_id INTEGER,"
			+ "chapter INTEGER, number INTEGER, text TEXT, marked INTEGER);";

	/**
	 * Books database creation sql statement
	 */
	private static final String BOOKS_DATABASE_CREATE = "CREATE TABLE books "
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT);";

	/**
	 * 
	 * @param context
	 */
	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static DatabaseHelper getInstance(Context context) {
		if(mInstance == null) {
			mInstance = new DatabaseHelper(context);
			
			return mInstance;
		}
		
		return mInstance;
	}

	/**
	 * Open the notes database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	// public SQLiteDatabase open() throws SQLException {
	// return getWritableDatabase();
	// }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(VERSES_DATABASE_CREATE);
		db.execSQL(BOOKS_DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS verses");
		db.execSQL("DROP TABLE IF EXISTS books");
		onCreate(db);
	}
}
