package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import br.snt.app.commands.clicks.ClickAboutButtonCommand;
import br.snt.app.commands.clicks.ClickAnnotationsButtonCommand;
import br.snt.app.commands.clicks.ClickBibleButtonCommand;
import br.snt.app.commands.clicks.ClickPartnersOfGodCommand;
import br.snt.app.commands.clicks.ClickStatisticsButtonCommand;
import br.snt.app.commands.clicks.ClickTenthButtonCommand;

/**
 * Represents the arena's main screen.
 * 
 * @author mvalencaa
 * @since 26/06/2011
 * 
 */
public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initButtons();
	}

	/**
	 * Initializes all main screen buttons.
	 */
	private void initButtons() {
		new ClickBibleButtonCommand(this,
				(Button) findViewById(R.id.bible_button));

		new ClickAnnotationsButtonCommand(this,
				(Button) findViewById(R.id.annotations_button));

		new ClickTenthButtonCommand(this,
				(Button) findViewById(R.id.tenth_button));

		new ClickPartnersOfGodCommand(this,
				(Button) findViewById(R.id.partners_of_god_button));

		new ClickStatisticsButtonCommand(this,
				(Button) findViewById(R.id.statistic_button));

		new ClickAboutButtonCommand(this,
				(Button) findViewById(R.id.about_button));
	}
}