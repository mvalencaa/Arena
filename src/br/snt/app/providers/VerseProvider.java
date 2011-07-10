package br.snt.app.providers;

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
import android.util.Log;
import br.snt.app.entities.Verse;

/**
 * Provides access to a database of notes. Each note has a title, the note
 * itself, a creation date and a modified data.
 * 
 * @author mvalencaa
 * @since 07/07/2011
 * 
 */
public class VerseProvider extends ContentProvider {

	// Used for debugging and logging
	private static final String TAG = "VerseProvider";

	/**
	 * The database that the provider uses as its underlying data store
	 */
	private static final String DATABASE_NAME = "arena_mobile.db";

	/**
	 * The database version
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * A projection map used to select columns from the database
	 */
	private static HashMap<String, String> sVersesProjectionMap;

	/**
	 * Standard projection for the interesting columns of a normal verse.
	 */
	private static final String[] READ_VERSE_PROJECTION = new String[] {
			Verse.VerseContract._ID, // Projection position 0, the verse's id.
			Verse.VerseContract.COLUMN_NAME_BOOK_ID, // Projection position 1,
			// the verse's book id.
			Verse.VerseContract.COLUMN_NAME_CHAPTER, // Projection position 2,
			// the verse's chapter.
			Verse.VerseContract.COLUMN_NAME_NUMBER, // Projection position 3,
			// the verse's number.
			Verse.VerseContract.COLUMN_NAME_TEXT, // Projection position 4, the
			// verse's text.
			Verse.VerseContract.COLUMN_NAME_MARKED, // Projection position 5,
	// the marked verse.
	};
	private static final int READ_VERSE_BOOK_ID_INDEX = 1;
	private static final int READ_VERSE_CHAPTER_INDEX = 2;
	private static final int READ_VERSE_NUMBER_INDEX = 3;
	private static final int READ_VERSE_TEXT_INDEX = 4;
	private static final int READ_VERSE_MARKED_INDEX = 5;

	/*
	 * Constants used by the Uri matcher to choose an action based on the
	 * pattern of the incoming URI
	 */
	// The incoming URI matches the Notes URI pattern
	private static final int VERSES = 1;

	// The incoming URI matches the Note ID URI pattern
	private static final int VERSE_ID = 2;

	/**
	 * A UriMatcher instance
	 */
	private static final UriMatcher sUriMatcher;

	// Handle to a new DatabaseHelper.
	private DatabaseHelper mOpenHelper;

	/**
	 * A block that instantiates and sets static objects
	 */
	static {

		/*
		 * Creates and initializes the URI matcher
		 */
		// Create a new instance
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		// Add a pattern that routes URIs terminated with "verses" to a VERSES
		// operation
		sUriMatcher.addURI(Verse.AUTHORITY, "verses", VERSES);

		// Add a pattern that routes URIs terminated with "verses" plus an
		// integer to a verse ID operation
		sUriMatcher.addURI(Verse.AUTHORITY, "verses/#", VERSE_ID);

		/*
		 * Creates and initializes a projection map that returns all columns
		 */

		// Creates a new projection map instance. The map returns a column name
		// given a string. The two are usually equal.
		sVersesProjectionMap = new HashMap<String, String>();

		// Maps the string "_ID" to the column name "_ID"
		sVersesProjectionMap.put(Verse.VerseContract._ID,
				Verse.VerseContract._ID);

		// Maps "title" to "title"
		sVersesProjectionMap.put(Verse.VerseContract.COLUMN_NAME_BOOK_ID,
				Verse.VerseContract.COLUMN_NAME_BOOK_ID);

		// Maps "note" to "note"
		sVersesProjectionMap.put(Verse.VerseContract.COLUMN_NAME_CHAPTER,
				Verse.VerseContract.COLUMN_NAME_CHAPTER);

		// Maps "note" to "note"
		sVersesProjectionMap.put(Verse.VerseContract.COLUMN_NAME_NUMBER,
				Verse.VerseContract.COLUMN_NAME_NUMBER);

		// Maps "note" to "note"
		sVersesProjectionMap.put(Verse.VerseContract.COLUMN_NAME_TEXT,
				Verse.VerseContract.COLUMN_NAME_TEXT);

		// Maps "note" to "note"
		sVersesProjectionMap.put(Verse.VerseContract.COLUMN_NAME_MARKED,
				Verse.VerseContract.COLUMN_NAME_MARKED);
	}

