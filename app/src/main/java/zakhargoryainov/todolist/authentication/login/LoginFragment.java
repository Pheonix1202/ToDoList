package zakhargoryainov.todolist.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.home.presentation.HomeActivity;

//todo check why mvpFragment doesn't work correctly unlike mvpActivity
public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    LoginPresenter loginPresenter;

    @BindViews({R.id.edit_text_email,R.id.edit_text_password})
    List<EditText> editTexts;

    @BindView (R.id.button_sign_in)
    Button buttonSignIn;

    @BindView(R.id.dot_progress_bar)
    DotProgressBar dotProgressBar;

    private Unbinder unbinder;

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
        dotProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.button_sign_in)
    public void OnSignInClick(){
        loginPresenter.signIn( //todo surround fields with format exceptions?
                editTexts.get(0).getText().toString(),
                editTexts.get(1).getText().toString());
    }

    @Override
    public void onSuccessSignIn() {
        Toast.makeText(getContext(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext() ,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        dotProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        dotProgressBar.setVisibility(View.INVISIBLE);
    }

}