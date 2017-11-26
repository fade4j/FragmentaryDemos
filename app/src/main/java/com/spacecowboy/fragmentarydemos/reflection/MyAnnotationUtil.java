package com.spacecowboy.fragmentarydemos.reflection;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.utils.L;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/26
 * @description:
 */

public class MyAnnotationUtil {
    static ArrayList<TextView> mViews = new ArrayList<>();

    public static void bind(Object o){
        Activity activity = (Activity) o;
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i ++)
        {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation[] fieldDeclaredAnnotations = field.getDeclaredAnnotations();
            for (int j = 0; j < fieldDeclaredAnnotations.length; j++)
            {
                Annotation fieldDeclaredAnnotation = fieldDeclaredAnnotations[j];
                if (fieldDeclaredAnnotation instanceof MyAnnotation){
                    int[] value = ((MyAnnotation) fieldDeclaredAnnotation).value();
                    for (int k = 0; k < value.length; k ++){
                        TextView textView = (TextView) activity.findViewById(value[k]);
                        textView.setText("After Bind");
                        mViews.add(textView);
                    }
                    break;
                }
            }

        }
    }

    public static void printSize()
    {
        L.e("MYANNOTATIONUTIL", "mViews.size = " + mViews.size());
    }
}
