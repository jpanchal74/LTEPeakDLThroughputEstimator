package com.example.jigneshpanchal.ltepeakdlthroughputestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    static final int REQUEST_BAND_CONFIGURATION = 1;

    static final String CatList[]={"0","1","3","4","6","9","12","16","18","19","20"};

    private int b2sectortx=4, b2devicerx=4, b2mod=256, b2bandwidth=0, b2cont=1, b2dlratio=100, b2cc=0, b2nl=0, b2tp=0;
    private int b5sectortx=4, b5devicerx=2, b5mod=256, b5bandwidth=0, b5cont=1, b5dlratio=100, b5cc=0, b5nl=0, b5tp=0;
    private int b13sectortx=4, b13devicerx=2, b13mod=256, b13bandwidth=0, b13cont=1, b13dlratio=100, b13cc=0, b13nl=0, b13tp=0;
    private int b46sectortx=2, b46devicerx=2, b46mod=256, b46bandwidth=0, b46cont=1, b46dlratio=100, b46cc=0, b46nl=0, b46tp=0;
    private int b48sectortx=4, b48devicerx=4, b48mod=256, b48bandwidth=0, b48cont=1, b48dlratio=75, b48cc=0, b48nl=0, b48tp=0;
    private int b66sectortx=4, b66devicerx=4, b66mod=256, b66bandwidth=0, b66cont=1, b66dlratio=100, b66cc=0, b66nl=0, b66tp=0;
    private String b2str="", b5str="", b13str="", b46str="", b48str="", b66str="";
    private TextView b2text, b5text, b13text, b46text, b48text, b66text, nltext, cctext, bctext, tputtext, mincattext;
    private Spinner dynamicSpinner;
    private ArrayAdapter<String> adapter;

    private int numlayer=0, numcc=0, totaltput=0, mincat=-1;
    private RequiredMinimumDeviceCategory reqmincat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        reqmincat = new RequiredMinimumDeviceCategory();

        // Find the View that shows the numbers category
        b2text = (TextView) findViewById(R.id.b2configtext);
        b2text.setText(generateBandText(2));
        LinearLayout b2 = (LinearLayout) findViewById(R.id.b2);
        // Set a click listener on numbers TextView
        b2.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent b2Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b2Intent.putExtra("band",2);
                b2Intent.putExtra("sectortx", b2sectortx );
                b2Intent.putExtra("devicerx", b2devicerx );
                b2Intent.putExtra("mod", b2mod );
                b2Intent.putExtra("bandwidth", b2bandwidth );
                b2Intent.putExtra("cont", b2cont );
                b2Intent.putExtra("cc", b2cc );
                b2Intent.putExtra("ccltr", b2str);
                b2Intent.putExtra("dlratio", b2dlratio);
                b2Intent.putExtra("nl", b2nl );
                b2Intent.putExtra("tp", b2tp );
                startActivityForResult(b2Intent,REQUEST_BAND_CONFIGURATION);
            }
        });

        // Find the View that shows the colors category
        b5text = (TextView) findViewById(R.id.b5configtext);
        b5text.setText(generateBandText(5));
        LinearLayout b5 = (LinearLayout) findViewById(R.id.b5);
        // Set a click listener on colors TextView
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b5Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b5Intent.putExtra("band",5);
                b5Intent.putExtra("sectortx", b5sectortx );
                b5Intent.putExtra("devicerx", b5devicerx );
                b5Intent.putExtra("mod", b5mod );
                b5Intent.putExtra("bandwidth", b5bandwidth );
                b5Intent.putExtra("cont", b5cont );
                b5Intent.putExtra("cc", b5cc );
                b5Intent.putExtra("ccltr", b5str);
                b5Intent.putExtra("dlratio", b5dlratio);
                b5Intent.putExtra("nl", b5nl );
                b5Intent.putExtra("tp", b5tp );
                startActivityForResult(b5Intent,REQUEST_BAND_CONFIGURATION);
            }
        });

        // Find the View that shows family members
        b13text = (TextView) findViewById(R.id.b13configtext);
        b13text.setText(generateBandText(13));
        LinearLayout b13 = (LinearLayout) findViewById(R.id.b13);
        // Set a click listener on family members TextView
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b13Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b13Intent.putExtra("band",13);
                b13Intent.putExtra("sectortx", b13sectortx );
                b13Intent.putExtra("devicerx", b13devicerx );
                b13Intent.putExtra("mod", b13mod );
                b13Intent.putExtra("bandwidth", b13bandwidth );
                b13Intent.putExtra("cont", b13cont );
                b13Intent.putExtra("cc", b13cc );
                b13Intent.putExtra("ccltr", b13str);
                b13Intent.putExtra("dlratio", b13dlratio);
                b13Intent.putExtra("nl", b13nl );
                b13Intent.putExtra("tp", b13tp );
                startActivityForResult(b13Intent,REQUEST_BAND_CONFIGURATION);
            }
        });

        // Find the View that shows phrases
        b46text = (TextView) findViewById(R.id.b46configtext);
        b46text.setText(generateBandText(46));
        LinearLayout b46 = (LinearLayout) findViewById(R.id.b46);
        // Set a click listener on phrases TextView
        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b46Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b46Intent.putExtra("band",46);
                b46Intent.putExtra("sectortx", b46sectortx );
                b46Intent.putExtra("devicerx", b46devicerx );
                b46Intent.putExtra("mod", b46mod );
                b46Intent.putExtra("bandwidth", b46bandwidth );
                b46Intent.putExtra("cont", b46cont );
                b46Intent.putExtra("cc", b46cc );
                b46Intent.putExtra("ccltr", b46str);
                b46Intent.putExtra("dlratio", b46dlratio);
                b46Intent.putExtra("nl", b46nl );
                b46Intent.putExtra("tp", b46tp );
                startActivityForResult(b46Intent,REQUEST_BAND_CONFIGURATION);
            }
        });


        // Find the View that shows phrases
        b48text = (TextView) findViewById(R.id.b48configtext);
        b48text.setText(generateBandText(48));
        LinearLayout b48 = (LinearLayout) findViewById(R.id.b48);
        // Set a click listener on phrases TextView
        b48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b48Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b48Intent.putExtra("band",48);
                b48Intent.putExtra("sectortx", b48sectortx );
                b48Intent.putExtra("devicerx", b48devicerx );
                b48Intent.putExtra("mod", b48mod );
                b48Intent.putExtra("bandwidth", b48bandwidth );
                b48Intent.putExtra("cont", b48cont );
                b48Intent.putExtra("cc", b48cc );
                b48Intent.putExtra("ccltr", b48str);
                b48Intent.putExtra("dlratio", b48dlratio);
                b48Intent.putExtra("nl", b48nl );
                b48Intent.putExtra("tp", b48tp );
                startActivityForResult(b48Intent,REQUEST_BAND_CONFIGURATION);
            }
        });

        // Find the View that shows phrases
        b66text = (TextView) findViewById(R.id.b66configtext);
        b66text.setText(generateBandText(66));
        LinearLayout b66 = (LinearLayout) findViewById(R.id.b66);
        // Set a click listener on phrases TextView
        b66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b66Intent = new Intent(MainActivity.this, BandConfigurationActivity.class);
                b66Intent.putExtra("band",66);
                b66Intent.putExtra("sectortx", b66sectortx );
                b66Intent.putExtra("devicerx", b66devicerx );
                b66Intent.putExtra("mod", b66mod );
                b66Intent.putExtra("bandwidth", b66bandwidth );
                b66Intent.putExtra("cont", b66cont );
                b66Intent.putExtra("cc", b66cc );
                b66Intent.putExtra("dlratio", b66dlratio);
                b66Intent.putExtra("ccltr", b66str);
                b66Intent.putExtra("nl", b66nl );
                b66Intent.putExtra("tp", b66tp );
                startActivityForResult(b66Intent,REQUEST_BAND_CONFIGURATION);
            }
        });

        // Find the View for computational texts
        nltext = (TextView) findViewById(R.id.NL);
        cctext = (TextView) findViewById(R.id.CC);
        bctext = (TextView) findViewById(R.id.BandCombo);
        tputtext = (TextView) findViewById(R.id.tput);


        dynamicSpinner = (Spinner) findViewById(R.id.mincatspinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CatList);
        dynamicSpinner.setAdapter(adapter);

        if (mincat >=0) {
            int spinnerPosition = adapter.getPosition(mincat + "");
            dynamicSpinner.setSelection(spinnerPosition);
        }
        updateTotalCalculation(false);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("item", (String) parent.getItemAtPosition(position));
                mincat=Integer.valueOf((String)parent.getItemAtPosition(position));
                totaltput = generateTotalDLThroughput();
                totaltput=Math.min(totaltput,reqmincat.DeviceCategoryDLThroughput(mincat));
                if(totaltput == 0) {
                    Toast.makeText(getApplicationContext(), "Category " + mincat +" not Valid,\nSelect another cateogry", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Displaying Toast with message
                    Toast.makeText(getApplicationContext(), "New Category " + mincat + " Configured", Toast.LENGTH_SHORT).show();
                }
                //updateTotalCalculation(true);
                tputtext.setText((totaltput/1000) + " Mbps");
                //int spinnerPosition = adapter.getPosition(mincat+"");
                //dynamicSpinner.setSelection(spinnerPosition);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //bandwidth=0;
                //updateTotalCalculation(false);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("b2sectortx", b2sectortx );
        outState.putInt("b2devicerx", b2devicerx );
        outState.putInt("b2mod", b2mod );
        outState.putInt("b2bandwidth", b2bandwidth );
        outState.putInt("b2cont", b2cont );
        outState.putInt("b2cc", b2cc );
        outState.putString("b2str", b2str);
        outState.putInt("b2dlratio", b2dlratio);
        outState.putInt("b2nl", b2nl );
        outState.putInt("b2tp", b2tp );

        outState.putInt("b5sectortx", b5sectortx );
        outState.putInt("b5devicerx", b5devicerx );
        outState.putInt("b5mod", b5mod );
        outState.putInt("b5bandwidth", b5bandwidth );
        outState.putInt("b5cont", b5cont );
        outState.putInt("b5cc", b5cc );
        outState.putString("b5str", b5str);
        outState.putInt("b5dlratio", b5dlratio);
        outState.putInt("b5nl", b5nl );
        outState.putInt("b5tp", b5tp );

        outState.putInt("b13sectortx", b13sectortx );
        outState.putInt("b13devicerx", b13devicerx );
        outState.putInt("b13mod", b13mod );
        outState.putInt("b13bandwidth", b13bandwidth );
        outState.putInt("b13cont", b13cont );
        outState.putInt("b13cc", b13cc );
        outState.putString("b13str", b13str);
        outState.putInt("b13dlratio", b13dlratio);
        outState.putInt("b13nl", b13nl );
        outState.putInt("b13tp", b13tp );


        outState.putInt("b46sectortx", b46sectortx );
        outState.putInt("b46devicerx", b46devicerx );
        outState.putInt("b46mod", b46mod );
        outState.putInt("b46bandwidth", b46bandwidth );
        outState.putInt("b46cont", b46cont );
        outState.putInt("b46cc", b46cc );
        outState.putString("b46str", b46str);
        outState.putInt("b46dlratio", b46dlratio);
        outState.putInt("b46nl", b46nl );
        outState.putInt("b46tp", b46tp );


        outState.putInt("b48sectortx", b48sectortx );
        outState.putInt("b48devicerx", b48devicerx );
        outState.putInt("b48mod", b48mod );
        outState.putInt("b48bandwidth", b48bandwidth );
        outState.putInt("b48cont", b48cont );
        outState.putInt("b48cc", b48cc );
        outState.putString("b48str", b48str);
        outState.putInt("b48dlratio", b48dlratio);
        outState.putInt("b48nl", b48nl );
        outState.putInt("b48tp", b48tp );


        outState.putInt("b66sectortx", b66sectortx );
        outState.putInt("b66devicerx", b66devicerx );
        outState.putInt("b66mod", b66mod );
        outState.putInt("b66bandwidth", b66bandwidth );
        outState.putInt("b66cont", b66cont );
        outState.putInt("b66cc", b66cc );
        outState.putString("b66str", b66str);
        outState.putInt("b66dlratio", b66dlratio);
        outState.putInt("b66nl", b66nl );
        outState.putInt("b66tp", b66tp );

        outState.putInt("mincat", mincat );
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        b2sectortx=savedInstanceState.getInt("b2sectortx");
        b2devicerx=savedInstanceState.getInt("b2devicerx");
        b2mod=savedInstanceState.getInt("b2mod");
        b2bandwidth=savedInstanceState.getInt("b2bandwidth");
        b2cont=savedInstanceState.getInt("b2cont");
        b2cc=savedInstanceState.getInt("b2cc");
        b2str=savedInstanceState.getString("b2str");
        b2dlratio=savedInstanceState.getInt("b2dlratio");
        b2nl=savedInstanceState.getInt("b2nl");
        b2tp=savedInstanceState.getInt("b2tp");
        b2text.setText(generateBandText(2));

        b5sectortx=savedInstanceState.getInt("b5sectortx");
        b5devicerx=savedInstanceState.getInt("b5devicerx");
        b5mod=savedInstanceState.getInt("b5mod");
        b5bandwidth=savedInstanceState.getInt("b5bandwidth");
        b5cont=savedInstanceState.getInt("b5cont");
        b5cc=savedInstanceState.getInt("b5cc");
        b5str=savedInstanceState.getString("b5str");
        b5dlratio=savedInstanceState.getInt("b5dlratio");
        b5nl=savedInstanceState.getInt("b5nl");
        b5tp=savedInstanceState.getInt("b5tp");
        b5text.setText(generateBandText(5));

        b13sectortx=savedInstanceState.getInt("b13sectortx");
        b13devicerx=savedInstanceState.getInt("b13devicerx");
        b13mod=savedInstanceState.getInt("b13mod");
        b13bandwidth=savedInstanceState.getInt("b13bandwidth");
        b13cont=savedInstanceState.getInt("b13cont");
        b13cc=savedInstanceState.getInt("b13cc");
        b13str=savedInstanceState.getString("b13str");
        b13dlratio=savedInstanceState.getInt("b13dlratio");
        b13nl=savedInstanceState.getInt("b13nl");
        b13tp=savedInstanceState.getInt("b13tp");
        b13text.setText(generateBandText(13));

        b46sectortx=savedInstanceState.getInt("b46sectortx");
        b46devicerx=savedInstanceState.getInt("b46devicerx");
        b46mod=savedInstanceState.getInt("b46mod");
        b46bandwidth=savedInstanceState.getInt("b46bandwidth");
        b46cont=savedInstanceState.getInt("b46cont");
        b46cc=savedInstanceState.getInt("b46cc");
        b46str=savedInstanceState.getString("b46str");
        b46dlratio=savedInstanceState.getInt("b46dlratio");
        b46nl=savedInstanceState.getInt("b46nl");
        b46tp=savedInstanceState.getInt("b46tp");
        b46text.setText(generateBandText(46));

        b48sectortx=savedInstanceState.getInt("b48sectortx");
        b48devicerx=savedInstanceState.getInt("b48devicerx");
        b48mod=savedInstanceState.getInt("b48mod");
        b48bandwidth=savedInstanceState.getInt("b48bandwidth");
        b48cont=savedInstanceState.getInt("b48cont");
        b48cc=savedInstanceState.getInt("b48cc");
        b48str=savedInstanceState.getString("b48str");
        b48dlratio=savedInstanceState.getInt("b48dlratio");
        b48nl=savedInstanceState.getInt("b48nl");
        b48tp=savedInstanceState.getInt("b48tp");
        b48text.setText(generateBandText(48));

        b66sectortx=savedInstanceState.getInt("b66sectortx");
        b66devicerx=savedInstanceState.getInt("b66devicerx");
        b66mod=savedInstanceState.getInt("b66mod");
        b66bandwidth=savedInstanceState.getInt("b66bandwidth");
        b66cont=savedInstanceState.getInt("b66cont");
        b66cc=savedInstanceState.getInt("b66cc");
        b66str=savedInstanceState.getString("b66str");
        b66dlratio=savedInstanceState.getInt("b66dlratio");
        b66nl=savedInstanceState.getInt("b66nl");
        b66tp=savedInstanceState.getInt("b66tp");
        b66text.setText(generateBandText(66));

        mincat=savedInstanceState.getInt("mincat");

        updateTotalCalculation(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BAND_CONFIGURATION & resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            int band = data.getExtras().getInt("band");

            switch (band) {
                case 2:
                    b2sectortx = data.getExtras().getInt("sectortx");
                    b2devicerx = data.getExtras().getInt("devicerx");
                    b2mod = data.getExtras().getInt("mod");
                    b2bandwidth = data.getExtras().getInt("bandwidth");
                    b2cont = data.getExtras().getInt("cont");
                    b2cc = data.getExtras().getInt("cc");
                    b2str = data.getExtras().getString("ccltr");
                    b2dlratio = data.getExtras().getInt("dlratio");
                    b2nl = data.getExtras().getInt("nl");
                    b2tp = data.getExtras().getInt("tp");
                    b2text.setText(generateBandText(band));
                    if(b2tp == 0){
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 5:
                    b5sectortx = data.getExtras().getInt("sectortx");
                    b5devicerx = data.getExtras().getInt("devicerx");
                    b5mod = data.getExtras().getInt("mod");
                    b5bandwidth = data.getExtras().getInt("bandwidth");
                    b5cont = data.getExtras().getInt("cont");
                    b5cc = data.getExtras().getInt("cc");
                    b5str = data.getExtras().getString("ccltr");
                    b5dlratio = data.getExtras().getInt("dlratio");
                    b5nl = data.getExtras().getInt("nl");
                    b5tp = data.getExtras().getInt("tp");
                    b5text.setText(generateBandText(band));
                    if(b5tp == 0) {
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 13:
                    b13sectortx = data.getExtras().getInt("sectortx");
                    b13devicerx = data.getExtras().getInt("devicerx");
                    b13mod = data.getExtras().getInt("mod");
                    b13bandwidth = data.getExtras().getInt("bandwidth");
                    b13cont = data.getExtras().getInt("cont");
                    b13cc = data.getExtras().getInt("cc");
                    b13str = data.getExtras().getString("ccltr");
                    b13dlratio = data.getExtras().getInt("dlratio");
                    b13nl = data.getExtras().getInt("nl");
                    b13tp = data.getExtras().getInt("tp");
                    b13text.setText(generateBandText(band));
                    if(b13tp == 0) {
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 46:
                    b46sectortx = data.getExtras().getInt("sectortx");
                    b46devicerx = data.getExtras().getInt("devicerx");
                    b46mod = data.getExtras().getInt("mod");
                    b46bandwidth = data.getExtras().getInt("bandwidth");
                    b46cont = data.getExtras().getInt("cont");
                    b46cc = data.getExtras().getInt("cc");
                    b46str = data.getExtras().getString("ccltr");
                    b46dlratio = data.getExtras().getInt("dlratio");
                    b46nl = data.getExtras().getInt("nl");
                    b46tp = data.getExtras().getInt("tp");
                    b46text.setText(generateBandText(band));
                    if(b46tp == 0) {
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 48:
                    b48sectortx = data.getExtras().getInt("sectortx");
                    b48devicerx = data.getExtras().getInt("devicerx");
                    b48mod = data.getExtras().getInt("mod");
                    b48bandwidth = data.getExtras().getInt("bandwidth");
                    b48cont = data.getExtras().getInt("cont");
                    b48cc = data.getExtras().getInt("cc");
                    b48str = data.getExtras().getString("ccltr");
                    b48dlratio = data.getExtras().getInt("dlratio");
                    b48nl = data.getExtras().getInt("nl");
                    b48tp = data.getExtras().getInt("tp");
                    b48text.setText(generateBandText(band));
                    if(b48tp == 0) {
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 66:
                    b66sectortx = data.getExtras().getInt("sectortx");
                    b66devicerx = data.getExtras().getInt("devicerx");
                    b66mod = data.getExtras().getInt("mod");
                    b66bandwidth = data.getExtras().getInt("bandwidth");
                    b66cont = data.getExtras().getInt("cont");
                    b66cc = data.getExtras().getInt("cc");
                    b66str = data.getExtras().getString("ccltr");
                    b66dlratio = data.getExtras().getInt("dlratio");
                    b66nl = data.getExtras().getInt("nl");
                    b66tp = data.getExtras().getInt("tp");
                    b66text.setText(generateBandText(band));
                    if(b66tp == 0) {
                        Toast.makeText(getApplicationContext(), "Band " + band +" bandwidth = 0 MHz,\nGo back and select bandwidth", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Displaying Toast with message
                        Toast.makeText(getApplicationContext(), "Band " + band + " Configured", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            updateTotalCalculation(true);
        }
    }

    public void updateTotalCalculation(boolean updateMinCat){
        numlayer = generateNumberOfLayers();
        numcc = generateNumberOfCC();
        totaltput = generateTotalDLThroughput();
        if ( totaltput > 0) {
            // Find the View for computational texts
            nltext.setText("" + numlayer);
            cctext.setText(numcc + "CC");
            bctext.setText(generateBandComboString());

            if ( (updateMinCat) | (mincat < 0) ){
                mincat = reqmincat.findMinDeviceCategory(totaltput);
                totaltput = Math.min(totaltput, reqmincat.DeviceCategoryDLThroughput(mincat));
            }

            tputtext.setText((totaltput/1000) + " Mbps");
            int spinnerPosition = adapter.getPosition(mincat+"");
            dynamicSpinner.setSelection(spinnerPosition);

        } else{
            // Find the View for computational texts
            nltext.setText(""+0);
            cctext.setText(0+"CC");
            bctext.setText("");
            tputtext.setText(0 + " Mbps");

            //mincat=0;
            //int spinnerPosition = adapter.getPosition(mincat+"");
            //dynamicSpinner.setSelection(spinnerPosition);

            //Displaying Toast with message
            Toast.makeText(getApplicationContext(), "Select Band to be configured", Toast.LENGTH_SHORT).show();
        }
    }

    public String generateBandText(int b) {
        String str="";
        switch (b) {
            case 2:
                if (b2tp != 0){
                    //if ( (b2bandwidth != 0) && (b2tp != 0) ) {
                    if ((b2sectortx == 4) & (b2devicerx == 4)) {
                        str = "4x4,";
                    } else if (b2sectortx == 4) {
                        str = "4x2,";
                    } else if (b2devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b2mod == 256) {
                        str = str + "256QAM,";
                    }else if (b2mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b2cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b2bandwidth + "MHz\n#CC=" +  b2cc +","+b2str+",#L=" + b2nl + ",TP=" + (int)((float)b2tp/1000) + "Mbps";
                }
                break;

            case 5:
                if (b5tp != 0){
                    //if ( (b5bandwidth != 0) && (b5tp != 0) ) {
                    if ((b5sectortx == 4) & (b5devicerx == 4)) {
                        str = "4x4,";
                    } else if (b5sectortx == 4) {
                        str = "4x2,";
                    } else if (b5devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b5mod == 256) {
                        str = str + "256QAM,";
                    }else if (b5mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b5cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b5bandwidth + "MHz\n#CC=" +  b5cc +","+b5str+",#L=" + b5nl + ",TP=" + (int)((float)b5tp/1000) + "Mbps";
                }
                break;

            case 13:
                if (b13tp != 0){
                    //if ( (b13bandwidth != 0) && (b13tp != 0) ) {
                    if ((b13sectortx == 4) & (b13devicerx == 4)) {
                        str = "4x4,";
                    } else if (b13sectortx == 4) {
                        str = "4x2,";
                    } else if (b13devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b13mod == 256) {
                        str = str + "256QAM,";
                    }else if (b13mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b13cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b13bandwidth + "MHz\n#CC=" +  b13cc +","+b13str+ ",#L=" + b13nl + ",TP=" + (int)((float)b13tp/1000) + "Mbps";
                }
                break;

            case 46:
                if (b46tp != 0){
                    //if ( (b46bandwidth != 0) && (b46tp != 0) ) {
                    if ((b46sectortx == 4) & (b46devicerx == 4)) {
                        str = "4x4,";
                    } else if (b46sectortx == 4) {
                        str = "4x2,";
                    } else if (b46devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b46mod == 256) {
                        str = str + "256QAM,";
                    }else if (b46mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b46cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b46bandwidth + "MHz\n#CC=" +  b46cc +","+b46str+ ",#L=" + b46nl + ",TP=" + (int)((float)b46tp/1000) + "Mbps";
                }
                break;

            case 48:
                if (b48tp != 0){
                    //if ( (b48bandwidth != 0) && (b48tp != 0) ) {
                    if ((b48sectortx == 4) & (b48devicerx == 4)) {
                        str = "4x4,";
                    } else if (b48sectortx == 4) {
                        str = "4x2,";
                    } else if (b48devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b48mod == 256) {
                        str = str + "256QAM,";
                    }else if (b48mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b48cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b48bandwidth + "MHz,R=" + b48dlratio + "%\n#CC=" +  b48cc +","+b48str+ ",#L=" + b48nl + ",TP=" + (int)((float)b48tp/1000) + "Mbps";
                }
                break;

            case 66:
                if (b66tp != 0){
                    //if ( (b66bandwidth != 0) && (b66tp != 0) ) {
                    if ((b66sectortx == 4) & (b66devicerx == 4)) {
                        str = "4x4,";
                    } else if (b66sectortx == 4) {
                        str = "4x2,";
                    } else if (b66devicerx == 4) {
                        str = "2x4,";
                    } else {
                        str = "2x2,";
                    }
                    if (b66mod == 256) {
                        str = str + "256QAM,";
                    }else if (b66mod == 1024) {
                        str = str + "1024QAM,";
                    } else {
                        str = str + "64QAM,";
                    }
                    if (b66cont == 1) {
                        str = str + "cont";
                    } else {
                        str = str + "noncont";
                    }
                    str = str + b66bandwidth + "MHz\n#CC=" +  b66cc +","+b66str+ ",#L=" + b66nl + ",TP=" + (int)((float)b66tp/1000) + "Mbps";
                }
                break;
        }
        return str;
    }

    public String generateBandComboString() {
        String str = "";
        if (b2tp > 0) {
            str=str+b2str+"-";
        }
        if (b5tp != 0) {
            str=str+b5str+"-";
        }
        if (b13tp != 0) {
            str=str+b13str+"-";
        }
        if (b46tp != 0) {
            str=str+b46str+"-";
        }
        if (b48tp != 0) {
            str=str+b48str+"-";
        }
        if (b66tp != 0) {
            str=str+b66str+"-";
        }
        return str;
    }

    public int generateNumberOfLayers() {
        int nl=0;
        if (b2tp > 0) {
            nl += b2nl;
        }
        if (b5tp != 0) {
            nl += b5nl;
        }
        if (b13tp != 0) {
            nl += b13nl;
        }
        if (b46tp != 0) {
            nl += b46nl;
        }
        if (b48tp != 0) {
            nl += b48nl;
        }
        if (b66tp != 0) {
            nl += b66nl;
        }
        return nl;
    }

    public int generateNumberOfCC() {
        int nl=0;
        if (b2tp > 0) {
            nl += b2cc;
        }
        if (b5tp != 0) {
            nl += b5cc;
        }
        if (b13tp != 0) {
            nl += b13cc;
        }
        if (b46tp != 0) {
            nl += b46cc;
        }
        if (b48tp != 0) {
            nl += b48cc;
        }
        if (b66tp != 0) {
            nl += b66cc;
        }
        return nl;
    }

    public int generateTotalDLThroughput() {
        int nl=0;
        if (b2bandwidth > 0) {
            nl += b2tp;
        }
        if (b5bandwidth != 0) {
            nl += b5tp;
        }
        if (b13bandwidth != 0) {
            nl += b13tp;
        }
        if (b46bandwidth != 0) {
            nl += b46tp;
        }
        if (b48bandwidth != 0) {
            nl += b48tp;
        }
        if (b66bandwidth != 0) {
            nl += b66tp;
        }
        return nl;
    }

}
