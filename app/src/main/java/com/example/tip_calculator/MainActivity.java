package com.example.tip_calculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private SeekBar seekBar;
    private TextView percentage;
    private Button button;
    private TextView displayTip;
    private TextView displayTotal;
    private int proggress;
    private float enteredValue;
    private float result;
    private AlertDialog.Builder alertDialog;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        percentage=(TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
        displayTip=(TextView)findViewById(R.id.textView3);
        displayTotal=(TextView)findViewById(R.id.textView4);
        button.setOnClickListener(this);
        alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(R.string.alert);
        alertDialog.setIcon(R.drawable.alert);
        alertDialog.setMessage(R.string.message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(getResources().getString(R.string.Okay), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        dialog=alertDialog.create();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage.setVisibility(View.VISIBLE);
                percentage.setText(seekBar.getProgress()+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                proggress=seekBar.getProgress();
            }
        });

    }

    @Override
    public void onClick(View v) {
     calc();
    }
    public void calc()
    {
        if(editText.getText().toString().equals("")) {
           dialog.show();
        }
        else
        {
            enteredValue = Float.parseFloat(editText.getText().toString());
            result=enteredValue*proggress/100;
            displayTip.setVisibility(View.VISIBLE);
            displayTip.setText("Your Tip is $ "+String.valueOf(result));
            displayTotal.setVisibility(View.VISIBLE);
            displayTotal.setText("Your total bill amount is $"+String.valueOf(result+enteredValue));
        }
    }
}
