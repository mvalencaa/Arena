/**
 * 
 */
package br.snt.app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author mvalencaa
 * @since 12/07/2011
 * 
 */
public class NotesDbAdapter {

	public static final String KEY_ID = "_id";
	public static final String KEY_VERSE_ID = "verse_id";
	public static final String KEY_TEXT = "text";
	public static final String KEY_CREATE_DATE = "create_date";
	public static final String KEY_MODIFICATION_DATE = "modification_date";

	// private static final String TAG = "NotesDbAdapter";
	private SQLiteDatabase mDb;

	private static final String DATABASE_TABLE = "notes";

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public NotesDbAdapter(SQLiteDatabase db) {
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
	public long createNote(long verse_id, String text) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_VERSE_ID, verse_id);
		initialValues.put(KEY_TEXT, text);

		long now = Long.valueOf(System.currentTimeMillis());

		initialValues.put(KEY_CREATE_DATE, now);
		initialValues.put(KEY_MODIFICATION_DATE, now);

		// Não está salvando a MODIFICATION_DATE
		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the note with the given rowId
	 * 
	 * @param rowId
	 *            id of note to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteNote(long rowId) {

		return mDb.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all notes in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllNotes() {
		return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
	}

	/**
	 * Return a Cursor over the list of all notes in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllNotesByVerseId(long verseId) {
		return mDb.query(DATABASE_TABLE, null, KEY_VERSE_ID + "=" + verseId,
				null, null, null, null);
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
	public Cursor fetchNote(long rowId) throws SQLException {
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
	public boolean updateNote(long rowId, String text) {
		ContentValues args = new ContentValues();
		args.put(KEY_TEXT, text);
		args.put(KEY_MODIFICATION_DATE, Long
				.valueOf(System.currentTimeMillis()));

		return mDb.update(DATABASE_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
}