package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.snt.app.R;
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

	/**
	 * Used to identify that the save button in AddNoteActivity has been
	 * clicked.
	 */
	public static final int SAVE_BUTTON_PRESSED = 0;
	
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
				finish();
			}
		});

		mSave = (Button) findViewById(R.id.save_note_button);
		mSave.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				long wasSaved = mNotesDbAdapter.createNote(getIntent()
						.getLongExtra("verse_id", 0),
						((EditText) findViewById(R.id.new_note)).getText()
								.toString());

				if (wasSaved != -1)
					setResult(RESULT_OK);
				else
					setResult(RESULT_CANCELED);

				finish();
			}
		});
	}

}
