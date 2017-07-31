package com.master.movie.moviemaster.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by stefan.bacevic on 7/31/2017.
 */

public class DbBitmapUtil {

        // convert from bitmap to byte array
        public static byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public static Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
}
