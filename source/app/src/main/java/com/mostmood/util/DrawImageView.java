package com.mostmood.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.graphics.Paint.Style;

public class DrawImageView extends ImageView {

	private final Paint paint;
	private final Context context;

	public DrawImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		this.paint.setStyle(Style.STROKE);
	}

	int startAngle = 0;

	@Override
	protected void onDraw(Canvas canvas) {
		int center = getMeasuredWidth() / 2;

		int innerCircle = dip2px(context, 50); // 设置内圆半径
		int ringWidth = dip2px(context, 5); // 设置圆环宽度

		// 绘制内圆
		// this.paint.setARGB(155, 167, 190, 206);
		this.paint.setColor(android.graphics.Color.CYAN);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center, center, innerCircle, this.paint);

		// 绘制圆环
		// this.paint.setARGB(255, 212, 225, 233);
		this.paint.setColor(android.graphics.Color.YELLOW);
		this.paint.setStrokeWidth(ringWidth);
		canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2,
				this.paint);

		// 绘制外圆
		// this.paint.setARGB(155, 167, 190, 206);
		this.paint.setColor(android.graphics.Color.CYAN);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);

		RectF rect2 = new RectF(center - (innerCircle + 1 + ringWidth / 2),
				center - (innerCircle + 1 + ringWidth / 2), center
						+ (innerCircle + 1 + ringWidth / 2), center
						+ (innerCircle + 1 + ringWidth / 2));

		// this.paint.setARGB(30, 127, 255, 212);
		this.paint.setColor(android.graphics.Color.GRAY);
		this.paint.setStrokeWidth(ringWidth);
		// 绘制不透明部分
		// canvas.drawArc(rect2, 180 + startAngle, 30, false, paint);
		canvas.drawArc(rect2, 0 + startAngle,10, false, paint);
		// 绘制透明部分
		// this.paint.setARGB(30, 127, 255, 212);
		// this.paint.setColor(android.graphics.Color.GRAY);
		// canvas.drawArc(rect2, 90 + startAngle, 30, false, paint);
		// canvas.drawArc(rect2, 270 + startAngle, 30, false, paint);

		startAngle += 5;
		if (startAngle == 360)
			startAngle = 0;
		super.onDraw(canvas);
		if (isStart) {
			invalidate();
		}
	}

	private boolean isStart = true;
	Object mObject = new Object();

	public void Start() {
		synchronized (mObject) {
			isStart = true;
			invalidate();
		}
	}

	public void Stop() {
		synchronized (mObject) {
			isStart = false;
			invalidate();
		}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}