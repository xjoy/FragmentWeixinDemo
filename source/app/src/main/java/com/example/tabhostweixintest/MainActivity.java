package com.example.tabhostweixintest;

import com.mostmood.fragment.Home_Fragment;
import com.mostmood.fragment.Me_Fragment;
import com.mostmood.fragment.Message_Fragment;
import com.mostmood.fragment.Wall_Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

	LinearLayout bottom_layout;
	Home_Fragment homeFragment;
	Wall_Fragment wallFragment;
	Message_Fragment messageFragment;
	Me_Fragment meFragment;
	android.support.v4.app.FragmentTransaction ft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		setContentView(R.layout.activity_main);
		findTabView();
		ft = getSupportFragmentManager().beginTransaction();
		isTabHome();
		ft.commit();
	}

	// 判断当前
	public void isTabHome() {

		if (homeFragment == null) {
			ft.add(R.id.realtabcontent, new Home_Fragment(), "home");
		} else {
			ft.attach(homeFragment);
		}
	}

	public void isTabWall() {

		if (wallFragment == null) {
			ft.add(R.id.realtabcontent, new Wall_Fragment(), "wall");
		} else {
			ft.attach(wallFragment);
		}
	}

	public void isTabMessage() {

		if (messageFragment == null) {
			ft.add(R.id.realtabcontent, new Message_Fragment(), "message");
		} else {
			ft.attach(messageFragment);
		}
	}

	public void isTabMe() {
		if (meFragment == null) {
			ft.add(R.id.realtabcontent, new Me_Fragment(), "me");
		} else {
			ft.attach(meFragment);
			// ft.replace(arg0, arg1)
		}
	}

	private ImageView mTabImg;// ����ͼƬ
	private ImageView mTab1, mTab2, mTab3, mTab4;
	private int zero = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int one;// ����ˮƽ����λ��
	private int two;
	private int three;

	private LinearLayout lay1, lay2, lay3, lay4;

	/**
	 * ͷ��������
	 */
	private View.OnClickListener MyOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			Log.e("MainActivity", "v.gettag=" + v.getId());
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			homeFragment = (Home_Fragment) fm.findFragmentByTag("home");
			wallFragment = (Wall_Fragment) fm.findFragmentByTag("wall");
			messageFragment = (Message_Fragment) fm
					.findFragmentByTag("message");
			meFragment = (Me_Fragment) fm.findFragmentByTag("me");
			ft = fm.beginTransaction();
			
			if (homeFragment != null)
				ft.detach(homeFragment);
			if (wallFragment != null)
				ft.detach(wallFragment);
			if (messageFragment != null)
				ft.detach(messageFragment);
			if (meFragment != null)
				ft.detach(meFragment);

			Animation animation = null;
			switch (v.getId()) {
			case R.id.lay_weixin:
				mTab1.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				currIndex = 0;
				isTabHome();
				break;
			case R.id.lay_address:
				mTab2.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				currIndex = 1;
				isTabWall();
				break;
			case R.id.lay_friends:
				mTab3.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				currIndex = 2;
				isTabMessage();
				break;
			case R.id.lay_setting:
				mTab4.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				}
				currIndex = 3;
				isTabMe();
				break;
			}
			ft.commit();
			if (animation != null) {
				animation.setFillAfter(true);
				animation.setDuration(150);
				mTabImg.startAnimation(animation);
			} else {
				Log.e("MainActivity", "animation=null");
			}
		}
	};

	/**
	 * 找到Tabhost布局
	 */
	public void findTabView() {

		mTab1 = (ImageView) findViewById(R.id.img_weixin);
		mTab2 = (ImageView) findViewById(R.id.img_address);
		mTab3 = (ImageView) findViewById(R.id.img_friends);
		mTab4 = (ImageView) findViewById(R.id.img_settings);
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);

		lay1 = (LinearLayout) findViewById(R.id.lay_weixin);
		lay2 = (LinearLayout) findViewById(R.id.lay_address);
		lay3 = (LinearLayout) findViewById(R.id.lay_friends);
		lay4 = (LinearLayout) findViewById(R.id.lay_setting);

		lay1.setTag(0);
		lay2.setTag(1);
		lay3.setTag(2);
		lay4.setTag(3);

		lay1.setOnClickListener(MyOnClickListener);
		lay2.setOnClickListener(MyOnClickListener);
		lay3.setOnClickListener(MyOnClickListener);
		lay4.setOnClickListener(MyOnClickListener);

		Display currDisplay = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int displayWidth = currDisplay.getWidth();
		one = displayWidth / 4;
		two = one * 2;
		three = one * 3;

	}
}