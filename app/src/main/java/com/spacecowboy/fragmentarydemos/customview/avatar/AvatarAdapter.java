package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.AvatarHolder> {
    private boolean isForward = true;
    private int currentPos=0;
    private int prePosition;
    private float offset;
    private ArrayList<String> mList = new ArrayList<>();
    private Context context;
    private View.OnClickListener mListener;
    public AvatarAdapter(Context context, ArrayList<String> list, View.OnClickListener listener){
        mList.addAll(list);
        this.context = context;
        mListener = listener;
        L.e("AvatarAdapter");
    }

    @Override
    public AvatarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        L.e("onCreateViewHolder");
        //UiUtil.inflate(context, R.layout.item_avatar)
        return new AvatarHolder(LayoutInflater.from(context).inflate(R.layout.item_avatar, parent, false));
    }

    @Override
    public void onBindViewHolder(AvatarHolder holder, int position) {
        L.e("onBindViewHolder");
        //holder.mItemRoot.setBackgroundColor(UiUtil.getResColor(R.color.color_DDDDDD));
        //holder.image.setImageURI("http://pics.sc.chinaz.com/files/pic/pic9/201801/zzpic9473.jpg");
        holder.tvCount.setText(String.valueOf(position + 1));
        holder.tvCount.setTag(position);
        holder.tvCount.setOnClickListener(mListener);
        if (position == prePosition){
            float factor = 0;
            if (isForward){
                factor = 1 - offset;
            } else {
                factor = offset;
            }
            float temp = UiUtil.sp2px(40) * factor;
            if (temp < UiUtil.sp2px(20)){
                temp = UiUtil.sp2px(20);
            }
            holder.tvCount.setTextSize(temp);
        } else if (currentPos == position){
            float factor = 0;
            if (isForward){
                factor = 1 + offset;
            } else {
                factor = 1 - offset + 1;
            }
            float temp = UiUtil.sp2px(20) * factor;
            if (temp > UiUtil.sp2px(40)){
                temp = UiUtil.sp2px(40);
            }
            holder.tvCount.setTextSize(temp);

        } else {
            holder.tvCount.setTextSize(UiUtil.sp2px(20));
        }
    }

    @Override
    public int getItemCount() {
        L.e("getItemCount");
        return mList.size();
    }

    public void notifyScrolled(int prePosition, float preOffset){
        if (offset > preOffset){
            isForward = false;
            currentPos = prePosition - 1;
            if (currentPos < 0){
                currentPos = 0;
            }
        } else {
            isForward = true;
            currentPos = prePosition + 1;
            if (currentPos == mList.size()){
                currentPos = mList.size() -1;
            }
        }
        offset = preOffset;
        if (preOffset != 0){
            this.prePosition = prePosition;
        }
        notifyDataSetChanged();
    }

    public class AvatarHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView image;
        TextView tvCount;
        public AvatarHolder(View itemView) {
            super(itemView);
            //image = (SimpleDraweeView) itemView.findViewById(R.id.simple);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
        }
    }
}
