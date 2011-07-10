package br.snt.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.snt.app.R;
import br.snt.app.commands.AddNoteCmd;
import br.snt.app.commands.Command;

/**
 * Represents a note click on the verse from the context menu.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class NotesActivity extends Activity {

	/**
	 * Used to identify that the save button in AddNoteActivity has been
	 * clicked.
	 */
	public static final int SAVE_BUTTON_PRESSED = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.notes_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Command addNoteCmd = new AddNoteCmd(this);
		addNoteCmd.execute();

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SAVE_BUTTON_PRESSED) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(),
						"Nota gravada com sucesso.", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(),
						"Falha ao salvar a nota.", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
