package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;

/**
 * Represents a note click on the verse from the context menu.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 *
 */
public class NotesActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);
	}
}
