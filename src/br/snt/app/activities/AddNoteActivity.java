package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.R;
import br.snt.app.commands.CancelCmd;
import br.snt.app.commands.Command;
import br.snt.app.commands.SaveNoteCmd;

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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_note);

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

				// Testar se a nota realmente foi salva!
				if(true) // A nota foi salva com sucesso...
					setResult(RESULT_OK);
				else // A nota não foi salva com sucesso...
					setResult(RESULT_CANCELED);

				finish();
			}
		});
	}

}
