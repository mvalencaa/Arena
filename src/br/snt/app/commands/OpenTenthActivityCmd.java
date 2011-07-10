package br.snt.app.commands;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.TenthActivity;

/**
 * Represents a click on the tenth button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class OpenTenthActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link TenthActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link TenthActivity}.
	 */
	public OpenTenthActivityCmd(Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, TenthActivity.class);
		mActivity.startActivity(intent);
	}
}
