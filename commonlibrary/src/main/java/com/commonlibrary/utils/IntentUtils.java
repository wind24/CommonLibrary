package com.commonlibrary.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Huangzefeng on 4/2/17.
 */
public class IntentUtils {

    /**
     * 启动系统相机
     *
     * @param act
     * @param outputPath
     * @param requestCode
     */
    public static void startSystemCamera(Activity act, String outputPath, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = Uri.parse(outputPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        act.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动系统相册
     *
     * @param act
     * @param outputPath
     * @param requestCode
     */
    public static void startSystemAlbum(Activity act, String outputPath, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        act.startActivityForResult(intent, requestCode);
    }

    /**
     * 转换系统相册选择结果uri为string
     * @param context
     * @param albumUri
     * @return
     */
    public static String convertAlbumResult(Context context, Uri albumUri) {
        if (null == albumUri) return null;
        final String scheme = albumUri.getScheme();
        String data = null;
        if (scheme == null)
            data = albumUri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = albumUri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(albumUri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
