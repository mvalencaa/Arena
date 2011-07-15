package br.snt.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.snt.app.R;

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
				Intent intent = new Intent(MainActivity.this, BibleActivity.class);
				startActivity(intent);
			}
		});

		mAnnotationsButton = (Button) findViewById(R.id.annotations_button);
		mAnnotationsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AnnotationsActivity.class);
				startActivity(intent);
			}
		});

		mTenthButton = (Button) findViewById(R.id.tenth_button);
		mTenthButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TenthActivity.class);
				startActivity(intent);
			}
		});

		mPartnersOfGodButton = (Button) findViewById(R.id.partners_of_god_button);
		mPartnersOfGodButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PartnersOfGodActivity.class);
				startActivity(intent);
			}
		});

		mStatisticsButton = (Button) findViewById(R.id.statistic_button);
		mStatisticsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
				startActivity(intent);
			}
		});

		mAboutButton = (Button) findViewById(R.id.about_button);
		mAboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
			}
		});
	}
}