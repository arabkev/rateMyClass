package com.ddkmm_000.ratemyclass;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by ddkmm_000 on 28/01/2015.
 */
public class CreateClassActivity extends ActionBarActivity {

    private ProgressDialog pDialog;

    JSONParser jParser = new JSONParser();
    JSONParser2 jParser2 = new JSONParser2();

    ArrayList<String> moduleList;
    ArrayList<String> moduleNames;
    ArrayList<String> staffList;

    //ArrayAdapter<String> adapter;
    HintAdapter adapter;
    ArrayAdapter<String> staffAdapter;
    ArrayAdapter<CharSequence> typeAdapter;

    Spinner spinner;
    Spinner staffSpinner;
    Spinner typeSpinner;

    private static String url_all_modules = "http://ratemyclass.byethost5.com/get_modules.php";
    private static String url_module_staff = "http://ratemyclass.byethost5.com/get_module_staff.php";
    private static String url_staff_details = "http://ratemyclass.byethost5.com/get_staff_details_from_id.php";
    private static String url_create_class = "http://ratemyclass.byethost5.com/create_class.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MODULES = "modules";
    private static final String TAG_MODULE_CODE = "Module_Code";
    private static final String TAG_MODULE_NAME = "Module_Name";

    JSONArray modules = null;
    JSONArray staff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createclass);

        typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        typeAdapter = ArrayAdapter.createFromResource(CreateClassActivity.this, R.array.class_types, R.layout.spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        moduleList = new ArrayList<String>();
        moduleNames = new ArrayList<String>();

        spinner = (Spinner)findViewById(R.id.moduleSpinner);
        //adapter = new ArrayAdapter<String>(CreateClassActivity.this, R.layout.spinner_item, moduleList);
        adapter = new HintAdapter(CreateClassActivity.this, moduleList, R.layout.spinner_item);

        new LoadAllModules().execute();


            //Log.d("MODULES: ", moduleNames.get(0));



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setSelection(adapter.getCount());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                staffList = new ArrayList<String>();

                staffSpinner = (Spinner)findViewById(R.id.staffSpinner);
                //staffAdapter = new HintAdapter(CreateClassActivity.this, staffList, R.layout.spinner_item);
                staffAdapter = new ArrayAdapter<String>(CreateClassActivity.this, R.layout.spinner_item, staffList);



                new LoadModuleStaff(spinner.getSelectedItem().toString().substring(0,7)).execute();

                staffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                staffSpinner.setAdapter(staffAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public void createClass (View view)
    {
        //Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //i.putExtra("successVar", 0);
        //startActivity(i);

        // closing this screen
        //finish();

        Spinner staffSpinner = (Spinner)findViewById(R.id.staffSpinner);

        String m = spinner.getSelectedItem().toString().substring(0,7);
        int s = Integer.parseInt(staffSpinner.getSelectedItem().toString().substring(3, 4));
        new CreateClass(m, s).execute();
    }

    class LoadAllModules extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateClassActivity.this);
            pDialog.setMessage("Loading modules. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(url_all_modules, "GET", params);

            Log.d("All Modules: ", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    modules = json.getJSONArray("modules");

                    for (int i = 0; i < modules.length(); i++) {
                        JSONObject c = modules.getJSONObject(i);

                        Log.d("Module added to list: ", c.getString("Module_Name"));

                        //moduleList.add(c.optString("Module_Code"));
                        //moduleNames.add(c.optString("Module_Name"));
                        String code_name = c.optString("Module_Code") + " - " + c.optString("Module_Name");
                        moduleList.add(code_name);
                    }

                    moduleList.add("Select a module");
                    //moduleNames.add("Select a module");
                } else {
                    // error handling
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            //spinner.setSelection(adapter.getCount());
        }

    }

    class LoadModuleStaff extends AsyncTask<String, String, String> {

        String module;

        public LoadModuleStaff(String string){
            module = string;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateClassActivity.this);
            pDialog.setMessage("Loading staff for the selected module. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("module", module));

            Log.d("JSON PARSER: ", jParser2.json);
            JSONObject json2 = null;
            json2 = jParser2.makeHttpRequest(url_module_staff, "GET", params);
            Log.d("Url: ", url_module_staff);
            Log.d("All staff who teach this module: ", jParser2.json);

            try{
                int success = json2.getInt(TAG_SUCCESS);

                if (success == 1) {
                    staff = json2.getJSONArray("staff");

                    for (int i = 0; i < staff.length(); i++) {
                        JSONObject c = staff.getJSONObject(i);

                        Log.d("Staff member added to list: ", c.getString("Staff_ID"));

                        String stf = "ID:" + c.optString("Staff_ID") + " - " + c.optString("Forename") + " " + c.optString("Surname");


                        //staffList.add(Integer.parseInt(stf));
                        staffList.add(stf);
                    }

                    //staffList.add("Select staff member");
                } else {
                    // error handling
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();

            staffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            staffSpinner.setAdapter(staffAdapter);
        }
    }

    class CreateClass extends AsyncTask<String, String, String> {

        String module;
        int staff;

        public CreateClass(String m, int s){
            module = m;
            staff = s;
            //Log.d("CLASSCREATIONSTUFF", module + ", " + staff);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateClassActivity.this);
            pDialog.setMessage("Creating a new class. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Class_Type", typeSpinner.getSelectedItem().toString()));
            params.add(new BasicNameValuePair("Module_Code", module));
            params.add(new BasicNameValuePair("Staff_ID", Integer.toString(staff)));

            JSONParser3 parser = new JSONParser3();

            JSONObject json = parser.makeHttpRequest(url_create_class, "POST", params);

            Log.d("Create Response", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1){
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("classVar", 1);
                    i.putExtra("successVar", 0);
                    //Log.d("ID", "///" + json.optInt("id") + "///");
                    i.putExtra("classid", json.optInt("id"));
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(getApplicationContext(), CreateClassActivity.class);
                    i.putExtra("successVar", 0);
                    startActivity(i);
                    finish();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }

    public class HintAdapter   extends ArrayAdapter<String> {

        public HintAdapter(Context theContext, List<String> objects, int theLayoutResId) {
            super(theContext, theLayoutResId, objects);
        }

        @Override
        public int getCount() {
            // don't display last item. It is used as hint.
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }





}
