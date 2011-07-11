package br.snt.app.activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;
import br.snt.app.R;
import br.snt.app.adapters.BooksDbAdapter;
import br.snt.app.adapters.DatabaseHelper;
import br.snt.app.adapters.VersesDbAdapter;
import br.snt.app.commands.Command;
import br.snt.app.commands.MarkVerseItemCmd;
import br.snt.app.commands.OpenNoteActivityCmd;

/**
 * Represents the bible's main screen.
 * 
 * @author mvalencaa
 * @since 27/06/2011
 * 
 */
public class BibleActivity extends ListActivity {

	/**
	 * View used to show all books.
	 */
	private Spinner mSpinnerBooks;

	private Spinner mSpinnerChapters;

	private DatabaseHelper mDbHelper;

	private VersesDbAdapter mVersesDbAdapter;

	private BooksDbAdapter mBooksDbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible);

		// Quando chamar o close()?
		mDbHelper = DatabaseHelper.getInstance(this);
		mVersesDbAdapter = new VersesDbAdapter(mDbHelper.getWritableDatabase());
		mBooksDbAdapter = new BooksDbAdapter(mDbHelper.getWritableDatabase());

		showSpinnerBooks();
		showSpinnerChapters();
		showVerses(BooksDbAdapter.GENESIS, 1);
	}

	/**
	 * Initializes a spinner with all books.
	 */
	private void showSpinnerBooks() {
		String[] from = new String[] { BooksDbAdapter.KEY_TITLE };
		int[] to = new int[] { android.R.id.text1 };

		Cursor cursor = mBooksDbAdapter.fetchAllBooks();

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, cursor, from, to);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mSpinnerBooks = (Spinner) findViewById(R.id.spinner_books);
		mSpinnerBooks.setAdapter(adapter);
		mSpinnerBooks.setSelection(BooksDbAdapter.GENESIS);

		mSpinnerBooks.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				showVerses(position, mSpinnerChapters.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	private void showSpinnerChapters() {
		String[] from = new String[] { VersesDbAdapter.KEY_CHAPTER };
		int[] to = new int[] { android.R.id.text1 };

		// Como parametrizar?
		Cursor cursor = mVersesDbAdapter
				.fetchAllChaptersByBook(BooksDbAdapter.GENESIS);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, cursor, from, to);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mSpinnerChapters = (Spinner) findViewById(R.id.spinner_chapters);
		mSpinnerChapters.setAdapter(adapter);
		mSpinnerChapters.setSelection(0);

		mSpinnerChapters
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						showVerses(mSpinnerBooks.getSelectedItemPosition(),
								position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
					}
				});
	}

	/**
	 * Shows all verses in a chapter of a book.
	 */
	private void showVerses(int book_id, int chapter) {
		String[] from = new String[] { VersesDbAdapter.KEY_NUMBER,
				VersesDbAdapter.KEY_TEXT };
		int[] to = new int[] { R.id.verse_number, R.id.verse_text };

		Cursor cursor = mVersesDbAdapter.fetchAllVersesByBookAndChapter(
				book_id, chapter);
		// startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.verse_item, cursor, from, to);
		setListAdapter(adapter);

		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu verseContextMenu,
			View bibleView, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(verseContextMenu, bibleView, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.verse_item_context_menu, verseContextMenu);

		verseContextMenu.setHeaderTitle("Opções");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.verse_item_context_menu_note:

			Command clickNotes = new OpenNoteActivityCmd(this);
			clickNotes.execute();

			return true;

		case R.id.verse_item_context_menu_mark:

			Command clickMarkVerseItem = new MarkVerseItemCmd(info.targetView);
			clickMarkVerseItem.execute();

			mVersesDbAdapter.updateVerse(info.id, 1);

			return true;

		case R.id.verse_item_context_menu_tweet:

			return true;

		default:

			return super.onContextItemSelected(item);

		}
	}
}
