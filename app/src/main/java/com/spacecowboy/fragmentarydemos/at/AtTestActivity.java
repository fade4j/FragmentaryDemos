package com.spacecowboy.fragmentarydemos.at;

import android.content.Intent;
import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.events.EUtil;
import com.spacecowboy.fragmentarydemos.events.EventSelectUser;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.spacecowboy.fragmentarydemos.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class AtTestActivity extends BaseActivity implements OnAtInputListener {
    @BindView(R.id.edit_input)
    AtEditText mEditText;

    @Override
    protected void initView(View view) {
        //mEditText = find(R.id.edit_input, view);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_at_test);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAtCharacterInput() {
        startActivity(new Intent(this, UserListActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventSelectUser eventSelectUser = EUtil.removeSticky(EventSelectUser.class);
        if (eventSelectUser != null) {
            if (Util.isEmpty(eventSelectUser.users)) {
                UiUtil.showToast("选择的用户列表为空！");
            } else {
                for (User user : eventSelectUser.users) {
                    mEditText.addAtContent(user.id, user.name);
                }
            }
        }
    }
}
