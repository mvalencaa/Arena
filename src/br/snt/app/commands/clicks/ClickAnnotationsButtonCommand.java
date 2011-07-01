package br.snt.app.commands.clicks;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.activities.AnnotationsActivity;
import br.snt.app.activities.MainActivity;
import br.snt.app.commands.Command;

/**
 * Represents a click on the annotations button.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class ClickAnnotationsButtonCommand implements Command {
	
	/**
	 * Activity used to start the {@link AnnotationsActivity}.
	 */
	private Activity mActivity;
	
	/**
	 * Button to be clicked.
	 */
	private Button mAnnotationsButton;

	/**
	 * @param mainActivity
	 * @param findViewById
	 */
	public ClickAnnotationsButtonCommand(MainActivity activity, Button button) {
		mActivity = activity;
		mAnnotationsButton = button;
		
		setOnClickListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Intent intent = new Intent(mActivity, AnnotationsActivity.class);
		mActivity.startActivity(intent);
	}

	/**
	 * Sets the OnClickListener event for this button.
	 */
	private void setOnClickListener() {
		mAnnotationsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});
	}
}
