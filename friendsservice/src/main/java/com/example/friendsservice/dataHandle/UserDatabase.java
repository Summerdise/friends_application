package com.example.friendsservice.dataHandle;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.friendsservice.Comment;
import com.example.friendsservice.Moment;
import com.example.friendsservice.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Database(entities = {User.class, Moment.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class UserDatabase extends RoomDatabase {

    private static volatile UserDatabase database;

    private static final String APPLE_IMG = "https://ww1.sinaimg.cn/large/ad6fc8b3ly1gl9vymmyjgj2028028t8m.jpg";
    private static final String BANANA_IMG = "https://ww1.sinaimg.cn/large/ad6fc8b3ly1gl9vza9x42j20280280sn.jpg";
    private static final String CHERRY_IMG = "https://ww1.sinaimg.cn/large/ad6fc8b3ly1glafw2ybfmj2028028gli.jpg";

    public static UserDatabase getDatabase(Context context) {
        if (database == null) {
            synchronized (UserDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "users_database").allowMainThreadQueries().build();
                    initDatabase();
                }
            }
        }
        return database;
    }

    public abstract UserDao userDao();

    public abstract MomentDao momentDao();


    private static User initApple() {
        String userName = "Apple";
        String avatar = APPLE_IMG;
        String backgroundImage = APPLE_IMG;
        return new User(userName, avatar, backgroundImage);
    }

    private static Moment initAppleMoment(int id, List<String> imageList, List<String> agreeList, List<Comment> commentList) {
        String userName = "Apple";
        String avatar = APPLE_IMG;
        Date pubTime = null;
        return new Moment(id, userName, avatar, "I love Cherry", pubTime, imageList, agreeList, commentList);
    }

    public static void initDatabase() {
        database.userDao().insertAll(initApple());
        List<String> imageList = Arrays.asList(CHERRY_IMG);
        List<String> agreeList = Arrays.asList("Banana", "Peach", "Grape");
        List<Comment> commentList = Arrays.asList(new Comment("Banana", "cool"), new Comment("Peach", "Banana", "you too"));
        User user = database.userDao().selectNowUserInformation();
        int userId = user.getId();
        database.momentDao().insertAll(initAppleMoment(userId, imageList, agreeList, commentList));
    }

}
