/**
 * 
 */
package br.snt.app.adapters;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author mvalencaa
 * @since 09/07/2011
 * 
 */
public class BooksDbAdapter {

	public static final String KEY_ID = "_id";
	public static final String KEY_BOOK_ID = "book_id";
	public static final String KEY_CHAPTER = "chapter";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_TEXT = "text";
	public static final String KEY_MARKED = "marked";

	private static final String TAG = "BooksDbAdapter";
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