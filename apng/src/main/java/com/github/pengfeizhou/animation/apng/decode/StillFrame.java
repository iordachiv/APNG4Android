package com.github.pengfeizhou.animation.apng.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.github.pengfeizhou.animation.apng.io.APNGReader;
import com.github.pengfeizhou.animation.apng.io.APNGWriter;
import com.github.pengfeizhou.animation.decode.Frame;

import java.io.IOException;

/**
 * @Description: APNG4Android
 * @Author: pengfei.zhou
 * @CreateDate: 2019-05-13
 */
public class StillFrame extends Frame<APNGReader, APNGWriter> {

    public StillFrame(APNGReader reader) {
        super(reader);
    }

    @Override
    public Bitmap draw(Canvas canvas, Paint paint, int sampleSize, Bitmap reusedBitmap, APNGWriter writer) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        options.inMutable = true;
        options.inBitmap = reusedBitmap;
        Bitmap bitmap = null;
        try {
            reader.reset();
            bitmap = BitmapFactory.decodeStream(reader.toInputStream(), null, options);
            assert bitmap != null;
            paint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
