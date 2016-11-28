package com.example.administrator.monitorcompositcallcenter;

import android.app.Activity;
import android.os.Parcelable;
import android.os.Bundle;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

import android.view.View;
import android.widget.Button;
public class MonitorMenu extends Activity{

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    Parcelable state;
    Button [] monitorsview = new Button[4];
    String TypeMonitors []={"Agent","Campaign","Ivr","Queue"};
    int pivot=-1,firstlist=-1;
    boolean tableViewer=true;
    InputStream [] inputStreamfiles=new InputStream[4];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);
        monitorsview[0]=(Button) findViewById(R.id.button5);
        monitorsview[1]=(Button) findViewById(R.id.button6);
        monitorsview[2]=(Button) findViewById(R.id.button7);
        monitorsview[3]=(Button) findViewById(R.id.button8);
        state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);
//        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        inputStreamfiles[0]= getResources().openRawResource(R.raw.agent);
        inputStreamfiles[1]= getResources().openRawResource(R.raw.campaign);
        inputStreamfiles[2]= getResources().openRawResource(R.raw.queue);
        inputStreamfiles[3]= getResources().openRawResource(R.raw.ivrline);
//         listView.removeAllViews();
    }

    public void Agent(View view){
        pivot=0;
        drawMonitor();

    }
    public void Campaign(View view){
        pivot=1;
        drawMonitor();
    }
    public void Queue(View view){
        pivot=2;
        drawMonitor();
    }
    public void IVR(View view){
        pivot=3;
        drawMonitor();
    }

    public void drawMonitor(){
        firstlist++;
        for (int i = 0; i < 4; i++) {
            monitorsview[i].setText(TypeMonitors[i]);
            monitorsview[i].setBackgroundColor(0xffffff00);
        }
        monitorsview[pivot].setText(TypeMonitors[pivot]+" -> Refresh");
        monitorsview[pivot].setBackgroundColor(0xFF00FF00);
        if(tableViewer)
            Table();
        else
            Graph();
    }
    public void Table (){
        CallFile(inputStreamfiles[pivot]);
    }
    public void Graph (){}

    public void CallFile(InputStream inputStream){
        if (firstlist!=0)
            listView.removeAllViews();

        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);
        for(String[] scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }
    }
}
