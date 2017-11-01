package com.spacecowboy.fragmentarydemos;

import android.Manifest;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.spacecowboy.fragmentarydemos.utils.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

public class DailyTestActivity extends BaseActivity {
    @BindView(R.id.button_load_bitmap)
    View btnLoadBitmap;
    @BindView(R.id.button_get_size)
    View btnGetBitmapSize;
    @BindView(R.id.imageView)
    ImageView mImageView;
    private Bitmap mSrcBitmap;

    private final int REQUEST_CODE_STORAGE = 1;

    @Override
    protected void initDataAfterViews() {
        ArrayList<String> list = new ArrayList<>();
        //list.add(5, "hahaha");
        for (int i = 0; i < list.size(); i++) {
            Log.e("DailyTestActivity", list.get(i));
        }
        //getHeapSize();
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_STORAGE);
        //loadLocalFile();
    }

    private void loadLocalFile() {
        if (Util.getExternalStorage() == null) {
            return;
        }
        File srcFile = new File(Util.getExternalStorage(), "abcd.jpg");
        if (!srcFile.exists()) {
            return;
        }

        L.e(NAME, "srcFile.length = " + srcFile.length());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcFile.getAbsolutePath(), options);
        int oWidth = options.outWidth;
        int oHeight = options.outHeight;

        float scaleX = oWidth * 1.0f / UiUtil.getScreenWidth();
        float scaleY = oHeight * 1.0f / UiUtil.getScreenHeight();
        L.e(NAME, "scaleX = " + scaleX + " scaleY = " + scaleY);
        L.e(NAME, "UiUtil.getScreenWidth() = " + UiUtil.getScreenWidth() + " UiUtil.getScreenHeight() = " + UiUtil.getScreenHeight());
        float scale = Math.max(scaleX, scaleY);
        int insampleSize = 1;
        if (scale % 2 > 0) {
            insampleSize = (int) ((scale / 2 + 1) * 2);
        }
        options.inJustDecodeBounds = false;
        //options.inScaled = true;
        options.inSampleSize = insampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(srcFile.getAbsolutePath(), options);
        L.e(NAME, "bitmap.getByteCount = " + bitmap.getByteCount());
        L.e(NAME, "scale = " + scale + " " + options.inSampleSize);
        L.e(NAME, "bitmap.getByteCount = " + options.outWidth + " , " + options.outHeight);
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        L.e(NAME, "requestCode = " + requestCode);
        for (String permission : permissions) {
            L.e(NAME, "permission = " + permission);
        }

        for (int i = 0; i < grantResults.length; i++) {
            L.e(NAME, "permission = " + grantResults[i]);
        }

        if (requestCode == REQUEST_CODE_STORAGE) {
            if (grantResults[0] == 0) {
                loadLocalFile();
            }
        }
    }

    /**
     * 获取手机对应用分配的堆内存
     */
    private void getHeapSize() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        Log.e(NAME, "heapSize = " + manager.getMemoryClass() + " , largeHapSize = " + manager.getLargeMemoryClass());
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        Log.e(NAME, "totalMemory = " + totalMemory + " , maxMemory = " + maxMemory + " , freeMemory = " + freeMemory);

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        Log.e(NAME, "onTrimMemory level = " + level);

    }

    @Override
    protected void initView(View view) {
        btnLoadBitmap.setOnClickListener(this);
        btnGetBitmapSize.setOnClickListener(this);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_daily_test);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_load_bitmap:
                if (mSrcBitmap == null) {
                    mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.source);
                }

                Log.e("DailyTestActivity", "load_bitmap size = " + String.format("%1d * %2d = %3d", mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getWidth() * mSrcBitmap.getHeight()));
                break;

            case R.id.button_get_size:
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                mSrcBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

                Log.e("DailyTestActivity", "get size = " + outputStream.toByteArray().length);
                break;
        }
    }
}
