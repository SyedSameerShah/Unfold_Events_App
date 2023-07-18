package com.sameer.unfoldevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sameer.unfoldevents.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {

    DBHelper DB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        Bundle extras = getIntent().getExtras();

        DB = new DBHelper(this);
        listView = findViewById(R.id.listView);

        ArrayList<String> userBookings = new ArrayList<>();

        List<EventModel> userEventArrayList = DB.readUserEvents(extras.getString("email"));

        for(EventModel bm: userEventArrayList){
            userBookings.add(
                    "Event: " + bm.getEventType().toUpperCase() + '\n' +
                    "Event Date: " + bm.getEventDate() + '\n' +
                    "No of Guests: "+ bm.getGuests() + '\n' +
                    "Venue: "+ bm.getVenue() + '\n' +
                    "Food Type: " + bm.getFood() + '\n' +
                    "Budget: " + bm.getBudget());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.mybooking_list_item, userBookings);

        listView.setAdapter(arrayAdapter);


    }
}