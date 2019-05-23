package com.brena.noteslab13.repositories;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.brena.noteslab13.models.User;
import com.orm.SugarRecord;

import java.util.List;

public class UserRepository {

    public static void create_user(String fullname,String email,String password){

        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);

        SugarRecord.save(user);
    }

    public static User login(String email,String password){
        List<User> users=SugarRecord.find(User.class,"email=? and password=?",email,password);
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    public static User load(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void callLogout(Activity activity){
        // remove from SharedPreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("islogged", false).commit();
    }

}
