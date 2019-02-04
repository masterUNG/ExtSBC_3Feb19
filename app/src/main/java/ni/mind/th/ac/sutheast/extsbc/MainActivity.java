package ni.mind.th.ac.sutheast.extsbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.benzneststudios.eventcalendar2.fragment.CalendarFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarFragment

//        Add Fradment to actvity
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().add(R.id.contentMainFragment, new MainFragment()).commit();
        }


    }   //Main Method

} //Main Class
