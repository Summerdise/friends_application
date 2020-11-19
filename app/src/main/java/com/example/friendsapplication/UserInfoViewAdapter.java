package com.example.friendsapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;
import com.example.friendsapplication.data.MomentsItem;
import com.example.friendsapplication.data.UserInformation;

import java.util.List;

public class UserInfoViewAdapter extends RecyclerView.Adapter {

    private static final int HEAD = 1;
    private static final int ITEM = 2;
    List<Data> data;

    public UserInfoViewAdapter(List<Data> data) {
        this.data = data;
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        ImageView backgroundImage;
        ImageView avatar;
        TextView userName;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImage = itemView.findViewById(R.id.background_image);
            avatar = itemView.findViewById(R.id.user_avatar);
            userName = itemView.findViewById(R.id.user_name);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemAvatar;
        TextView itemUserName;
        TextView itemContent;
        ImageView descriptionImage;
        TextView itemUserAgree;
        TextView itemComment;
        ImageView agreeIcon;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemAvatar = itemView.findViewById(R.id.item_avatar);
            itemUserName = itemView.findViewById(R.id.item_user_name);
            itemContent = itemView.findViewById(R.id.item_content);
            descriptionImage = itemView.findViewById(R.id.description_img);
            itemUserAgree = itemView.findViewById(R.id.item_agree_user_list);
            itemComment = itemView.findViewById(R.id.item_comment);
            agreeIcon = itemView.findViewById(R.id.agree_icon);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_item, parent, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(headView);
                return headViewHolder;
            case ITEM:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
                ItemViewHolder itemViewHolder = new ItemViewHolder(itemView);
                return itemViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (data.get(position).getType()) {
            case HEAD:
                UserInformation userInformation = data.get(position).getUserInformation();
                ((HeadViewHolder) holder).backgroundImage.setImageResource(userInformation.getBackgroundImage());
                ((HeadViewHolder) holder).userName.setText(userInformation.getUserName());
                ((HeadViewHolder) holder).avatar.setImageResource(userInformation.getAvatar());
                break;
            case ITEM:
                MomentsItem momentsItem = data.get(position).getMomentsItem();
                ((ItemViewHolder) holder).itemAvatar.setImageResource(momentsItem.getAvatar());
                ((ItemViewHolder) holder).itemUserName.setText(momentsItem.getUserName());
                ((ItemViewHolder) holder).itemContent.setText(momentsItem.getContent());
                if (momentsItem.getImageList() != null) {
                    ((ItemViewHolder) holder).descriptionImage.setImageResource(momentsItem.getImageList().get(0));
                }
                if (momentsItem.getAgreeList() != null) {
                    ((ItemViewHolder) holder).agreeIcon.setImageResource(R.drawable.agree_icon);
                    ((ItemViewHolder) holder).itemUserAgree.setText(getAgreeInformation(momentsItem.getAgreeList()));
                }
                if (momentsItem.getCommentList() != null) {
                    ((ItemViewHolder) holder).itemComment.setText(getCommentsInformation(momentsItem.getCommentList()));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getAgreeInformation(List<String> agreeList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : agreeList) {
            stringBuilder.append(s);
            stringBuilder.append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    public String getCommentsInformation(List<Comment> commentList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Comment comment : commentList) {
            if (null == comment.getToUserName()) {
                stringBuilder.append(comment.getFromUserName() + ": " + comment.getComment() + "\n");
            } else {
                stringBuilder.append(comment.getFromUserName() + " 回复 " + comment.getToUserName() + ": " + comment.getComment() + "\n");
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
