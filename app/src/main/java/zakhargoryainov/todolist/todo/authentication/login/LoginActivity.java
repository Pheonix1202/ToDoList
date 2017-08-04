package zakhargoryainov.todolist.todo.authentication.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 01.08.2017.
 */

public class LoginActivity extends MvpActivity implements LoginView{

    @InjectPresenter
    LoginPresenter loginPresenter;

    @BindViews({R.id.edit_text_email,R.id.edit_text_password})
    List<EditText> editTexts;

    @BindView (R.id.button_sign_in)
    Button buttonSignIn;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.button_sign_in)
    public void OnSignInClick(){
        loginPresenter.signIn(
                editTexts.get(0).getText().toString(),
                editTexts.get(1).getText().toString());
    }

    @Override
    public void onSuccessSignIn() {
        Toast.makeText(getContext(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedSignIn(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void gotoRegistration() {

    }

    @Override
    public void gotoHome() {

    }
}