	/**
	 * 
	 * This class helps open, create, and upgrade the database file. Set to
	 * package visibility for testing purposes.
	 */
	static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {

			// calls the super constructor, requesting the default cursor
			// factory.
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * 
		 * Creates the underlying database with table name and column names
		 * taken from the Verse class.
		 */
		// TODO Verificar os tipos disponiveis para os dados no banco!
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + Verse.VerseContract.TABLE_NAME + " ("
					+ Verse.VerseContract._ID + " INTEGER PRIMARY KEY,"
					+ Verse.VerseContract.COLUMN_NAME_BOOK_ID + " INTEGER,"
					+ Verse.VerseContract.COLUMN_NAME_CHAPTER + " INTEGER,"
					+ Verse.VerseContract.COLUMN_NAME_NUMBER + " INTEGER,"
					+ Verse.VerseContract.COLUMN_NAME_TEXT + " TEXT,"
					+ Verse.VerseContract.COLUMN_NAME_MARKED + " INTEGER"
					+ ");");
		}

		/**
		 * 
		 * Demonstrates that the provider must consider what happens when the
		 * underlying datastore is changed. In this sample, the database is
		 * upgraded the database by destroying the existing data. A real
		 * application should upgrade the database in place.
		 */
		// TODO Refatorar esse metodo para nao apagar o banco de dados!
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			// Logs that the database is being upgraded
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			// Kills the table and existing data
			db.execSQL("DROP TABLE IF EXISTS verses");

			// Recreates the database with a new version
			onCreate(db);
		}
	}

	/**
	 * 
	 * Initializes the provider by creating a new DatabaseHelper. onCreate() is
	 * called automatically when Android creates the provider in response to a
	 * resolver request from a client.
	 */
	@Override
	public boolean onCreate() {

		// Creates a new helper object. Note that the database itself isn't
		// opened until something tries to access it, and it's only created if
		// it doesn't already exist.
		mOpenHelper = new DatabaseHelper(getContext());

		// Assumes that any failures will be reported by a thrown exception.
		return true;
	}

	/**
	 * This method is called when a client calls
	 * {@link android.content.ContentResolver#query(Uri, String[], String, String[], String)}
	 * . Queries the database and returns a cursor containing the results.
	 * 
	 * @return A cursor containing the results of the query. The cursor exists
	 *         but is empty if the query returns no results or an exception
	 *         occurs.
	 * @throws IllegalArgumentException
	 *             if the incoming URI pattern is invalid.
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Constructs a new query builder and sets its table name
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(Verse.VerseContract.TABLE_NAME);

		/**
		 * Choose the projection and adjust the "where" clause based on URI
		 * pattern-matching.
		 */
		switch (sUriMatcher.match(uri)) {
		// If the incoming URI is for notes, chooses the Notes projection
		case VERSES:
			qb.setProjectionMap(sVersesProjectionMap);
			break;

		/*
		 * If the incoming URI is for a single note identified by its ID,
		 * chooses the note ID projection, and appends "_ID = <noteID>" to the
		 * where clause, so that it selects that single note
		 */
		case VERSE_ID:
			qb.setProjectionMap(sVersesProjectionMap);
			qb.appendWhere(Verse.VerseContract._ID
					+ "=" +
					// the position of the note ID itself in the incoming URI
					uri.getPathSegments().get(
							Verse.VerseContract.VERSE_ID_PATH_POSITION));
			break;

		default:
			// If the URI doesn't match any of the known patterns, throw an
			// exception.
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = Verse.VerseContract.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		// Opens the database object in "read" mode, since no writes need to be
		// done.
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		/*
		 * Performs the query. If no problems occur trying to read the database,
		 * then a Cursor object is returned; otherwise, the cursor variable
		 * contains null. If no records were selected, then the Cursor object is
		 * empty, and Cursor.getCount() returns 0.
		 */
		Cursor c = qb.query(db, // The database to query
				projection, // The columns to return from the query
				selection, // The columns for the where clause
				selectionArgs, // The values for the where clause
				null, // don't group the rows
				null, // don't filter by row groups
				orderBy // The sort order
				);

		// Tells the Cursor what URI to watch, so it knows when its source data
		// changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		
		return c;
	}

	/**
	 * This is called when a client calls
	 * {@link android.content.ContentResolver#getType(Uri)}. Returns the MIME
	 * data type of the URI given as a parameter.
	 * 
	 * @param uri
	 *            The URI whose MIME type is desired.
	 * @return The MIME type of the URI.
	 * @throws IllegalArgumentException
	 *             if the incoming URI pattern is invalid.
	 */
	@Override
	//TODO Entender esse metodo!
	public String getType(Uri uri) {

		/**
		 * Chooses the MIME type based on the incoming URI pattern
		 */
		switch (sUriMatcher.match(uri)) {

		// If the pattern is for verses, returns the general
		// content type.
		case VERSES:
			return Verse.VerseContract.CONTENT_TYPE;

			// If the pattern is for note IDs, returns the note ID content type.
		case VERSE_ID:
			return Verse.VerseContract.CONTENT_ITEM_TYPE;

			// If the URI pattern doesn't match any permitted patterns, throws
			// an exception.
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	/**
	 * This describes the MIME types that are supported for opening a note URI
	 * as a stream.
	 *//*
	static ClipDescription NOTE_STREAM_TYPES = new ClipDescription(null,
			new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN });*/

	/**
	 * Returns the types of available data streams. URIs to specific notes are
	 * supported. The application can convert such a note to a plain text
	 * stream.
	 * 
	 * @param uri
	 *            the URI to analyze
	 * @param mimeTypeFilter
	 *            The MIME type to check for. This method only returns a data
	 *            stream type for MIME types that match the filter. Currently,
	 *            only text/plain MIME types match.
	 * @return a data stream MIME type. Currently, only text/plan is returned.
	 * @throws IllegalArgumentException
	 *             if the URI pattern doesn't match any supported patterns.
	 */
	/*@Override
	public String[] getStreamTypes(Uri uri, String mimeTypeFilter) {
		*//**
		 * Chooses the data stream type based on the incoming URI pattern.
		 *//*
		switch (sUriMatcher.match(uri)) {

		// If the pattern is for notes or live folders, return null. Data
		// streams are not
		// supported for this type of URI.
		case VERSES:
			return null;

			// If the pattern is for note IDs and the MIME filter is text/plain,
			// then return
			// text/plain
		case VERSE_ID:
			return NOTE_STREAM_TYPES.filterMimeTypes(mimeTypeFilter);

			// If the URI pattern doesn't match any permitted patterns, throws
			// an exception.
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}*/

	/**
	 * Returns a stream of data for each supported stream type. This method does
	 * a query on the incoming URI, then uses
	 * {@link android.content.ContentProvider#openPipeHelper(Uri, String, Bundle, Object, PipeDataWriter)}
	 * to start another thread in which to convert the data into a stream.
	 * 
	 * @param uri
	 *            The URI pattern that points to the data stream
	 * @param mimeTypeFilter
	 *            A String containing a MIME type. This method tries to get a
	 *            stream of data with this MIME type.
	 * @param opts
	 *            Additional options supplied by the caller. Can be interpreted
	 *            as desired by the content provider.
	 * @return AssetFileDescriptor A handle to the file.
	 * @throws FileNotFoundException
	 *             if there is no file associated with the incoming URI.
	 */
	/*@Override
	public AssetFileDescriptor openTypedAssetFile(Uri uri,
			String mimeTypeFilter, Bundle opts) throws FileNotFoundException {

		// Checks to see if the MIME type filter matches a supported MIME type.
		String[] mimeTypes = getStreamTypes(uri, mimeTypeFilter);

		// If the MIME type is supported
		if (mimeTypes != null) {

			// Retrieves the note for this URI. Uses the query method defined
			// for this provider,
			// rather than using the database query method.
			Cursor c = query(uri, // The URI of a note
					READ_VERSE_PROJECTION, // Gets a projection containing the
					// note's ID, title,
					// and contents
					null, // No WHERE clause, get all matching records
					null, // Since there is no WHERE clause, no selection
					// criteria
					null // Use the default sort order (modification date,
			// descending
			);

			// If the query fails or the cursor is empty, stop
			if (c == null || !c.moveToFirst()) {

				// If the cursor is empty, simply close the cursor and return
				if (c != null) {
					c.close();
				}

				// If the cursor is null, throw an exception
				throw new FileNotFoundException("Unable to query " + uri);
			}

			// Start a new thread that pipes the stream data back to the caller.
			return new AssetFileDescriptor(openPipeHelper(uri, mimeTypes[0],
					opts, c, this), 0, AssetFileDescriptor.UNKNOWN_LENGTH);
		}

		// If the MIME type is not supported, return a read-only handle to the
		// file.
		return super.openTypedAssetFile(uri, mimeTypeFilter, opts);
	}*/

	/**
	 * Implementation of {@link android.content.ContentProvider.PipeDataWriter}
	 * to perform the actual work of converting the data in one of cursors to a
	 * stream of data for the client to read.
	 */
	/*@Override
	public void writeDataToPipe(ParcelFileDescriptor output, Uri uri,
			String mimeType, Bundle opts, Cursor c) {
		// We currently only support conversion-to-text from a single note
		// entry, so no need for cursor data type checking here.
		FileOutputStream fout = new FileOutputStream(output.getFileDescriptor());
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(fout, "UTF-8"));
			pw.println(c.getString(READ_VERSE_BOOK_ID_INDEX));
			pw.println("");
			pw.println(c.getString(READ_VERSE_CHAPTER_INDEX));
			pw.println("");
			pw.println(c.getString(READ_VERSE_NUMBER_INDEX));
			pw.println("");
			pw.println(c.getString(READ_VERSE_TEXT_INDEX));
			pw.println("");
			pw.println(c.getString(READ_VERSE_MARKED_INDEX));
		} catch (UnsupportedEncodingException e) {
			Log.w(TAG, "Ooops", e);
		} finally {
			c.close();
			if (pw != null) {
				pw.flush();
			}
			try {
				fout.close();
			} catch (IOException e) {
			}
		}
	}*/

	/**
	 * This is called when a client calls
	 * {@link android.content.ContentResolver#insert(Uri, ContentValues)}.
	 * Inserts a new row into the database. This method sets up default values
	 * for any columns that are not included in the incoming map. If rows were
	 * inserted, then listeners are notified of the change.
	 * 
	 * @return The row ID of the inserted row.
	 * @throws SQLException
	 *             if the insertion fails.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {

		// Validates the incoming URI. Only the full provider URI is allowed for
		// inserts.
		if (sUriMatcher.match(uri) != VERSES) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// A map to hold the new record's values.
		ContentValues values;

		// If the incoming values map is not null, uses it for the new values.
		if (initialValues != null) {
			values = new ContentValues(initialValues);

		} else {
			// Otherwise, create a new value map
			values = new ContentValues();
		}

		// Gets the current system time in milliseconds
		Long now = Long.valueOf(System.currentTimeMillis());

		// Opens the database object in "write" mode.
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		// Performs the insert and returns the ID of the new note.
		long rowId = db.insert(Verse.VerseContract.TABLE_NAME, // The table to
				// insert
				// into.
				null, // A hack, SQLite sets this
				// column value to null
				// if values is empty.
				values // A map of column names, and the values to insert
				// into the columns.
				);

		// If the insert succeeded, the row ID exists.
		if (rowId > 0) {
			// Creates a URI with the note ID pattern and the new row ID
			// appended to it.
			Uri noteUri = ContentUris.withAppendedId(
					Verse.VerseContract.CONTENT_ID_URI_BASE, rowId);

			// Notifies observers registered against this provider that the data
			// changed.
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}

		// If the insert didn't succeed, then the rowID is <= 0. Throws an
		// exception.
		throw new SQLException("Failed to insert row into " + uri);
	}

	/**
	 * This is called when a client calls
	 * {@link android.content.ContentResolver#delete(Uri, String, String[])}.
	 * Deletes records from the database. If the incoming URI matches the note
	 * ID URI pattern, this method deletes the one record specified by the ID in
	 * the URI. Otherwise, it deletes a a set of records. The record or records
	 * must also match the input selection criteria specified by where and
	 * whereArgs.
	 * 
	 * If rows were deleted, then listeners are notified of the change.
	 * 
	 * @return If a "where" clause is used, the number of rows affected is
	 *         returned, otherwise 0 is returned. To delete all rows and get a
	 *         row count, use "1" as the where clause.
	 * @throws IllegalArgumentException
	 *             if the incoming URI pattern is invalid.
	 */
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {

		// Opens the database object in "write" mode.
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String finalWhere;

		int count;

		// Does the delete based on the incoming URI pattern.
		switch (sUriMatcher.match(uri)) {

		// If the incoming pattern matches the general pattern for notes, does a
		// delete
		// based on the incoming "where" columns and arguments.
		case VERSES:
			count = db.delete(Verse.VerseContract.TABLE_NAME, // The database
					// table
					// name
					where, // The incoming where clause column names
					whereArgs // The incoming where clause values
					);
			break;

		// If the incoming URI matches a single note ID, does the delete based
		// on the
		// incoming data, but modifies the where clause to restrict it to the
		// particular note ID.
		case VERSE_ID:
			/*
			 * Starts a final WHERE clause by restricting it to the desired note
			 * ID.
			 */
			finalWhere = Verse.VerseContract._ID + // The ID column name
					" = " + // test for equality
					uri.getPathSegments(). // the incoming note ID
							get(Verse.VerseContract.VERSE_ID_PATH_POSITION);

			// If there were additional selection criteria, append them to the
			// final
			// WHERE clause
			if (where != null) {
				finalWhere = finalWhere + " AND " + where;
			}

			// Performs the delete.
			count = db.delete(Verse.VerseContract.TABLE_NAME, // The database
					// table
					// name.
					finalWhere, // The final WHERE clause
					whereArgs // The incoming where clause values.
					);
			break;

		// If the incoming pattern is invalid, throws an exception.
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		/*
		 * Gets a handle to the content resolver object for the current context,
		 * and notifies it that the incoming URI changed. The object passes this
		 * along to the resolver framework, and observers that have registered
		 * themselves for the provider are notified.
		 */
		getContext().getContentResolver().notifyChange(uri, null);

		// Returns the number of rows deleted.
		return count;
	}

	/**
	 * This is called when a client calls
	 * {@link android.content.ContentResolver#update(Uri,ContentValues,String,String[])}
	 * Updates records in the database. The column names specified by the keys
	 * in the values map are updated with new data specified by the values in
	 * the map. If the incoming URI matches the note ID URI pattern, then the
	 * method updates the one record specified by the ID in the URI; otherwise,
	 * it updates a set of records. The record or records must match the input
	 * selection criteria specified by where and whereArgs. If rows were
	 * updated, then listeners are notified of the change.
	 * 
	 * @param uri
	 *            The URI pattern to match and update.
	 * @param values
	 *            A map of column names (keys) and new values (values).
	 * @param where
	 *            An SQL "WHERE" clause that selects records based on their
	 *            column values. If this is null, then all records that match
	 *            the URI pattern are selected.
	 * @param whereArgs
	 *            An array of selection criteria. If the "where" param contains
	 *            value placeholders ("?"), then each placeholder is replaced by
	 *            the corresponding element in the array.
	 * @return The number of rows updated.
	 * @throws IllegalArgumentException
	 *             if the incoming URI pattern is invalid.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {

		// Opens the database object in "write" mode.
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		String finalWhere;

		// Does the update based on the incoming URI pattern
		switch (sUriMatcher.match(uri)) {

		// If the incoming URI matches the general notes pattern, does the
		// update based on
		// the incoming data.
		case VERSES:

			// Does the update and returns the number of rows updated.
			count = db.update(Verse.VerseContract.TABLE_NAME, // The database
					// table
					// name.
					values, // A map of column names and new values to use.
					where, // The where clause column names.
					whereArgs // The where clause column values to select on.
					);
			break;

		// If the incoming URI matches a single note ID, does the update based
		// on the incoming
		// data, but modifies the where clause to restrict it to the particular
		// note ID.
		case VERSE_ID:
			// From the incoming URI, get the note ID
			String noteId = uri.getPathSegments().get(
					Verse.VerseContract.VERSE_ID_PATH_POSITION);

			/*
			 * Starts creating the final WHERE clause by restricting it to the
			 * incoming note ID.
			 */
			finalWhere = Verse.VerseContract._ID + // The ID column name
					" = " + // test for equality
					uri.getPathSegments(). // the incoming note ID
							get(Verse.VerseContract.VERSE_ID_PATH_POSITION);

			// If there were additional selection criteria, append them to the
			// final WHERE
			// clause
			if (where != null) {
				finalWhere = finalWhere + " AND " + where;
			}

			// Does the update and returns the number of rows updated.
			count = db.update(Verse.VerseContract.TABLE_NAME, // The database
					// table
					// name.
					values, // A map of column names and new values to use.
					finalWhere, // The final WHERE clause to use
					// placeholders for whereArgs
					whereArgs // The where clause column values to select on, or
					// null if the values are in the where argument.
					);
			break;
		// If the incoming pattern is invalid, throws an exception.
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		/*
		 * Gets a handle to the content resolver object for the current context,
		 * and notifies it that the incoming URI changed. The object passes this
		 * along to the resolver framework, and observers that have registered
		 * themselves for the provider are notified.
		 */
		getContext().getContentResolver().notifyChange(uri, null);

		// Returns the number of rows updated.
		return count;
	}

	/**
	 * A test package can call this to get a handle to the database underlying
	 * NotePadProvider, so it can insert test data into the database. The test
	 * case class is responsible for instantiating the provider in a test
	 * context; {@link android.test.ProviderTestCase2} does this during the call
	 * to setUp()
	 * 
	 * @return a handle to the database helper object for the provider's data.
	 */
	DatabaseHelper getOpenHelperForTest() {
		return mOpenHelper;
	}

}
