package com.ddkmm_000.ratemyclass.tests;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ddkmm_000.ratemyclass.MainActivity;
import com.ddkmm_000.ratemyclass.R;

/**
 * Created by ddkmm_000 on 16/02/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
        Intent i = new Intent();
        i.setClassName("com.ddkmm_000.ratemyclass", "com.ddkmm_000.ratemyclass.MainActivity");
        i.putExtra("classVar", 0);
        i.putExtra("successVar", 1);
        i.putExtra("classid", 1);
        setActivityIntent(i);
        activity = getActivity();
    }

    public void testUI() throws Exception{
        SeekBar seekBar = (SeekBar)activity.findViewById(R.id.seekBar);
        assertEquals(seekBar.getProgress(), 0);
        seekBar = (SeekBar)activity.findViewById(R.id.seekBarInformative);
        assertEquals(seekBar.getProgress(), 0);
        seekBar = (SeekBar)activity.findViewById(R.id.seekBarInteractive);
        assertEquals(seekBar.getProgress(), 0);
        seekBar = (SeekBar)activity.findViewById(R.id.seekBarIntelligible);
        assertEquals(seekBar.getProgress(), 0);
        seekBar = (SeekBar)activity.findViewById(R.id.seekBarInnovative);
        assertEquals(seekBar.getProgress(), 0);

        Spinner comment = (Spinner)activity.findViewById(R.id.spinner);
        assertTrue(comment.getVisibility() == View.INVISIBLE);
        comment = (Spinner)activity.findViewById(R.id.spinnerInformative);
        assertTrue(comment.getVisibility() == View.INVISIBLE);
        comment = (Spinner)activity.findViewById(R.id.spinnerInteractive);
        assertTrue(comment.getVisibility() == View.INVISIBLE);
        comment = (Spinner)activity.findViewById(R.id.spinnerIntelligible);
        assertTrue(comment.getVisibility() == View.INVISIBLE);
        comment = (Spinner)activity.findViewById(R.id.spinnerInnovative);
        assertTrue(comment.getVisibility() == View.INVISIBLE);

        Button rmv = (Button)activity.findViewById(R.id.rmvBtnInteresting);
        assertTrue(rmv.getVisibility() == View.GONE);
        rmv = (Button)activity.findViewById(R.id.rmvBtnInteractive);
        assertTrue(rmv.getVisibility() == View.GONE);
        rmv = (Button)activity.findViewById(R.id.rmvBtnInformative);
        assertTrue(rmv.getVisibility() == View.GONE);
        rmv = (Button)activity.findViewById(R.id.rmvBtnIntelligible);
        assertTrue(rmv.getVisibility() == View.GONE);
        rmv = (Button)activity.findViewById(R.id.rmvBtnInnovative);
        assertTrue(rmv.getVisibility() == View.GONE);

        Button add = (Button)activity.findViewById(R.id.addBtnInteresting);
        assertTrue(add.getVisibility() == View.VISIBLE);
        add = (Button)activity.findViewById(R.id.addBtnIntelligible);
        assertTrue(add.getVisibility() == View.VISIBLE);
        add = (Button)activity.findViewById(R.id.addBtnInformative);
        assertTrue(add.getVisibility() == View.VISIBLE);
        add = (Button)activity.findViewById(R.id.addBtnInteractive);
        assertTrue(add.getVisibility() == View.VISIBLE);
        add = (Button)activity.findViewById(R.id.addBtnInnovative);
        assertTrue(add.getVisibility() == View.VISIBLE);

        final Button rmv2 = rmv;
        final Button add2 = add;
        final Spinner comment2 = comment;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.addInnovativeComment(activity.getCurrentFocus());
                assertTrue(rmv2.getVisibility() == View.VISIBLE);
                assertTrue(comment2.getVisibility() == View.VISIBLE);
                assertTrue(add2.getVisibility() == View.GONE);
            }
        });

    }
}
