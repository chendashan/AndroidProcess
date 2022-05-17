package com.example.androidprocess.firstline.chapter3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprocess.R;

import java.util.List;

/**
 * @author chendashan
 * @date 2022/5/17
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> mMsgList;

    public MessageAdapter(List<Message> messageList) {
        this.mMsgList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = mMsgList.get(position);
        if (message.getType() == Message.TYPE_RECEIVED) {
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.llRight.setVisibility(View.GONE);
            holder.tvReceive.setText(message.getContent());
        } else {
            holder.llLeft.setVisibility(View.GONE);
            holder.llRight.setVisibility(View.VISIBLE);
            holder.tvSent.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llRight;
        LinearLayout llLeft;
        TextView tvReceive;
        TextView tvSent;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            llLeft = itemView.findViewById(R.id.ll_message_left);
            llRight = itemView.findViewById(R.id.ll_message_right);
            tvReceive = itemView.findViewById(R.id.tv_message_receive);
            tvSent = itemView.findViewById(R.id.tv_message_sent);
        }
    }
}
