package com.ddkmm_000.ratemyclass.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.widget.Button;
import android.widget.Spinner;

import com.ddkmm_000.ratemyclass.CreateClassActivity;
import com.ddkmm_000.ratemyclass.R;

import java.util.ArrayList;

/**
 * Created by ddkmm_000 on 16/02/2015.
 */
public class CreateClassTest extends ActivityInstrumentationTestCase2<CreateClassActivity> {

    private CreateClassActivity activity;

    public CreateClassTest(){
        super(CreateClassActivity.class);
    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
        activity = getActivity();
    }

    public void testInitValues() throws Exception{

    }

    public void testUI() throws Exception{
        Spinner classType = (Spinner)activity.findViewById(R.id.typeSpinner);
        assertEquals((String)classType.getItemAtPosition(0), "Lecture");
        assertEquals((String)classType.getItemAtPosition(1), "Lab");
        Spinner module = (Spinner)activity.findViewById(R.id.moduleSpinner);
        assertNotNull(module);
        Spinner staff = (Spinner)activity.findViewById(R.id.staffSpinner);
        assertNotNull(staff);
        Button btn = (Button)activity.findViewById(R.id.createBtn);
        assertNotNull(btn);
    }

    public void textSend() throws Exception{
        //activity.createClass(activity.getCurrentFocus());

    }
}
