package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.TenthActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the tenth button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 *
 */
public class ClickTenthButtonCommand implements Command {

	/**
	 * Activity used to start the {@link TenthActivity}.
	 */
	private Activity mActivity;
	
	/**
	 * Button to be clicked.
	 */
	private Button mTenthButton;

	/**
	 * Default constructor.
	 * 
	 * @param activity Activity used to start the {@link TenthActivity}.
	 * @param button Button to be clicked.
	 */
	public ClickTenthButtonCommand(Activity activity, Button button) {
		mActivity = activity;
		mTenthButton = button;
		
		setOnClickListener();
	}
	
	/* (non-Javadoc)
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, TenthActivity.class);
		mActivity.startActivity(intent);
	}

	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mTenthButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
