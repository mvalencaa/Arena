/**
 * 
 */
package br.snt.app.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author mvalencaa
 * @since 09/07/2011
 * 
 */
public class BooksDbAdapter {
	
	public static final int GENESIS = 1;
	public static final int EXODUS = 2;
	public static final int LEVITICUS = 3;
	public static final int NUMBERS = 4;
	public static final int DEUTERONOMY = 5;
	public static final int JOSHUA = 6;
	public static final int JUDGES = 7;
	public static final int RUTH = 8;
	public static final int SAMUEL_1 = 9;
	public static final int SAMUEL_2 = 10;
	public static final int KINGS_1 = 11;
	public static final int KINGS_2 = 12;
	public static final int CHRONICLES_1 = 13;
	public static final int CHRONICLES_2 = 14; //Continuar mudando as numeracoes.
	public static final int EZRA = 14;
	public static final int NEHEMIAH = 15;
	public static final int ESTHER = 16;
	public static final int JOB = 17;
	public static final int PSALMS = 18;
	public static final int PROVERBS = 19;
	public static final int ECCLESIASTES = 20;
	public static final int SONG_OF_SONGS = 21;
	public static final int ISAIAH = 22;
	public static final int JEREMIAH = 23;
	public static final int LAMENTATIONS = 24;
	public static final int EZEKIEL = 25;
	public static final int DANIEL = 26;
	public static final int HOSEA = 27;
	public static final int JOEL = 28;
	public static final int AMOS = 29;
	public static final int OBADIAH = 30;
	public static final int JONAH = 31;
	public static final int MICAH = 32;
	public static final int NAHUM = 33;
	public static final int HABAKKUK = 34;
	public static final int ZEPHANIAH = 35;
	public static final int HAGGAI = 36;
	public static final int ZECHARIAH = 37;
	public static final int MALACHI = 38;
	public static final int MATTHEW = 39;
	public static final int MARK = 40;
	public static final int LUKE = 41;
	public static final int JOHN = 42;
	public static final int ATOS = 43;
	public static final int ROMAN = 44;
	public static final int CORINTHIANS_1 = 45;
	public static final int CORINTHIANS_2 = 46;
	public static final int GALATIANS = 47;
	public static final int EPHESIANS = 48;
	public static final int PHILIPPIANS = 49;
	public static final int COLOSSIANS = 50;
	public static final int THESSALONIANS_1 = 51;
	public static final int THESSALONIANS_2 = 52;
	public static final int TIMOTHY_1 = 53;
	public static final int TIMOTHY_2 = 54;
	public static final int TITUS = 55;
	public static final int PHILEMON = 56;
	public static final int HEBREWS = 57;
	public static final int JAMES = 58;
	public static final int PETER_1 = 59;
	public static final int PETER_2 = 60;
	public static final int JOHN_1 = 61;
	public static final int JOHN_2 = 62;
	public static final int JOHN_3 = 63;
	public static final int JUDE = 64;
	public static final int REVELATION = 65;

	public static final String KEY_ID = "_id";
	public static final String KEY_TITLE = "title";

//	private static final String TAG = "BooksDbAdapter";
	private SQLiteDatabase mDb;

	private static final String DATABASE_TABLE = "books";

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public BooksDbAdapter(SQLiteDatabase db) {
		this.mDb = db;
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
	// public BooksDbAdapter open() throws SQLException {
	// mDbHelper = new DatabaseHelper(mCtx);
	// mDb = mDbHelper.getWritableDatabase();
	// return this;
	// }
	//
	// public void close() {
	// mDbHelper.close();
	// }

	/**
	 * Create a new note using the title and body provided. If the note is
	 * successfully created return the new rowId for that note, otherwise return
	 * a -1 to indicate failure.
	 * 
	 * @param title
	 *            the title of the note
	 * @param body
	 *            the body of the note
	 * @return rowId or -1 if failed
	 */
	// public long createVerse(String title, String body) {
	// ContentValues initialValues = new ContentValues();
	// initialValues.put(KEY_BOOK_ID, title);
	// initialValues.put(KEY_CHAPTER, body);
	//
	// return mDb.insert(DATABASE_TABLE, null, initialValues);
	// }

	/**
	 * Delete the note with the given rowId
	 * 
	 * @param rowId
	 *            id of note to delete
	 * @return true if deleted, false otherwise
	 */
	// public boolean deleteVerse(long rowId) {
	//
	// return mDb.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
	// }

	/**
	 * Return a Cursor over the list of all notes in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllBooks() {
		return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the note that matches the given rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching note, if found
	 * @throws SQLException
	 *             if note could not be found/retrieved
	 */
	public Cursor fetchBook(long rowId) throws SQLException {
		Cursor mCursor = mDb.query(true, DATABASE_TABLE, null, KEY_ID + "="
				+ rowId, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	/**
	 * Update the note using the details provided. The note to be updated is
	 * specified using the rowId, and it is altered to use the title and body
	 * values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @param body
	 *            value to set note body to
	 * @return true if the note was successfully updated, false otherwise
	 */
	// public boolean updateBook(long rowId, int marked) {
	// ContentValues args = new ContentValues();
	// args.put(KEY_MARKED, marked);
	//
	// return mDb.update(DATABASE_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	// }
}