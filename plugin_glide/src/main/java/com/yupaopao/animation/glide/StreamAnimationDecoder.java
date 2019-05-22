package com.yupaopao.animation.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.yupaopao.animation.FrameAnimationDrawable;
import com.yupaopao.animation.apng.APNGDrawable;
import com.yupaopao.animation.apng.decode.APNGParser;
import com.yupaopao.animation.gif.GifDrawable;
import com.yupaopao.animation.gif.decode.GifParser;
import com.yupaopao.animation.io.ByteBufferReader;
import com.yupaopao.animation.io.StreamReader;
import com.yupaopao.animation.loader.Loader;
import com.yupaopao.animation.loader.StreamLoader;
import com.yupaopao.animation.webp.WebPDrawable;
import com.yupaopao.animation.webp.decode.WebPParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @Description: StreamAnimationDecoder
 * @Author: pengfei.zhou
 * @CreateDate: 2019-05-14
 */
public class StreamAnimationDecoder implements ResourceDecoder<InputStream, Drawable> {

    private final ResourceDecoder<ByteBuffer, Drawable> byteBufferDecoder;

    public StreamAnimationDecoder(ResourceDecoder<ByteBuffer, Drawable> byteBufferDecoder) {
        this.byteBufferDecoder = byteBufferDecoder;
    }

    @Override
    public boolean handles(@NonNull InputStream source, @NonNull Options options) {
        return !options.get(GifOptions.DISABLE_ANIMATION)
                && (WebPParser.isAWebP(new StreamReader(source))
                || APNGParser.isAPNG(new StreamReader(source))
                || GifParser.isGif(new StreamReader(source)));
    }

    @Nullable
    @Override
    public Resource<Drawable> decode(@NonNull final InputStream source, int width, int height, @NonNull Options options) throws IOException {
        byte[] data = inputStreamToBytes(source);
        if (data == null) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        return byteBufferDecoder.decode(byteBuffer, width, height, options);
    }


    private static byte[] inputStreamToBytes(InputStream is) {
        final int bufferSize = 16384;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(bufferSize);
        try {
            int nRead;
            byte[] data = new byte[bufferSize];
            while ((nRead = is.read(data)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch (IOException e) {
            return null;
        }
        return buffer.toByteArray();
    }
}
