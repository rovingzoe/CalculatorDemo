package com.marvell.zoezhu.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class Calculator extends Activity {

    GridView mKeyPadGrid;
    KeypadAdapter mAdapter;

    String data=null;
    KeypadButton operator;
    Boolean resetInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mKeyPadGrid = (GridView) findViewById(R.id.grdButtons);
        mAdapter = new KeypadAdapter(this);

        mKeyPadGrid.setAdapter(mAdapter);

        mAdapter.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                KeypadButton keypadButton = (KeypadButton) btn.getTag();
                
                ProcessKeypadInput(keypadButton);
            }
        });
    }

    private void ProcessKeypadInput(KeypadButton keypadButton) {
        TextView tv = (TextView) findViewById(R.id.txtInput);
        String currentText = tv.getText().toString();
        String text = keypadButton.getText().toString();
        String tempResult = null;
        switch (keypadButton)
        {
            case C:
                resetBuffer();
                tv.setText("0");
                break;
            case CALCULATE:
                tempResult = CalResult(data, currentText, operator);
                tv.setText(tempResult);
                resetBuffer();
                break;
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIV:
                tempResult = CalResult(data, currentText, operator);
                tv.setText(tempResult);
                if(tempResult.equals("Error"))
                {
                    resetBuffer();
                }
                else
                {
                    data = tempResult;
                    operator = keypadButton;
                    resetInput = true;
                }
                break;
            default:
                if(resetInput)
                {
                    tv.setText(text);
                    resetInput = false;
                }
                else
                {
                    tv.setText(currentText + text);
                }

                break;
        }
    }

    private void resetBuffer()
    {
        data = null;
        operator = null;
        resetInput = true;
    }
    private String CalResult(String a, String b, KeypadButton keypadButton)
    {
        if (b.equals("Error"))
        {
            return "0";
        }
        if (a == null || keypadButton == null)
        {
            return b;
        }
        int num1 = Str2Int(a);
        int num2 = Str2Int(b);
        int result;
        if (keypadButton == KeypadButton.DIV && Str2Int(b)==0)
        {
            return "Error";
        }
        switch (keypadButton)
        {
            case PLUS:
                result = num1 + num2;
                break;
            case MINUS:
                result = num1 - num2;
                break;
            case MULTIPLY:
                result = num1 * num2;
                break;
            case DIV:
                result = num1 / num2;
                break;
            default:
                result = 0;
        }
        return Int2Str(result);
    }

    private  int Str2Int(String s)
    {
        return Integer.parseInt(s);
    }
    private  String Int2Str(int i)
    {
        return  Integer.toString(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
