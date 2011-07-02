package br.snt.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.commands.Command;
import br.snt.app.commands.clicks.OpenAboutActivityCmd;
import br.snt.app.commands.clicks.OpenAnnotationsActivityCmd;
import br.snt.app.commands.clicks.OpenBibleActivityCmd;
import br.snt.app.commands.clicks.OpenPartnersOfGodActivityCmd;
import br.snt.app.commands.clicks.OpenStatisticsActivityCmd;
import br.snt.app.commands.clicks.OpenTenthActivityCmd;

/**
 * Represents the arena's main screen.
 * 
 * @author mvalencaa
 * @since 26/06/2011
 * 
 */
public class MainActivity extends Activity {

	/**
	 * Button to be clicked to start {@link AboutActivity}.
	 */
	private Button mAboutButton;

	/**
	 * Button to be clicked to start {@link AnnotationsActivity}.
	 */
	private Button mAnnotationsButton;

	/**
	 * Button to be clicked to start {@link BibleActivity}.
	 */
	private Button mBibleButton;

	/**
	 * Button to be clicked to start {@link TenthActivity}.
	 */
	private Button mTenthButton;

	/**
	 * Button to be clicked to start {@link PartnersOfGodActivity}.
	 */
	private Button mPartnersOfGodButton;

	/**
	 * Button to be clicked to start {@link StatisticsActivity}.
	 */
	private Button mStatisticsButton;

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

		mBibleButton = (Button) findViewById(R.id.bible_button);
		mBibleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command bibleClickCommand = new OpenBibleActivityCmd(
						MainActivity.this);
				bibleClickCommand.execute();
			}
		});

		mAnnotationsButton = (Button) findViewById(R.id.annotations_button);
		mAnnotationsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command annotationsClickCommand = new OpenAnnotationsActivityCmd(
						MainActivity.this);
				annotationsClickCommand.execute();
			}
		});

		mTenthButton = (Button) findViewById(R.id.tenth_button);
		mTenthButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command tenthClickCommand = new OpenTenthActivityCmd(
						MainActivity.this);
				tenthClickCommand.execute();
			}
		});

		mPartnersOfGodButton = (Button) findViewById(R.id.partners_of_god_button);
		mPartnersOfGodButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command partnersOfGodClickCommand = new OpenPartnersOfGodActivityCmd(
						MainActivity.this);
				partnersOfGodClickCommand.execute();
			}
		});

		mStatisticsButton = (Button) findViewById(R.id.statistic_button);
		mStatisticsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command statisticsClickCommand = new OpenStatisticsActivityCmd(
						MainActivity.this);
				statisticsClickCommand.execute();
			}
		});

		mAboutButton = (Button) findViewById(R.id.about_button);
		mAboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Command aboutClickCommand = new OpenAboutActivityCmd(
						MainActivity.this);
				aboutClickCommand.execute();
			}
		});
	}
}