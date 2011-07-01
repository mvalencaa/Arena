package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;

/**
 * Represents the abouts's main screen.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 *
 */
public class AboutActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}
}
