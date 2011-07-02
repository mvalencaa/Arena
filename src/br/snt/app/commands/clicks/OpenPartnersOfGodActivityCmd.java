package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import br.snt.app.activities.PartnersOfGodActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the partners of god button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class OpenPartnersOfGodActivityCmd implements Command {

	/**
	 * The parent activity used to start the {@link PartnersOfGodActivity}
	 * calling startActivity() method.
	 */
	private Activity mActivity;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link PartnersOfGodActivity}.
	 */
	public OpenPartnersOfGodActivityCmd(Activity activity) {
		mActivity = activity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, PartnersOfGodActivity.class);
		mActivity.startActivity(intent);
	}
}
