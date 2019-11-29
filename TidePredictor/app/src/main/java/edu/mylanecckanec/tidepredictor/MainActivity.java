package edu.mylanecckanec.tidepredictor;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity  implements AdapterView.OnItemClickListener {

    ArrayList<TideItem> tideItems;

    public final String DATE = "date";
    public final String DAY = "day";
    public final String TIME = "time";
    public final String PRED_IN_FT = "PredictionInFeet";
    public final String PRED_IN_CM = "PredictionInCentimeters";
    public final String HIGH_LOW = "highLow";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dal dal = new Dal(this);
        tideItems = dal.parseXmlFile("Florence_2019_tide_predictions.xml");

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for (TideItem t : tideItems)
        {
            HashMap<String,String> map = new HashMap<String, String>();
            map.put(DATE,t.getDate());
            map.put(DAY,t.getDay());
            map.put(TIME, t.getTime());
            map.put(PRED_IN_FT,t.getPredInFt());
            map.put(PRED_IN_CM,t.getPredInCm());
            map.put(HIGH_LOW,t.getHighlow());
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.tide_list_layout,
                new String[]{DATE,DAY,TIME,PRED_IN_FT,PRED_IN_CM,HIGH_LOW},
                new int[] {
                        R.id.dateTextView,
                        R.id.dayTextView,
                        R.id.timeTextView,
                        R.id.predFtTextView,
                        R.id.predCmTextView,
                        R.id.highLowTextView
                } );

        ListView mainListView = getListView();//findViewById(R.id.list);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       // Toast.makeText(this, vocabItems.get(i).getEnglish(), Toast.LENGTH_SHORT).show();
    }
}
