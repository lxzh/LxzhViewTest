package com.lxzh123.demo.progressanim;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorRoundProgressActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_round_progress_container);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private ColorRoundProgressBar circleProgressBar;
		private int progress = 0;

		public PlaceholderFragment() {
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.layout_fragment_round_progress,
					container, false);
			return rootView;
		}

		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			circleProgressBar = (ColorRoundProgressBar) getView().findViewById(
					R.id.circleProgressBar);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (progress <= 90) {
						progress += 5;
						circleProgressBar.setProgress(progress);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
}
