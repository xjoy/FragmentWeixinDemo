package com.mostmood.fragment;

import com.example.tabhostweixintest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Home_Fragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	private EditText editText1 = null;
	private LinearLayout mMoreLayout = null;
	private ImageView mMoreImage = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.moodhome, container, false);
		editText1 = (EditText) mView.findViewById(R.id.editText1);
		mMoreLayout = (LinearLayout) mView.findViewById(R.id.layout_more);
		mMoreImage = (ImageView) mView.findViewById(R.id.iamgeview_showmore);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mMoreLayout.setVisibility(View.GONE);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMoreImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mMoreLayout.isShown()) {
					mMoreLayout.setVisibility(View.GONE);
				} else {
					mMoreLayout.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	public void onStart() {
		Log.e("Home", "onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e("Home", "onResume");
		editText1.clearFocus();
		editText1.setFocusableInTouchMode(true);
		super.onResume();
	}

	@Override
	public void onStop() {
		Log.e("Home", "onStop");
		super.onStop();
	}

	@Override
	public void onPause() {
		Log.e("Home", "onPause");
		super.onPause();
	}

}
