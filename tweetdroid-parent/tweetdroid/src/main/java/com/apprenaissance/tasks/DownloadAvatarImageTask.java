package com.apprenaissance.tasks;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

	private Bitmap downloadImage(String urlString) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			BufferedInputStream bufferedResponseBodyStream = new BufferedInputStream(connection.getInputStream());
			Bitmap map = BitmapFactory.decodeStream(bufferedResponseBodyStream);
			bufferedResponseBodyStream.close();
			return map;	
		}
		catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		} finally {
			if(null != connection) connection.disconnect();
		}
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}
}
