package com.mostmood.util;

import org.apache.http.impl.client.TunnelRefusedException;

import com.example.tabhostweixintest.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class RotateUpdatedViewEx extends ImageView {

	private final Paint mpaint;

	private int mBorderThickness = 2;
	/**
	 * 内圆或者外圆的边框颜色
	 */
	private int mBorderColor = android.graphics.Color.YELLOW;
	/**
	 * 滑块颜色
	 */
	private int mSlidercolor = android.graphics.Color.CYAN;

	public RotateUpdatedViewEx(Context mContext, AttributeSet attrs) {
		super(mContext, attrs);
		this.mpaint = new Paint();
		this.mpaint.setAntiAlias(true);
		this.mpaint.setStyle(Style.STROKE);
		if (isInEditMode()) {
			isDraw = true;
			mBorderThickness = 4;
		} else {
			mBorderThickness = mContext.getResources().getDimensionPixelSize(
					R.dimen.rotatethiness);
		}
	}

	int startAngle = 0;

	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap scaledSrcBmp;
		int diameter = radius * 2;
		if (bmp.getWidth() != diameter || bmp.getHeight() != diameter)
			scaledSrcBmp = Bitmap.createScaledBitmap(bmp, diameter, diameter,
					false);
		else
			scaledSrcBmp = bmp;
		Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
				scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
		return output;
	}

	private boolean isDraw = false;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (drawable == null) {
			return;
		}
		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		this.measure(0, 0);
		if (drawable.getClass() == NinePatchDrawable.class)
			return;
		Bitmap b = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
		int w = getWidth(), h = getHeight();
		int radius = (w < h ? w : h) / 2 - mBorderThickness-1;
		Bitmap roundBitmap = getCroppedBitmap(bitmap, radius);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		
		
		
		paint.setColor(android.graphics.Color.GREEN);
		canvas.drawCircle(w / 2, h / 2, radius + mBorderThickness+1, paint);
		paint.setColor(mBorderColor);
		// 外围边框
		canvas.drawCircle(w / 2, h / 2, radius + mBorderThickness, paint);
		canvas.drawBitmap(roundBitmap, w / 2 - radius, h / 2 - radius, null);
		
		if(isInEditMode()){
			mpaint.setColor(mSlidercolor);
			mpaint.setStrokeWidth(mBorderThickness);
			mpaint.setAntiAlias(true);
			mpaint.setDither(true);
			RectF rect2 = new RectF(w/2-radius-2,h/2-radius-2, getWidth() - mBorderThickness,getHeight() - mBorderThickness);
			//RectF rect2=new RectF(2,2,(radius + mBorderThickness)*2-2,(radius + mBorderThickness)*2-2);
			
			
			canvas.drawArc(rect2,270, 30, false, mpaint);
			canvas.drawArc(rect2,90, 30, false, mpaint);
			canvas.drawArc(rect2,0, 30, false, mpaint);
			canvas.drawArc(rect2,180, 30, false, mpaint);
			canvas.drawArc(rect2,45, 30, false, mpaint);
			canvas.drawArc(rect2,135, 30, false, mpaint);
			canvas.drawArc(rect2,225, 30, false, mpaint);
			canvas.drawArc(rect2,315, 30, false, mpaint);
		}
		
		if (!isDraw) {
			mpaint.setColor(mSlidercolor);
			mpaint.setStrokeWidth(mBorderThickness);
			mpaint.setAntiAlias(true);
			mpaint.setDither(true);
			RectF rect2 = new RectF(w/2-radius-2,h/2-radius-2, getWidth() - mBorderThickness,getHeight() - mBorderThickness);
			//RectF rect2=new RectF(2,2,(radius + mBorderThickness)*2-2,(radius + mBorderThickness)*2-2);
			canvas.drawArc(rect2, startAngle, 20, false, mpaint);
			startAngle += 5;
			if (startAngle == 360)
				startAngle = 0;
			if (isStart) {
				// super.onDraw(canvas);
				isDraw = false;
				invalidate();
			}
		}
	}
	

	private boolean isStart = true;

	Object mObject = new Object();

	public void Start() {
		synchronized (mObject) {
			isStart = true;
			isDraw = false;
			invalidate();
		}
	}

	public void Stop() {
		synchronized (mObject) {
			isStart = false;
			isDraw = true;
			invalidate();
		}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}