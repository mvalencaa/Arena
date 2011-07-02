package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.StatisticsActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the statistics button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 *
 */
public class OpenStatisticsActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link StatisticsActivity} calling
	 * startActivity() method.
	 */
	private Activity mActivity;
	
	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link StatisticsActivity}.
	 */
	public OpenStatisticsActivityCmd(Activity activity) {
		mActivity = activity;
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, StatisticsActivity.class);
		mActivity.startActivity(intent);	
	}
}
