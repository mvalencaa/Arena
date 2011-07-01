package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.AboutActivity;
import br.snt.app.activities.BibleActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the bible button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 *
 */
public class ClickBibleButtonCommand implements Command {
	
	/**
	 * Activity used to start the {@link AboutActivity}.
	 */
	private Activity mActivity;
	
	/**
	 * Button to be clicked.
	 */
	private Button mBibleButton;
	
	/**
	 * Default constructor.
	 * 
	 * @param activity Activity used to start the {@link BibleActivity}.
	 * @param button Button to be clicked.
	 */
	public ClickBibleButtonCommand(final Activity activity, Button button) {
		mActivity = activity;
		mBibleButton = button;
		
		setOnClickListener();
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.main.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, BibleActivity.class);
		mActivity.startActivity(intent);
	}
	
	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mBibleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
