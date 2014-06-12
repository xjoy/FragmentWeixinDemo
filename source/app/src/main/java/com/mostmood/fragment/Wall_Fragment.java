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

public class Wall_Fragment extends Fragment {
	private EditText editText1 = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.wall, container, false);
		editText1 = (EditText) mView.findViewById(R.id.editText1);
		return mView;
	}

	@Override
	public void onResume() {
		Log.e("Wall", "onResume");
		editText1.clearFocus();
		editText1.setFocusableInTouchMode(true);
		super.onResume();
	}

}
