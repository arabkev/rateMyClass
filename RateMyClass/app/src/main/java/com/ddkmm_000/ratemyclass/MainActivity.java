package com.ddkmm_000.ratemyclass;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    // Progress Dialog
    ProgressDialog pDialog;


    //JSONTokener tokener = new JSONTokener()
    JSONParser parser = new JSONParser();

    final Context context = this;

    int successVal = 0;
    int paramVal = 0;
    int classid = 0;


    // url to create new feedback
    //private static String url_create_feedback = "http://ratemyclass.site50.net/create_feedback.php";
    private static String url_create_feedback = "http://ratemyclass.byethost5.com/create_feedback.php";


    // JSON node names
    private static String TAG_SUCCESS = "success";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Set the green-red colour scheme for the seek bars **/
        final SeekBar mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 33){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));}
                else if (progress <= 66){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekeramber));}
                else {seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekergreen));}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));
        final SeekBar mSeekBar2 = (SeekBar) findViewById(R.id.seekBarInformative);
        mSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 33){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));}
                else if (progress <= 66){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekeramber));}
                else {seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekergreen));}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar2.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));
        final SeekBar mSeekBar3 = (SeekBar) findViewById(R.id.seekBarInnovative);
        mSeekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 33){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));}
                else if (progress <= 66){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekeramber));}
                else {seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekergreen));}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar3.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));
        final SeekBar mSeekBar4 = (SeekBar) findViewById(R.id.seekBarIntelligible);
        mSeekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 33){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));}
                else if (progress <= 66){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekeramber));}
                else {seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekergreen));}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar4.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));
        final SeekBar mSeekBar5 = (SeekBar) findViewById(R.id.seekBarInteractive);
        mSeekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 33){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));}
                else if (progress <= 66){seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekeramber));}
                else {seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekergreen));}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar5.setProgressDrawable(getResources().getDrawable(R.drawable.seeker));


        /** Hide the 'Remove Comment' buttons **/
        Button btn = (Button)findViewById(R.id.rmvBtnInteresting);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInteractive);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInformative);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnIntelligible);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInnovative);
        btn.setVisibility(View.GONE);

        /** Populate the spinners with the pre-defined comments **/
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);
        spinner = (Spinner)findViewById(R.id.spinnerInformative);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);
        spinner = (Spinner)findViewById(R.id.spinnerInnovative);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);
        spinner = (Spinner)findViewById(R.id.spinnerIntelligible);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);
        spinner = (Spinner)findViewById(R.id.spinnerInteractive);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);

        if (getIntent().getExtras().getInt("classVar") == 1){
            final AlertDialog alertDialog;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Success!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Class created successfully")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                        }
                    });

            // create alert dialog
            alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

        if (getIntent().getExtras().getInt("successVar") == 1){
            paramVal = 1;
        }
        else if (getIntent().getExtras().getInt("successVar") == 2){
            paramVal = 2;
        }

        if (getIntent().getExtras().getInt("classid") != 0){
            classid = getIntent().getExtras().getInt("classid");
        }

        if (paramVal == 1) {
            final AlertDialog alertDialog;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Success!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Feedback submitted successfully")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                        }
                    });

            // create alert dialog
            alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else if (paramVal == 2)
        {
            final AlertDialog alertDialog;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Oops!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Feedback could not be submitted, please try again.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                        }
                    });

            // create alert dialog
            alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            return true;
        }
        if (id == R.id.action_newclass){
            startActivity(new Intent(getApplicationContext(), CreateClassActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addInterestingComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Button btn = (Button)findViewById(R.id.addBtnInteresting);
        spinner.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInteresting);
        btn.setVisibility(View.VISIBLE);
        spinner.performClick();
    }

    public void removeInterestingComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Button btn = (Button)findViewById(R.id.rmvBtnInteresting);
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.addBtnInteresting);
        btn.setVisibility(View.VISIBLE);
    }

    public void addInteractiveComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInteractive);
        Button btn = (Button)findViewById(R.id.addBtnInteractive);
        spinner.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInteractive);
        btn.setVisibility(View.VISIBLE);
        spinner.performClick();
    }

    public void removeInteractiveComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInteractive);
        Button btn = (Button)findViewById(R.id.rmvBtnInteractive);
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.addBtnInteractive);
        btn.setVisibility(View.VISIBLE);
    }

    public void addInformativeComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInformative);
        Button btn = (Button)findViewById(R.id.addBtnInformative);
        spinner.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInformative);
        btn.setVisibility(View.VISIBLE);
        spinner.performClick();
    }

    public void removeInformativeComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInformative);
        Button btn = (Button)findViewById(R.id.rmvBtnInformative);
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.addBtnInformative);
        btn.setVisibility(View.VISIBLE);
    }

    public void addIntelligibleComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerIntelligible);
        Button btn = (Button)findViewById(R.id.addBtnIntelligible);
        spinner.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnIntelligible);
        btn.setVisibility(View.VISIBLE);
        spinner.performClick();
    }

    public void removeIntelligibleComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerIntelligible);
        Button btn = (Button)findViewById(R.id.rmvBtnIntelligible);
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.addBtnIntelligible);
        btn.setVisibility(View.VISIBLE);
    }

    public void addInnovativeComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInnovative);
        Button btn = (Button)findViewById(R.id.addBtnInnovative);
        spinner.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.rmvBtnInnovative);
        btn.setVisibility(View.VISIBLE);
        spinner.performClick();
    }

    public void removeInnovativeComment(View view)
    {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInnovative);
        Button btn = (Button)findViewById(R.id.rmvBtnInnovative);
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        btn = (Button)findViewById(R.id.addBtnInnovative);
        btn.setVisibility(View.VISIBLE);
    }

    /** Called when the user clicks the 'Send' button
    public void sendMessage(View view)
    {
        EditText editText = (EditText)findViewById(R.id.edit_message);
        String message = editText.getText().toString() + " Forever...";
        editText.setText(message);
    }**/

    /** Print the value of the seek bar to the text box
    public void getValue(View view)
    {
        EditText editText = (EditText)findViewById(R.id.edit_message);
        SeekBar seeker = (SeekBar)findViewById(R.id.seekBar);
        int value = seeker.getProgress();
        String textVal = String.valueOf(value);
        editText.setText(textVal);
    }**/

    //** Send feedback data to PHP (which will create a row in the database) **/
    public void sendFeedback(View view)
    {
        new CreateNewProduct().execute();
    }


    /**
     * Background Async Task to Create new product
     * Code adapted from tutorial written by Ravi Tamada, androidhive.info
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Sending feedback..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            //String name = inputName.getText().toString();
            //String price = inputPrice.getText().toString();
            //String description = inputDesc.getText().toString();
            //int Class_ID = 1;

            SeekBar seeker = (SeekBar)findViewById(R.id.seekBar);
            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            int Interesting_Rating = seeker.getProgress();
            String Interesting_Comment = spinner.getSelectedItem().toString();

            seeker = (SeekBar)findViewById(R.id.seekBarInformative);
            spinner = (Spinner)findViewById(R.id.spinnerInformative);
            int Informative_Rating = seeker.getProgress();;
            String Informative_Comment = spinner.getSelectedItem().toString();

            seeker = (SeekBar)findViewById(R.id.seekBarInteractive);
            spinner = (Spinner)findViewById(R.id.spinnerInteractive);
            int Interactive_Rating = seeker.getProgress();;
            String Interactive_Comment = spinner.getSelectedItem().toString();

            seeker = (SeekBar)findViewById(R.id.seekBarIntelligible);
            spinner = (Spinner)findViewById(R.id.spinnerIntelligible);
            int Intelligible_Rating = seeker.getProgress();;
            String Intelligible_Comment = spinner.getSelectedItem().toString();

            seeker = (SeekBar)findViewById(R.id.seekBarInnovative);
            spinner = (Spinner)findViewById(R.id.spinnerInnovative);
            int Innovative_Rating = seeker.getProgress();;
            String Innovative_Comment = spinner.getSelectedItem().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("name", name));
            //params.add(new BasicNameValuePair("price", price));
            //params.add(new BasicNameValuePair("description", description));
            params.add(new BasicNameValuePair("Class_ID", Integer.toString(classid)));
            params.add(new BasicNameValuePair("Interesting_Rating", Integer.toString(Interesting_Rating)));
            params.add(new BasicNameValuePair("Interesting_Comment", Interesting_Comment));
            params.add(new BasicNameValuePair("Informative_Rating", Integer.toString(Informative_Rating)));
            params.add(new BasicNameValuePair("Informative_Comment", Informative_Comment));
            params.add(new BasicNameValuePair("Interactive_Rating", Integer.toString(Interactive_Rating)));
            params.add(new BasicNameValuePair("Interactive_Comment", Interactive_Comment));
            params.add(new BasicNameValuePair("Intelligible_Rating", Integer.toString(Intelligible_Rating)));
            params.add(new BasicNameValuePair("Intelligible_Comment", Intelligible_Comment));
            params.add(new BasicNameValuePair("Innovative_Rating", Integer.toString(Innovative_Rating)));
            params.add(new BasicNameValuePair("Innovative_Comment", Innovative_Comment));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = parser.makeHttpRequest(url_create_feedback,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product

                    //dlgAlert.setMessage("Feedback sent successfully");
                    //dlgAlert.setTitle("Success!");
                    //dlgAlert.setPositiveButton("OK", null);
                    //dlgAlert.setCancelable(true);
                    //dlgAlert.create().show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("successVar", 1);
                    i.putExtra("classid", classid);
                    i.putExtra("classVar", 3);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("successVar", 2);
                    i.putExtra("classid", classid);
                    i.putExtra("classVar", 3);
                    startActivity(i);

                    // closing this screen
                    finish();
                    //dlgAlert.setMessage("Feedback could not be sent, please try again");
                    //dlgAlert.setTitle("Error");
                    //dlgAlert.setPositiveButton("OK", null);
                    //dlgAlert.setCancelable(true);
                    //dlgAlert.create().show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

}
