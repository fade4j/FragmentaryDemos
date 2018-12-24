package com.spacecowboy.fragmentarydemos.at;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import com.spacecowboy.fragmentarydemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class AtEditText extends EditText implements Runnable {
    ArrayList<User> atList;
    private Context context;

    public AtEditText(Context context) {
        super(context);
        initView(context);
    }

    public AtEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AtEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        atList = new ArrayList<>();
        setOnKeyListener(new MyOnKeyListener(this));
        MentionTextWatcher watcher = new MentionTextWatcher(this);
        watcher.setOnAtInputListener((OnAtInputListener) context);
        addTextChangedListener(watcher);
    }


    /**
     * 添加@的内容
     */
    public void addAtContent(String id, String content) {
        atList.add(new User(id, content));
        // 光标位置之前的字符是否是 @ , 如果不是 加上一个@
        int selectionStart = getSelectionStart();
        // 获取当前内容
        String sss = getText().toString();
        // 获取光标前以为字符
        String s = selectionStart != 0 ? sss.toCharArray()[selectionStart - 1] + "" : "";
        // 将内容插入 , 改变文字颜色
        setText(changeTextColor(sss.substring(0, selectionStart) + (!s.equals("@") ? "@" : "") + content + sss.substring(selectionStart, sss.length()))); //字符串替换，删掉符合条件的字符串
        // 设置光标位置
        setSelection((sss.substring(0, selectionStart) + (!s.equals("@") ? "@" : "") + content).length());
    }

    public SpannableString changeTextColor(String sText) {
        int endIndex = 0;
        int startIndex = 0;
        List<Integer> spanIndexes = new ArrayList<Integer>();
        for (int i = 0; i < atList.size(); i++) {
            String tempname = "@" + atList.get(i).name;
            endIndex = 0;
            String tempText = sText;
            while ((startIndex = tempText.indexOf(tempname, endIndex)) != -1) {
                endIndex = startIndex + tempname.length();
                spanIndexes.add(startIndex);//name 的开始索引，键值为偶数，从0开始
                spanIndexes.add(startIndex + tempname.length()); //name 的结束索引，键值为奇数，从1开始
                //tempText = tempText.substring(endIndex);
            }

            /*if ((startIndex = sText.indexOf(tempname, endIndex)) != -1) {
                endIndex = startIndex + tempname.length();
                spanIndexes.add(startIndex);//name 的开始索引，键值为偶数，从0开始
                spanIndexes.add(startIndex + tempname.length()); //name 的结束索引，键值为奇数，从1开始
            }*/
        }

        SpannableString spanText = new SpannableString(sText);

        if (spanIndexes != null && spanIndexes.size() != 0) {
            for (int i = 0; i < spanIndexes.size(); i++) {
                if (i % 2 == 0) {  // 开始位置
                    startIndex = spanIndexes.get(i);
                } else {  // 结束位置
                    spanText.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimaryDark)), startIndex, spanIndexes.get(i), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spanText;
    }

    public ArrayList<User> getAtList() {
        return atList;
    }

    private boolean isChecking = false;

    public boolean isChecking() {
        return isChecking;
    }

    @Override
    public void run() {
        isChecking = true;
        int selection = getSelectionStart();
        setText(changeTextColor(getText().toString()));
        setSelection(selection);
        removeCallbacks(this);
        isChecking = false;
    }
}
