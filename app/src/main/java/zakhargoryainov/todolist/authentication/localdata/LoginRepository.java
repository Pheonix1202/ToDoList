package zakhargoryainov.todolist.authentication.localdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;

import javax.inject.Inject;

/**
 * Created by Захар on 22.08.2017.
 */

public class LoginRepository {

    private final String TODOLIST_PREFERENCES = "zakhargoryainov.todolist.todolist_preferences";
    private final String LOGIN_KEY = "login";
    private final String PASSWORD_KEY= "password";
    private final Context context;

    public LoginRepository(Context context){
        this.context = context;
    }

    public void saveLoginPassword(String login, String password){
        SharedPreferences preferences = context.getSharedPreferences(TODOLIST_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LOGIN_KEY,login);
        editor.putString(PASSWORD_KEY,password);
        editor.apply();
    }

    public boolean containsLoginPassword(){
        SharedPreferences preferences = context.getSharedPreferences(TODOLIST_PREFERENCES,Context.MODE_PRIVATE);
        return preferences.contains(LOGIN_KEY) && preferences.contains(PASSWORD_KEY);
    }

    public Pair<String,String> loadLoginPassword(){
        SharedPreferences preferences = context.getSharedPreferences(TODOLIST_PREFERENCES,Context.MODE_PRIVATE);
        return new Pair<>(
                preferences.getString(LOGIN_KEY,""),
                preferences.getString(PASSWORD_KEY,"")
        );
    }
}
