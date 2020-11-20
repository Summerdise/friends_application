package com.example.friendsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;

import java.util.List;

import butterknife.BindView;

public class UserInfoViewAdapter extends RecyclerView.Adapter {

    private static final int HEAD = 1;
    private static final int ITEM = 2;

    Context context;
    UserDatabase database;
    List<Data> data;

    public UserInfoViewAdapter(List<Data> data, Context context) {
        this.data = data;
        this.context = context;
        database = UserDatabase.getDatabase(context);
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

        ImageButton itemMoreButton;
        Button agreeButton;
        Button commentButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemAvatar = itemView.findViewById(R.id.item_avatar);
            itemUserName = itemView.findViewById(R.id.item_user_name);
            itemContent = itemView.findViewById(R.id.item_content);
            descriptionImage = itemView.findViewById(R.id.description_img);
            itemUserAgree = itemView.findViewById(R.id.item_agree_user_list);
            itemComment = itemView.findViewById(R.id.item_comment);
            agreeIcon = itemView.findViewById(R.id.agree_icon);

            itemMoreButton = itemView.findViewById(R.id.item_more_button);
            agreeButton = itemView.findViewById(R.id.agree_button);
            commentButton = itemView.findViewById(R.id.comment_button);
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
                moreItemShowAndHide(itemViewHolder);
                isAgreeChange(itemViewHolder);
                return itemViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (data.get(position).getType()) {
            case HEAD:
                User userInformation = data.get(position).getUser();
                ((HeadViewHolder) holder).backgroundImage.setImageResource(userInformation.getBackgroundImage());
                ((HeadViewHolder) holder).userName.setText(userInformation.getUserName());
                ((HeadViewHolder) holder).avatar.setImageResource(userInformation.getAvatar());
                break;
            case ITEM:
                Moment moment = data.get(position).getMoment();
                ((ItemViewHolder) holder).itemAvatar.setImageResource(moment.getAvatar());
                ((ItemViewHolder) holder).itemUserName.setText(moment.getUserName());
                ((ItemViewHolder) holder).itemContent.setText(moment.getContent());
                if (moment.getImageList() != null) {
                    ((ItemViewHolder) holder).descriptionImage.setImageResource(moment.getImageList().get(0));
                }
                if (moment.getAgreeList() != null) {
                    ((ItemViewHolder) holder).agreeIcon.setImageResource(R.drawable.agree_icon);
                    ((ItemViewHolder) holder).itemUserAgree.setText(getAgreeInformation(moment.getAgreeList()));
                }
                if (moment.getCommentList() != null) {
                    ((ItemViewHolder) holder).itemComment.setText(getCommentsInformation(moment.getCommentList()));
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

    public void moreItemShowAndHide(ItemViewHolder itemViewHolder){
        itemViewHolder.itemMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemViewHolder.agreeButton.getVisibility() == View.INVISIBLE) {
                    itemViewHolder.agreeButton.setVisibility(View.VISIBLE);
                    itemViewHolder.commentButton.setVisibility(View.VISIBLE);
                } else {
                    itemViewHolder.agreeButton.setVisibility(View.INVISIBLE);
                    itemViewHolder.commentButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void isAgreeChange(ItemViewHolder itemViewHolder){
        itemViewHolder.agreeButton.setOnClickListener(v -> {
            int index = itemViewHolder.getAdapterPosition();
            Moment moment= database.momentDao().selectMomentsById(index);
            List<String> agreeList = moment.getAgreeList();
            String nowUserName = data.get(0).getUser().getUserName();
            if(!agreeList.contains(nowUserName)){
                agreeList.add(nowUserName);
            }else{
                agreeList.remove(nowUserName);
            }
            moment.setAgreeList(agreeList);
            database.momentDao().updateMoments(moment);
            data.get(index).setMoment(moment);
            notifyDataSetChanged();
        });
    }

//    public void addComment(ItemViewHolder itemViewHolder){
//        itemViewHolder.agreeButton.setOnClickListener(v -> {
//            int index = itemViewHolder.getAdapterPosition();
//            Moment moment= database.momentDao().selectMomentsById(index);
//            List<Comment> commentList = moment.getCommentList();
//            String nowUserName = data.get(0).getUser().getUserName();
//
//
//            moment.setCommentList(commentList);
//            database.momentDao().updateMoments(moment);
//            data.get(index).setMoment(moment);
//            notifyDataSetChanged();
//        });
//    }
}
