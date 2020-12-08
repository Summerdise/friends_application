package com.example.friendsapplication.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.friendsapplication.R;
import com.example.friendsapplication.model.UserDatabase;
import com.example.friendsapplication.model.Moment;
import com.example.friendsapplication.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoImageCreateActivity extends AppCompatActivity {

    @BindView(R.id.title_publish_button)
    Button publishButton;
    @BindView(R.id.input_text)
    EditText inputText;

    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_image_create);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        userDatabase = UserDatabase.getDatabase(this);

        inputText.addTextChangedListener(new TextChangedWatcher());
        publishButton.setOnClickListener(new PublishButtonListener());
    }

    class TextChangedWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                publishButton.setClickable(false);
                publishButton.setTextColor(Color.rgb(192, 192, 192));
                publishButton.setBackgroundColor(Color.rgb(246, 247, 248));
            } else {
                publishButton.setClickable(true);
                publishButton.setTextColor(Color.rgb(255, 255, 255));
                publishButton.setBackgroundColor(Color.rgb(50, 205, 50));
            }
        }
    }

    class PublishButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String inputContent = String.valueOf(inputText.getText());
            User nowUser = userDatabase.userDao().selectNowUserInformation();
            Moment moment = new Moment(nowUser.getId(), nowUser.getUserName(), nowUser.getAvatar(), inputContent, null, null, null, null);
            userDatabase.momentDao().insertAll(moment);
            finish();
        }
    }
}
