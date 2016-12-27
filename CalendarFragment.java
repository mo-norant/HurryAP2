package be.norant.mo.hurryap2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

//http://www.androidhive.info/2012/01/android-json-parsing-tutorial/

public class CalendarFragment extends Fragment {

    private static String url = "https://content.googleapis.com/calendar/v3/calendars/bfk39bdudavstlsguprkss8frk%40group.calendar.google.com/events?key=AIzaSyCuAT22qhW6cU9gy-NoV9SYFXxeuBr3z0A";

    TextView textView;
    CalendarAdapter calendarAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    JSONObject jsonObject = new JSONObject();
    RequestQueue queue;


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();



        listDataHeader.add("Calendar Reply");

/*
              // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

*/


        List<String> mo = new ArrayList<>();
        mo.add("test");


        listDataChild.put(listDataHeader.get(0), mo); // Header, Child data
  

    }



    private void getJson(){


         JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, jsonObject , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.v("Raw",response.toString());
                jsonObject = response;

            Log.v("JSONOBJECT",jsonObject.toString());

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });

        queue.add(jsonObjReq);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        queue = Volley.newRequestQueue(getContext());
        getJson();
       
        prepareListData();


        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        textView = (TextView) rootView.findViewById(R.id.textView);


        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        calendarAdapter = new CalendarAdapter(getContext(), listDataHeader, listDataChild);

        expListView.setAdapter(calendarAdapter);


        RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);




        return rootView;


    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }


    public interface VolleyCallback {
        void onSuccess(JSONObject result);
    }


}


