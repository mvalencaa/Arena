package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.AboutActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the about button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 *
 */
public class ClickAboutButtonCommand implements Command {
	
	/**
	 * Activity used to start the {@link AboutActivity}.
	 */
	private Activity mActivity;
	
	/**
	 * Button to be clicked.
	 */
	private Button mAboutButton;

	/**
	 * Default constructor.
	 * 
	 * @param activity Activity used to start the {@link AboutActivity}.
	 * @param button Button to be clicked.
	 */
	public ClickAboutButtonCommand(Activity activity, Button button) {
		mActivity = activity;
		mAboutButton = button;
		
		setOnClickListener();
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, AboutActivity.class);
		mActivity.startActivity(intent);
	}
	
	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mAboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
