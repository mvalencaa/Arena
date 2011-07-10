package br.snt.app.activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.AdapterContextMenuInfo;
import br.snt.app.R;
import br.snt.app.adapters.DatabaseHelper;
import br.snt.app.adapters.VersesDbAdapter;
import br.snt.app.commands.Command;
import br.snt.app.commands.MarkVerseItemCmd;
import br.snt.app.commands.OpenNoteActivityCmd;
import br.snt.app.entities.Verse;

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

	private DatabaseHelper dbHelper;
	
	private VersesDbAdapter versesDbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible);

		//Quando chamar o close()?
		dbHelper = DatabaseHelper.getInstance(this);
		versesDbAdapter = new VersesDbAdapter(dbHelper.getWritableDatabase());

		showSpinnerBooks();
		showVerses();
	}

	/**
	 * Initializes a spinner with all books.
	 */
	private void showSpinnerBooks() {
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter
				.createFromResource(this, R.array.books_array,
						android.R.layout.simple_spinner_item);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mSpinnerBooks = (Spinner) findViewById(R.id.spinner_books);
		mSpinnerBooks.setAdapter(arrayAdapter);
	}

	/**
	 * Shows all verses in a chapter.
	 */
	private void showVerses() {
		String[] from = new String[] { Verse.VerseContract.COLUMN_NAME_NUMBER,
				Verse.VerseContract.COLUMN_NAME_TEXT };
		int[] to = new int[] { R.id.verse_number, R.id.verse_text };

		Cursor cursor = versesDbAdapter.fetchAllVerses();
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
		// verseContextMenu.setHeaderTitle("Capítulo 1:2");
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
			return true;
		case R.id.verse_item_context_menu_tweet:
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
