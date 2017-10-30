package com.spacecowboy.fragmentarydemos.normaltest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.view.DragEvent;
import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.lang.reflect.Field;

import butterknife.BindView;

public class IdleHandlerTestActivity extends BaseActivity implements OnDrawFinishedListener {
    private long time;
    @BindView(R.id.tv_last)
    CustomTextView customTextView;
    private boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        time = System.currentTimeMillis();
        L.e("IdleHandlerTestActivity", "onCreate");
        //HandlerThread thread = new HandlerThread(NAME);
        /*Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return isFinished;
            }
        });*/

    }

    private void loop() {
        HandlerThread handlerThread = new HandlerThread(NAME);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //sendEmptyMessage(1);
                L.e("IdleHandlerTestActivity", "handleMessage");
            }
        };
        try {
            Field filed = Looper.class.getDeclaredField("mQueue");
            filed.setAccessible(true);
            MessageQueue queue = (MessageQueue) filed.get(handlerThread.getLooper());
            queue.addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    L.e("IdleHandlerTestActivity", "queueIdle " + (System.currentTimeMillis() - time));
                    return isFinished;
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        handler.sendEmptyMessage(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e("IdleHandlerTestActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e("IdleHandlerTestActivity", "onResume " + (System.currentTimeMillis() - time));

    }

    @Override
    protected void initView(View view) {
        loop();
        customTextView.setDrawFinishedListener(this);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_handler_thread_test);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDrawFinished() {
        isFinished = true;

        L.e("IdleHandlerTestActivity", "onDrawFinished " + (System.currentTimeMillis() - time));
    }
}
