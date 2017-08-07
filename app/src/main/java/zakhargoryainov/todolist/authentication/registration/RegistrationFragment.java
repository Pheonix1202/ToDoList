package zakhargoryainov.todolist.authentication.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 04.08.2017.
 */

public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView {

    @InjectPresenter
    RegistrationPresenter registrationPresenter;

    @BindViews({R.id.edit_text_email, R.id.edit_text_password, R.id.edit_text_confirm_password})
    List<EditText> editTexts;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    @BindView(R.id.dot_progress_bar)
    DotProgressBar dotProgressBar;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        unbinder = ButterKnife.bind(this, view);
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


    @OnClick(R.id.button_sign_up)
    public void onClick() { //todo surround fields with format exceptions?
        registrationPresenter.SignUp(
                editTexts.get(0).getText().toString(),
                editTexts.get(1).getText().toString(),
                editTexts.get(2).getText().toString());

    }


    @Override
    public void onSuccessSignUp() {
        Toast.makeText(getContext(), "Вы успешно зарегистрировались", Toast.LENGTH_LONG).show();
        ViewPager pager = (ViewPager) getActivity().findViewById(R.id.view_pager_auth);
        pager.setCurrentItem(0);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
