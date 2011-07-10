/**
 * 
 */
package br.snt.app.entities;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author mvalencaa
 * @since 07/07/2011
 * 
 */
public class Verse {

	public static final String AUTHORITY = "br.snt.app.providers.VerseProvider";

	private Verse() {
	}

	/**
	 * Verses table contract
	 */
	public static final class VerseContract implements BaseColumns {

		// This class cannot be instantiated
		private VerseContract() {
		}

		/**
		 * The table name offered by this provider
		 */
		public static final String TABLE_NAME = "verses";

		/*
		 * URI definitions
		 */

		/**
		 * The scheme part for this provider's URI
		 */
		private static final String SCHEME = "content://";

		/**
		 * Path parts for the URIs
		 */

		/**
		 * Path part for the Notes URI
		 */
		private static final String PATH_VERSES = "/verses";

		/**
		 * Path part for the Verse ID URI
		 */
		private static final String PATH_VERSE_ID = "/verses/";

		/**
		 * 0-relative position of a note ID segment in the path part of a verse
		 * ID URI
		 */
		public static final int VERSE_ID_PATH_POSITION = 1;

		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY
				+ PATH_VERSES);

		/**
		 * The content URI base for a single verse. Callers must append a
		 * numeric verse id to this Uri to retrieve a note
		 */
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME
				+ AUTHORITY + PATH_VERSE_ID);

		/**
		 * The content URI match pattern for a single note, specified by its ID.
		 * Use this to match incoming URIs or to construct an Intent.
		 */
		public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME
				+ AUTHORITY + PATH_VERSE_ID + "/#");

		/*
		 * MIME type definitions
		 */

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * note.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";

		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "modified DESC";

		/*
		 * Column definitions
		 */

		/**
		 * Column name for the book of the verse.
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String COLUMN_NAME_BOOK_ID = "book_id";

		/**
		 * Column name of the verse chapter.
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String COLUMN_NAME_CHAPTER = "chapter";

		/**
		 * Column name of the verse number.
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String COLUMN_NAME_NUMBER = "number";

		/**
		 * Column name of the verse text.
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String COLUMN_NAME_TEXT = "text";

		/**
		 * Column name of the marked verse.
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String COLUMN_NAME_MARKED = "marked";
	}

}
