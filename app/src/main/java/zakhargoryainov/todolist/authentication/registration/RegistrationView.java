package zakhargoryainov.todolist.todo.authentication.registration;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Захар on 04.08.2017.
 */

public interface RegistrationView extends MvpView {

    void onSuccessSignUp();

    void onFailedSignUp();

    void ShowProgress();

    void HideProgress();
}
