package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.AnnotationsActivity;
import br.snt.app.activities.MainActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the annotations button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class OpenAnnotationsActivityCmd implements Command {
	
	/**
	 * The parent activity used to start the {@link AnnotationsActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;
	
	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link AnnotationsActivity}.
	 */
	public OpenAnnotationsActivityCmd(MainActivity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, AnnotationsActivity.class);
		mActivity.startActivity(intent);
	}
}
