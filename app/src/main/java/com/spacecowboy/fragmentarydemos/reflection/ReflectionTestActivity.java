package com.spacecowboy.fragmentarydemos.reflection;

import android.view.View;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/26
 * @description:
 */

public class ReflectionTestActivity extends BaseActivity {
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @MyAnnotation(R.id.tv_result)
    @BindView(R.id.tv_trigger)
    TextView mTvTrigger;

    @Override
    protected void initView(View view) {
        //        testReflection();
        MyAnnotationUtil.bind(this);
        MyAnnotationUtil.printSize();
    }

    private void testReflection() {
        Student student = new Student();
        //Field[] fields = student.getClass().getDeclaredFields();
        Field[] fields = student.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            L.e(NAME, "field : " + fields[i].getName());
        }
        Field name = null;
        try {
            // 获取属性
            Class<? extends Student> aClass = student.getClass();
            name = aClass.getDeclaredField("name");
            name.setAccessible(true);
            mTvResult.setText("name: " + name.get(student).toString());
            // 执行带参方法
            Method setName = aClass.getDeclaredMethod("setName", String.class);
            setName.setAccessible(true);
            setName.invoke(student, "LiSi");
            // 执行不带参方法
            Method getName = aClass.getDeclaredMethod("getName");
            getName.setAccessible(true);
            Object getNameStr = getName.invoke(student);
            mTvResult.setText("getName: " + getNameStr);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.reflection_test_activity);
    }

    @Override
    public void onClick(View v) {

    }
}
