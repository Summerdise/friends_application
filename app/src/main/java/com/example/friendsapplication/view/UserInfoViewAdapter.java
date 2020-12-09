package com.example.friendsapplication.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.R;
import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;
import com.example.friendsapplication.data.Moment;
import com.example.friendsapplication.data.User;
import com.example.friendsapplication.data.UserDatabase;

import java.util.ArrayList;
import java.util.List;

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
        EditText commentEditText;
        Button commentPublishButton;

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
            commentEditText=itemView.findViewById(R.id.add_comment_edit);
            commentPublishButton = itemView.findViewById(R.id.add_comment_btn);
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
                commentEditShowAndHide(itemViewHolder);
                addComment(itemViewHolder);
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
                if (moment.getAgreeList() == null || moment.getAgreeList().size() == 0) {
                    ((ItemViewHolder) holder).agreeIcon.setVisibility(View.GONE);
                    ((ItemViewHolder) holder).itemUserAgree.setVisibility(View.GONE);

                } else {
                    ((ItemViewHolder) holder).agreeIcon.setVisibility(View.VISIBLE);
                    ((ItemViewHolder) holder).itemUserAgree.setVisibility(View.VISIBLE);
                    ((ItemViewHolder) holder).agreeIcon.setImageResource(R.drawable.agree_icon);
                    ((ItemViewHolder) holder).itemUserAgree.setText(getAgreeInformation(moment.getAgreeList()));
                }
                if (moment.getCommentList() != null) {
                    ((ItemViewHolder) holder).itemComment.setVisibility(View.VISIBLE);
                    ((ItemViewHolder) holder).itemComment.setText(getCommentsInformation(moment.getCommentList()));
                } else {
                    ((ItemViewHolder) holder).itemComment.setVisibility(View.GONE);
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
        if (stringBuilder.length() > 2) {
            return stringBuilder.substring(0, stringBuilder.length() - 2);
        } else {
            return stringBuilder.toString();
        }
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
        if (stringBuilder.length() > 1) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        } else {
            return stringBuilder.toString();
        }
    }

    public void moreItemShowAndHide(ItemViewHolder itemViewHolder) {
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

    public void commentEditShowAndHide(ItemViewHolder itemViewHolder) {
        itemViewHolder.commentButton.setOnClickListener(v -> {
            if (itemViewHolder.commentEditText.getVisibility() == View.GONE) {
                itemViewHolder.commentEditText.setVisibility(View.VISIBLE);
                itemViewHolder.commentPublishButton.setVisibility(View.VISIBLE);
                itemViewHolder.agreeButton.setVisibility(View.INVISIBLE);
                itemViewHolder.commentButton.setVisibility(View.INVISIBLE);
            }else{
                itemViewHolder.commentEditText.setVisibility(View.GONE);
                itemViewHolder.commentPublishButton.setVisibility(View.GONE);
            }
        });
    }

    public void isAgreeChange(ItemViewHolder itemViewHolder) {
        itemViewHolder.agreeButton.setOnClickListener(v -> {
            int index = itemViewHolder.getAdapterPosition();
            Moment moment = database.momentDao().selectMomentsById(index);
            List<String> agreeList = moment.getAgreeList();
            String nowUserName = data.get(0).getUser().getUserName();
            if (agreeList == null) {
                agreeList = new ArrayList<>();
                agreeList.add(nowUserName);
            } else {
                if (!agreeList.contains(nowUserName)) {
                    agreeList.add(nowUserName);
                } else {
                    agreeList.remove(nowUserName);
                }
            }
            moment.setAgreeList(agreeList);
            database.momentDao().updateMoments(moment);
            data.get(index).setMoment(moment);
            notifyDataSetChanged();
        });
    }

    public void addComment(ItemViewHolder itemViewHolder){
        itemViewHolder.commentPublishButton.setOnClickListener(v -> {
            int index = itemViewHolder.getAdapterPosition();
            Moment moment= database.momentDao().selectMomentsById(index);
            List<Comment> commentList = moment.getCommentList();
            String nowUserName = data.get(0).getUser().getUserName();
            String replyContent = String.valueOf(itemViewHolder.commentEditText.getText());
            Comment newComment = new Comment(nowUserName,replyContent);
            if(commentList==null){
                commentList = new ArrayList<>();
            }
            commentList.add(newComment);
            moment.setCommentList(commentList);
            database.momentDao().updateMoments(moment);
            data.get(index).setMoment(moment);
            itemViewHolder.commentEditText.setVisibility(View.GONE);
            itemViewHolder.commentPublishButton.setVisibility(View.GONE);
            closeKeyboard((Activity) context);
            notifyDataSetChanged();
        });
    }

    public static void closeKeyboard(Activity activity) {
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
