package br.snt.app.commands;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.BibleActivity;

/**
 * Represents a click on the bible button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 * 
 */
public class OpenBibleActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link BibleActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link BibleActivity}.
	 */
	public OpenBibleActivityCmd(final Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.main.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, BibleActivity.class);
		mActivity.startActivity(intent);
	}
}
