package br.snt.app.commands;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.AboutActivity;

/**
 * Represents a click on the about button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 * 
 */
public class OpenAboutActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link AboutActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link AboutActivity}.
	 */
	public OpenAboutActivityCmd(Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, AboutActivity.class);
		mActivity.startActivity(intent);
	}
}
