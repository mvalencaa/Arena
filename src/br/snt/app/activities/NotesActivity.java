package br.snt.app.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import br.snt.app.R;
import br.snt.app.database.DatabaseHelper;
import br.snt.app.database.NotesDbAdapter;

/**
 * Represents a note click on the verse from the context menu.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class NotesActivity extends ListActivity {

	private DatabaseHelper mDbHelper;

	private NotesDbAdapter mNotesDbAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);

		mDbHelper = DatabaseHelper.getInstance(this);
		mNotesDbAdapter = new NotesDbAdapter(mDbHelper.getWritableDatabase());

		showNotes();
	}

	/**
	 * 
	 */
	private void showNotes() {
		String[] from = new String[] { NotesDbAdapter.KEY_TEXT };
		int[] to = new int[] { R.id.note_text };

		Cursor cursor = mNotesDbAdapter.fetchAllNotesByVerseId(getIntent()
				.getLongExtra("verse_id", 0));
		startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.note_item, cursor, from, to);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.notes_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, AddNoteActivity.class);
		intent.putExtra("verse_id", getIntent().getLongExtra("verse_id", 0));
		startActivityForResult(intent, AddNoteActivity.SAVE_BUTTON_PRESSED);

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == AddNoteActivity.SAVE_BUTTON_PRESSED) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(),
						"Nota gravada com sucesso.", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(),
						"Falha ao salvar a nota.", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView,
	 * android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.i("TESTE", "Funfou!");
		super.onListItemClick(l, v, position, id);
	}
}
