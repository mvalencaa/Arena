package br.snt.app.commands;

import android.app.Activity;

/**
 * Represents a cancel operation.
 * 
 * @author mvalencaa
 * @since 02/07/2011
 * 
 */
public class CancelCmd implements Command {

	/**
	 * The parent activity used to finalize itself calling its finish() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to finalize itself.
	 */
	public CancelCmd(Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		mActivity.finish();
	}

}
