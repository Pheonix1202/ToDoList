package zakhargoryainov.todolist.todo.home.done;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 02.08.2017.
 */

public class DoneFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done,container,false);
        return view;
    }
}