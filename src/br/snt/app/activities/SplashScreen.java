/**
 * 
 */
package br.snt.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.snt.app.R;

/**
 * @author mvalencaa
 * @since 14/07/2011
 *
 */
public class SplashScreen extends Activity implements Runnable {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		Handler handler = new Handler();
		handler.postDelayed(this, 3000);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

}
