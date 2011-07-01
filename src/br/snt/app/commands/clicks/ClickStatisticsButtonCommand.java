package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.StatisticsActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the statistics button.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 *
 */
public class ClickStatisticsButtonCommand implements Command {

	/**
	 * Activity used to start the {@link StatisticsActivity}.
	 */
	private Activity mActivity;
	
	/**
	 * Button to be clicked.
	 */
	private Button mStatisticsButton;

	/**
	 * Default constructor.
	 * 
	 * @param activity Activity used to start the {@link StatisticsActivity}.
	 * @param button Button to be clicked.
	 */
	public ClickStatisticsButtonCommand(Activity activity, Button button) {
		mActivity = activity;
		mStatisticsButton = button;
		
		setOnClickListener();
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.command.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, StatisticsActivity.class);
		mActivity.startActivity(intent);	
	}
	
	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mStatisticsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
