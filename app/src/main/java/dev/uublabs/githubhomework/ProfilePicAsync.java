package dev.uublabs.githubhomework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Admin on 11/16/2017.
 */

public class ProfilePicAsync extends AsyncTask<String, Void, Bitmap>
{
    ImageView bmImage;

    public ProfilePicAsync(ImageView imageView)
    {
        this.bmImage = imageView;
    }

    protected Bitmap doInBackground(String... urls)
    {
        String picUrl = urls[0];
        Bitmap profilePic = null;
        try
        {
            InputStream in = new java.net.URL(picUrl).openStream();
            profilePic = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return profilePic;
    }

    protected void onPostExecute(Bitmap result)
    {
        bmImage.setImageBitmap(result);
    }
}
