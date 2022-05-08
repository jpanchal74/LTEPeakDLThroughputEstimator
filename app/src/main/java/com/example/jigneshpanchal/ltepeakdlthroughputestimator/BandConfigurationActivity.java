package com.example.jigneshpanchal.ltepeakdlthroughputestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BandConfigurationActivity extends AppCompatActivity {

    private int band, sectortx, devicerx, mod, bandwidth, cont, dlratio, maxdlRatioSeek, cc, nl, tp;
    private boolean enableDLratio = false;
    private String ccltr;
    private String bandNumber;
    private String bandDuplexing;
    private String bandFrequncies;
    private TextView  bn, bd, bf, dlratiotv, cctv, nltv, tptv;
    private RadioGroup  sectorRG, deviceRG, modRG, BWcontRG;
    private SeekBar dlRatioSeekBar;
    private BandConfigurationComputation bcc;

    private String[] bw;
    //private final static String TAG = "BandConfigurationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_configuration);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            band = extras.getInt("band");
            sectortx = extras.getInt("sectortx");
            devicerx = extras.getInt("devicerx");
            mod = extras.getInt("mod");
            bandwidth = extras.getInt("bandwidth");
            cont = extras.getInt("cont");
            cc = extras.getInt("cc");
            ccltr =extras.getString("ccltr");
            dlratio = extras.getInt("dlratio");
            nl = extras.getInt("nl");
            tp = extras.getInt("tp");
            if (band == 48) enableDLratio=true;
            else enableDLratio=false;
            generateBWSpinnerArray(band);
        }

        bcc = new BandConfigurationComputation();


        dlratiotv = (TextView) findViewById(R.id.DLRatio);
        cctv = (TextView) findViewById(R.id.CompCarriers);
        nltv = (TextView) findViewById(R.id.NumOfLayers);
        tptv = (TextView) findViewById(R.id.DLThroughput);

        bn = (TextView) findViewById(R.id.BandConfigInfoText1);
        bn.setText(bandNumber);
        bd = (TextView) findViewById(R.id.BandConfigInfoText2);
        bd.setText(bandDuplexing);
        bf = (TextView) findViewById(R.id.BandConfigInfoText3);
        bf.setText(bandFrequncies);

        sectorRG = (RadioGroup) findViewById(R.id.SectorAntennasRadioGroup);
        if(sectortx == 4){
            sectorRG.check(R.id.sector4Tx);
        }else {
            sectorRG.check(R.id.sector2Tx);
        }
        sectorRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.sector4Tx) sectortx=4;
                else if(checkedId == R.id.sector2Tx) sectortx=2;
                updateCalculation();
            }
        });

        deviceRG = (RadioGroup) findViewById(R.id.DeviceAntennasRadioGroup);
        if(devicerx == 4){
            deviceRG.check(R.id.device4Rx);
        }else {
            deviceRG.check(R.id.device2Rx);
        }
        deviceRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.device4Rx) devicerx=4;
                else if(checkedId == R.id.device2Rx) devicerx=2;
                updateCalculation();
            }
        });

        modRG = (RadioGroup) findViewById(R.id.ModGroup);
        if(mod == 256){
            modRG.check(R.id.QAM256);
        }else if (mod == 1024) {
            modRG.check(R.id.QAM1024);
        }else {
                modRG.check(R.id.QAM64);
        }
        modRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.QAM256) mod=256;
                else if(checkedId == R.id.QAM1024) mod=1024;
                else if(checkedId == R.id.QAM64) mod=64;
                updateCalculation();
            }
        });

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.BandwidthSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bw);
        dynamicSpinner.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(bandwidth+" MHz");
        dynamicSpinner.setSelection(spinnerPosition);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("item", (String) parent.getItemAtPosition(position));
                ChosenBandwidth((String) parent.getItemAtPosition(position));
                updateCalculation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //bandwidth=0;
                updateCalculation();
            }
        });

        BWcontRG = (RadioGroup) findViewById(R.id.BWContinuousRadioGroup);
        if(cont == 0){
            BWcontRG.check(R.id.non_cont);
        }else {
            BWcontRG.check(R.id.cont);
        }
        BWcontRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.non_cont) cont=0;
                else if(checkedId == R.id.cont) cont=1;
                updateCalculation();
            }
        });

        // initiate  views
        dlRatioSeekBar=(SeekBar)findViewById(R.id.DLRatioSeekBar);

        if (enableDLratio) {
            maxdlRatioSeek = dlRatioSeekBar.getMax();
            dlRatioSeekBar.setProgress((int) (dlratio * maxdlRatioSeek/100));
            // perform seek bar change listener event used for getting the progress value
            dlRatioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressChangedValue = 0;

                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressChangedValue = progress;
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar seekBar) {

                    dlratio = (int) (progressChangedValue * 100/maxdlRatioSeek);
                    //Displaying Toast with message
                    Toast.makeText(getApplicationContext(), "DL Ratio:" + dlratio + "%", Toast.LENGTH_SHORT).show();
                    updateCalculation();
                }
            });
        }else
        {
            dlRatioSeekBar.setEnabled(false);
            //dlRatioSeekBar.setVisibility(dlRatioSeekBar.GONE);
        }
        updateCalculation();

        // Find the View that shows done button
        Button done = (Button) findViewById(R.id.done);
        // Set a click listener on done button
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createResultIntentAndFinish();
            }

        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                createResultIntentAndFinish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        // DO NOT ADD this!!! Otherwise BackPressed don't work properly - MainActivity fails to get Band Configuration
        // super.onBackPressed();
        createResultIntentAndFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void createResultIntentAndFinish(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("band", band );
        resultIntent.putExtra("sectortx", sectortx );
        resultIntent.putExtra("devicerx", devicerx );
        resultIntent.putExtra("mod", mod );
        resultIntent.putExtra("bandwidth", bandwidth );
        resultIntent.putExtra("cont", cont );
        resultIntent.putExtra("cc", cc );
        resultIntent.putExtra("ccltr",ccltr);
        resultIntent.putExtra("dlratio", dlratio );
        resultIntent.putExtra("nl", nl );
        resultIntent.putExtra("tp", tp );
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void updateCalculation(){
        if(bandwidth > 0) {
            nl = bcc.NumberOfMIMOLayers(sectortx, devicerx);
            cc = bcc.NumberOfComponentCarriers(band, bandwidth);
            ccltr = bcc.ComponentLetter(band, cc, bandwidth, cont);
            tp = bcc.DLThroughput(band, nl, mod, cc, bandwidth, dlratio);
        } else {
            cc=0;
            ccltr="";
            nl=0;
            tp=0;
            //Displaying Toast with message
            Toast.makeText(getApplicationContext(), "Bandwidth = 0 MHz, Select Bandwidth", Toast.LENGTH_SHORT).show();
        }
        dlratiotv.setText(dlratio+"%");
        cctv.setText(ccltr + " (" + cc + "CC)");
        nl = cc * nl;
        nltv.setText("" + nl);
        tptv.setText((tp / 1000) + " Mbps");
    }

    public void generateBWSpinnerArray(int b) {
        switch (b){
            case 2:
                bw = new String[] { "0 MHz", "5 MHz", "10 MHz", "15 MHz", "20 MHz", "25 MHz", "30 MHz", "35 MHz", "40 MHz", "45 MHz", "50 MHz", "55 MHz", "60 MHz" };
                bandNumber = "Band 2 (PCS)";
                bandDuplexing = "FDD";
                bandFrequncies = "BW:60x60MHz (UL:1850-1910MHz, DL:1930-1990MHz)";
                break;
            case 5:
                bw = new String[] { "0 MHz", "5 MHz", "10 MHz", "15 MHz", "20 MHz" };
                bandNumber = "Band 5 (Cellular)";
                bandDuplexing = "FDD";
                bandFrequncies = "BW:25x25MHz (UL:824-849MHz, DL:869-894MHz)";
                break;
            case 13:
                bw = new String[] { "0 MHz", "10 MHz" };
                bandNumber = "Band 13 (700MHz C)";
                bandDuplexing = "FDD";
                bandFrequncies = "BW:11x11MHz (UL:776-787MHz, DL:746-757MHz)";
                break;
            case 46:
                bw = new String[] { "0 MHz", "20 MHz", "40 MHz", "60 MHz", "80 MHz", "100 MHz" };
                bandNumber = "Band 46 (5GHz)";
                bandDuplexing = "LAA";
                bandFrequncies = "BW:775MHz (DL:5150-5925MHz)";
                break;
            case 48:
                bw = new String[] { "0 MHz", "10 MHz", "20 MHz", "30 MHz", "40 MHz", "50 MHz", "60 MHz", "70 MHz", "80 MHz", "90 MHz", "100 MHz" };
                bandNumber = "Band 48 (3.5GHz CBRS)";
                bandDuplexing = "TDD";
                bandFrequncies = "BW:150MHz (UL/DL:3550-3700MHz)";
                break;
            case 66:
                bandNumber = "Band 66 (AWS1+AWS3)";
                bandDuplexing = "FDD";
                bandFrequncies = "BW:70x90MHz (UL:1710-1780MHz, DL:2110-2180MHz)";
                bw = new String[] { "0 MHz", "5 MHz", "10 MHz", "15 MHz", "20 MHz", "25 MHz", "30 MHz", "35 MHz", "40 MHz", "45 MHz", "50 MHz", "55 MHz", "60 MHz" };
                break;
        }
    }

    public void ChosenBandwidth(String chosenBW) {
        bandwidth = 0;
        switch (chosenBW){
            case "0 MHz":
                bandwidth = 0;
                break;
            case "5 MHz":
                bandwidth = 5;
                break;
            case "10 MHz":
                bandwidth = 10;
                break;
            case "15 MHz":
                bandwidth = 15;
                break;
            case "20 MHz":
                bandwidth = 20;
                break;
            case "25 MHz":
                bandwidth = 25;
                break;
            case "30 MHz":
                bandwidth = 30;
                break;
            case "35 MHz":
                bandwidth = 35;
                break;
            case "40 MHz":
                bandwidth = 40;
                break;
            case "45 MHz":
                bandwidth = 45;
                break;
            case "50 MHz":
                bandwidth = 50;
                break;
            case "55 MHz":
                bandwidth = 55;
                break;
            case "60 MHz":
                bandwidth = 60;
                break;
            case "70 MHz":
                bandwidth = 70;
                break;
            case "80 MHz":
                bandwidth = 80;
                break;
            case "90 MHz":
                bandwidth = 90;
                break;
            case "100 MHz":
                bandwidth = 100;
                break;
        }
    }

}