
package zakhargoryainov.todolist.todo.authentication.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zakhargoryainov.todolist.MvpAppCompatFragment;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 01.08.2017.
 */

public class LoginFragment extends MvpAppCompatFragment implements LoginView{

    @BindView(R.id.header)
    TextView textView;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @BindViews({R.id.edit_text_email,R.id.edit_text_password})
    List<EditText> editTexts;

    @BindView (R.id.button_sign_in)
    Button buttonSignIn;

    @BindView(R.id.dot_progress_bar)
    DotProgressBar dotProgressBar;

    private Unbinder unbinder;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_login);
//        unbinder = ButterKnife.bind(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.button_sign_in)
    public void OnSignInClick(){
        loginPresenter.signIn( //todo validate this
                editTexts.get(0).getText().toString(),
                editTexts.get(1).getText().toString());
    }

    @Override
    public void onSuccessSignIn() {
        Toast.makeText(getContext(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedSignIn(String message) {
        Toast.makeText(getContext() ,message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {
        dotProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        dotProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void gotoRegistration() {

    }

    @Override
    public void gotoHome() {

    }
}