package com.fabantowapi.joetz_android.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.fabantowapi.joetz_android.R;

import java.io.InputStream;

/**
 * Created by a_176_000 on 31-7-2016.
 */
public class ImageDownloadTask  extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    Context context;

    public ImageDownloadTask(ImageView bmImage, Context context) {
        this.bmImage = bmImage;
        this.context = context;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
          Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        if(mIcon == null){
            mIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.offline_image);
        }

        Bitmap resized = Bitmap.createScaledBitmap(mIcon, 800, 500, true);

        return resized;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);

    }
}
