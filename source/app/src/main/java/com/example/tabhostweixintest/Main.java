package com.example.tabhostweixintest;

import com.mostmood.util.DrawImageView;
import com.mostmood.util.RotateUpdatedViewEx;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	private DrawImageView mDrawImageView=null;
	private RotateUpdatedViewEx mEx=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop);
		mDrawImageView=(DrawImageView) findViewById(R.id.drawImageView1);
		mEx=(RotateUpdatedViewEx) findViewById(R.id.rotateUpdatedViewEx1);
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mDrawImageView.Start();
						mEx.Start();
					}
				});
		((Button) findViewById(R.id.button2))
		.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawImageView.Stop();
				mEx.Stop();
			}
		});
		((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mEx.setImageDrawable(getResources().getDrawable(R.drawable.xiaohei));
			}
		});
	}
}
