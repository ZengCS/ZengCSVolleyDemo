package com.zcs.demo.volley.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {
	// �������������Ŀ����һ��ͼƬ����,��ô������Խ�mCache���óɾ�̬
	private LruCache<String, Bitmap> mCache;

	public BitmapCache() {
		if (mCache == null) {
			// ����10M�Ļ���ռ�
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes() * value.getHeight();
				}
			};
		}
	}

	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
		Log.d(getClass().getSimpleName(), "cacheSize/maxSize:" + mCache.size() + "/" + mCache.maxSize());
	}
}