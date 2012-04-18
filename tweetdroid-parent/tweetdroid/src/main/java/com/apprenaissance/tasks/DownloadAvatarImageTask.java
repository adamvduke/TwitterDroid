package com.apprenaissance.tasks;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadAvatarImageTask extends AsyncTask<ImageView, Void, Bitmap> {

	private ImageView imageView;
	
	@Override
	protected Bitmap doInBackground(ImageView... imageViews) {
		this.imageView = imageViews[0];
		return downloadImage((String)imageView.getTag());
	}

	private Bitmap downloadImage(String url) {
		HttpGet request = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);
			InputStream responseBodyStream = response.getEntity().getContent();
			BufferedInputStream bufferedResponseBodyStream = new BufferedInputStream(responseBodyStream);
			Bitmap map = BitmapFactory.decodeStream(bufferedResponseBodyStream);
			bufferedResponseBodyStream.close();
			responseBodyStream.close();
			return map;	
		}
		catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}
}
