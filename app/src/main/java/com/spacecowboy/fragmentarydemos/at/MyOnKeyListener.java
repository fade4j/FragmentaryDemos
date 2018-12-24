package com.spacecowboy.fragmentarydemos.at;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.spacecowboy.fragmentarydemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class MyOnKeyListener implements View.OnKeyListener {
    private AtEditText editText;

    public MyOnKeyListener(AtEditText editText) {
        this.editText = editText;
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) { //当为删除键并且是按下动作时执行
            String content = editText.getText().toString();
            int selectionStart = editText.getSelectionStart();
            int endIndex = 0;
            int startIndex;
            int deleteNum = 0;
            List<Integer> spanIndexes = new ArrayList<Integer>();
            for (int i = 0; i < editText.getAtList().size(); i++) {
                String name = "@" + editText.getAtList().get(i).name;
                if ((startIndex = content.indexOf(name, endIndex)) != -1) {
                    if (startIndex > selectionStart) break; // 如果开始索引值,大于光标位置,那么退出遍历
                    endIndex = startIndex + name.length();
                    deleteNum = i;
                    spanIndexes.add(startIndex);//name 的开始索引，键值为偶数，从0开始
                    spanIndexes.add(startIndex + name.length()); //name 的结束索引，键值为奇数，从1开始
                    if (endIndex > selectionStart) break; // 如果结束索引值,大于光标位置,那么退出遍历
                }
            }
            // spanIndexes 必须大于0 且 光标位置不能大于 结束索引位置
            if (spanIndexes.size() > 0 && spanIndexes.get(spanIndexes.size() - 1) >= selectionStart) {
                editText.setText(changeTextColor(content.substring(0, spanIndexes.get(spanIndexes.size() - 2)) + content.substring(spanIndexes.get(spanIndexes.size() - 1)))); //字符串替换，删掉符合条件的字符串
                editText.setSelection(spanIndexes.get(spanIndexes.size() - 2));  // 设置光标位置
                editText.getAtList().remove(deleteNum); //删除对应实体
                return true;
            }
        }
        return false;
    }
    private SpannableString changeTextColor(String sText) {
        int endIndex = 0;
        int startIndex = 0;
        List<Integer> spanIndexes = new ArrayList<Integer>();
        for (int i = 0; i < editText.getAtList().size(); i++) {
            String tempname = "@" + editText.getAtList().get(i).name;
            if ((startIndex = sText.indexOf(tempname, endIndex)) != -1) {
                endIndex = startIndex + tempname.length();
                spanIndexes.add(startIndex);//name 的开始索引，键值为偶数，从0开始
                spanIndexes.add(startIndex + tempname.length()); //name 的结束索引，键值为奇数，从1开始
            }
        }

        SpannableString spanText = new SpannableString(sText);

        if (spanIndexes != null && spanIndexes.size() != 0) {
            for (int i = 0; i < spanIndexes.size(); i++) {
                if (i % 2 == 0) {  // 开始位置
                    startIndex = spanIndexes.get(i);
                } else {  // 结束位置
                    spanText.setSpan(new ForegroundColorSpan(editText.getContext().getResources().getColor(R.color.colorPrimaryDark)), startIndex, spanIndexes.get(i), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spanText;
    }
}
