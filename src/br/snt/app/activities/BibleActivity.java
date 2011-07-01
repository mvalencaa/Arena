package br.snt.app.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * Represents the bible's main screen.
 * 
 * @author mvalencaa
 * @since 27/06/2011
 * 
 */
public class BibleActivity extends Activity {

	/**
	 * View used to show all verses of the book and chapter choosed.
	 */
	private ListView mVerses;

	/**
	 * View used to show all books.
	 */
	private Spinner mSpinnerBooks;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible);

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
	// TODO Passar um capítulo como parâmetro para retornar apenas os versículos
	// do capítulo passado.
	private void showVerses() {
		String[] from = new String[] { "verseId", "verseText" };
		int[] to = new int[] { R.id.verse_id, R.id.verse_text };

		// prepare the list of all records
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("verseId", "" + i);
			map.put("verseText",
					"E haja luz E haja luz E haja luz E haja luz E haja luz E haja luz E haja luz "
							+ i);
			fillMaps.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, fillMaps,
				R.layout.verse_item, from, to);

		mVerses = (ListView) findViewById(R.id.listView_verses);
		mVerses.setTextFilterEnabled(true);
		mVerses.setAdapter(simpleAdapter);

		registerForContextMenu(mVerses);
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
		Log.i("\n\n\nTESTE: ", "" + item.getTitle());
		switch (item.getItemId()) {
		case R.id.verse_item_context_menu_note:
			// editNote(info.id);
			return true;
		case R.id.verse_item_context_menu_mark:
			// deleteNote(info.id);
			return true;
		case R.id.verse_item_context_menu_tweet:
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
