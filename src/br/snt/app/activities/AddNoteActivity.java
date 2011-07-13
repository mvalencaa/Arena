package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.snt.app.R;
import br.snt.app.commands.CancelCmd;
import br.snt.app.commands.Command;
import br.snt.app.commands.SaveNoteCmd;
import br.snt.app.database.DatabaseHelper;
import br.snt.app.database.NotesDbAdapter;

/**
 * Represents an add note click on the verse from the context menu.
 * 
 * @author mvalencaa
 * @since 02/07/2011
 * 
 */
public class AddNoteActivity extends Activity {

	private Button mCancel;

	private Button mSave;

	private DatabaseHelper mDbHelper;

	private NotesDbAdapter mNotesDbAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_note);

		mDbHelper = DatabaseHelper.getInstance(this);
		mNotesDbAdapter = new NotesDbAdapter(mDbHelper.getWritableDatabase());

		initButtons();
	}

	/**
	 * Initializes all main screen buttons.
	 */
	private void initButtons() {

		mCancel = (Button) findViewById(R.id.cancel_note_button);
		mCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command cancelCmd = new CancelCmd(AddNoteActivity.this);
				cancelCmd.execute();
			}
		});

		mSave = (Button) findViewById(R.id.save_note_button);
		mSave.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command saveNoteCommand = new SaveNoteCmd(AddNoteActivity.this);
				saveNoteCommand.execute();

				// Como passar o verse_id?
				long wasSaved = mNotesDbAdapter
						.createNote(1, ((EditText) findViewById(R.id.new_note))
								.getText().toString());

				if (wasSaved != -1)
					setResult(RESULT_OK);
				else
					setResult(RESULT_CANCELED);

				finish();
			}
		});
	}

}
