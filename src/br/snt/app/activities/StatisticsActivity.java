package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import br.snt.app.R;

/**
 * Represents the statistics's main screen.
 * 
 * @author mvalencaa
 * @since 29/06/2011
 * 
 */
public class StatisticsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics);
	}
}
