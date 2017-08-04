package zakhargoryainov.todolist.todo.authentication.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.MvpAppCompatFragment;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 04.08.2017.
 */

public class RegistrationFragment extends MvpAppCompatFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
}
