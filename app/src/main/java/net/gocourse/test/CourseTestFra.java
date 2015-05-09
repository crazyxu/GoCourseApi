package net.gocourse.test;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.gocourse.api.action.UserManager;
import net.gocourse.api.bean.ActionResEntity;


public class CourseTestFra extends Fragment implements View.OnClickListener{
    MyApplication app;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_course_test, container, false);
        initView();
        app=(MyApplication)getActivity().getApplication();
        return rootView;
    }

    void initView(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

}
