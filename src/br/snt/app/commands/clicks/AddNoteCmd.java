package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.AddNoteActivity;
import br.snt.app.activities.NotesActivity;
import br.snt.app.commands.Command;

/**
 * Represents an add click on the verse from the add note menu.
 * 
 * @author mvalencaa
 * @since 02/07/2011
 * 
 */
public class AddNoteCmd implements Command {

	/**
	 * The parent activity used to start the {@link AddNoteActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link AddNoteActivity}.
	 */
	public AddNoteCmd(Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, AddNoteActivity.class);
		mActivity.startActivityForResult(intent,
				NotesActivity.SAVE_BUTTON_PRESSED);
	}
}
