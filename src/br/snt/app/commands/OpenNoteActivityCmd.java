package br.snt.app.commands;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.NotesActivity;

/**
 * Represents a click on the about button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 *
 */
public class OpenNoteActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link NotesActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;
	
	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link NotesActivity}.
	 */
	public OpenNoteActivityCmd(Activity activity) {
		mActivity = activity;
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, NotesActivity.class);
		mActivity.startActivity(intent);
	}
}
