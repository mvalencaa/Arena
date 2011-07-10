package br.snt.app.commands;

import android.app.Activity;
import android.util.Log;

/**
 * Represents a save note operation.
 * 
 * @author mvalencaa
 * @since 02/07/2011
 *
 */
public class SaveNoteCmd implements Command {
	
	private Activity mActivity;
	
	public SaveNoteCmd(Activity activity) {
		mActivity = activity;
	}

	/* (non-Javadoc)
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Log.i("SaveNoteCmd", "Em teoria a nota foi salva.");
	}

}
