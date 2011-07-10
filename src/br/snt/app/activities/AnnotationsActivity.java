package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import br.snt.app.R;

/**
 * Represents the annotations's main screen.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 *
 */
public class AnnotationsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.annotations);
	}
}
