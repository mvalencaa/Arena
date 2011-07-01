package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.PartnersOfGodActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the partners of god button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class ClickPartnersOfGodCommand implements Command {

	/**
	 * Activity used to start the {@link PartnersOfGodActivity}.
	 */
	private Activity mActivity;

	/**
	 * Button to be clicked.
	 */
	private Button mPartnersOfGodButton;

	/**
	 * Default constructor.
	 * 
	 * @param activity
	 *            Activity used to start the {@link PartnersOfGodActivity}.
	 * @param button
	 *            Button to be clicked.
	 */
	public ClickPartnersOfGodCommand(Activity activity, Button button) {
		mActivity = activity;
		mPartnersOfGodButton = button;

		setOnClickListener();
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

	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mPartnersOfGodButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
