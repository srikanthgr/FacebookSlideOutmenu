/* Created by Srikanth gr.
 */

package com.example.facebook.slideoutmenu;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	// Declare
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;
	private RelativeLayout headerPanel;
	private int panelWidth;
	private int panelWidth1;

	private ImageView menuViewButton,menuRightButton;

	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layer_stack);

		// Initialize
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		panelWidth = (int) ((metrics.widthPixels) * -0.75);
		panelWidth1 = (int) ((metrics.widthPixels) * 0.75);

		headerPanel = (RelativeLayout) findViewById(R.id.header);
		headerPanelParameters = (LinearLayout.LayoutParams) headerPanel
				.getLayoutParams();
		headerPanelParameters.width = metrics.widthPixels;
		headerPanel.setLayoutParams(headerPanelParameters);

		slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel
				.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);

		// Slide the Panel
		menuRightButton = (ImageView) findViewById(R.id.menuViewButton);
		menuRightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isExpanded) {
					isExpanded = true;
					// Expand

					FragmentManager fragmentManager = getFragmentManager();

					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.menuPanel,
							new LeftMenuFragment());
					fragmentTransaction.commit();
					new ExpandAnimation(slidingPanel, panelWidth1,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);

				} else {
					isExpanded = false;
					// Collapse
					
					new CollapseAnimation(slidingPanel, panelWidth1,
							TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
							TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f,
							0, 0.0f);

				}
			}
		});

		menuViewButton = (ImageView) findViewById(R.id.menuRightButton);
		menuViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!isExpanded) {
					isExpanded = true;

					// Expand

					FragmentManager fragmentManager = getFragmentManager();

					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.menuPanel,
							new RightMenuFragment());
					fragmentTransaction.commit();
					new ExpandAnimation(slidingPanel, panelWidth,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, -0.75f, 0, 0.0f, 0,
							0.0f);

				} else {
					isExpanded = false;

					// Collapse
					new CollapseAnimation(slidingPanel, panelWidth,
							TranslateAnimation.RELATIVE_TO_SELF, -0.75f,
							TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f,
							0, 0.0f);

				}
			}
		});

	}
}
