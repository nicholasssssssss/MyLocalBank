package com.example.mylocalbanks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView myLocalBank;
    TextView uob;
    TextView ocbc;
    TextView dbs;
    ToggleButton tblan;

    String wordClicked="";
    boolean isEnglish = true; // initial language is english

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocalBank=findViewById(R.id.textViewName);
        dbs=findViewById(R.id.tvDBS);
        ocbc=findViewById(R.id.tvOCBC);
        uob=findViewById(R.id.tvUOB);
        tblan=findViewById(R.id.tbLanguage);

        registerForContextMenu(dbs);
        registerForContextMenu(ocbc);
        registerForContextMenu(uob);

        tblan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchToChinese();
                } else {
                    switchToEnglish();
                }
            }
        });
    }

    private void switchToChinese() {
        dbs.setText("星展银行");
        ocbc.setText("华侨银行");
        uob.setText("大华银行");
        myLocalBank.setText("我的本地银行");
        isEnglish = false;
        Toast.makeText(this, "Language switched to Chinese", Toast.LENGTH_SHORT).show();
    }

    private void switchToEnglish() {
        dbs.setText("DBS");
        ocbc.setText("OCBC");
        uob.setText("UOB");
        myLocalBank.setText("My Local Bank");
        isEnglish = true;
        Toast.makeText(this, "Language switched to English", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"Website");
        menu.add(0,1,1,"Contact The Bank");

        if (v==dbs)
        {
            wordClicked="dbs";
        }else if (v==ocbc)
        {
            wordClicked="ocbc";
        }else if (v==uob)
        {
            wordClicked="uob";
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (wordClicked.equalsIgnoreCase("dbs")) {
            if (item.getItemId()==0) {
                Intent intent=new Intent(Intent. ACTION_VIEW, Uri.parse("https://www.dbs.com.sg"));
                startActivity(intent);
                return true;
            } else if (item.getItemId()==1) {
                Intent intentcall=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+1800111111));
                startActivity(intentcall);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase("ocbc"))
        {
            if (item.getItemId()==0) {
                Intent intent=new Intent(Intent. ACTION_VIEW, Uri.parse("https://www.ocbc.com"));
                startActivity(intent);
                return true;
            } else if (item.getItemId()==1) {
                Intent intentcall=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+1800363333));
                startActivity(intentcall);
                return true;
            }
        }
        else if (wordClicked.equalsIgnoreCase("uob"))
        {
            if (item.getItemId()==0) {
                Intent intent=new Intent(Intent. ACTION_VIEW, Uri.parse("https://www.uob.com.sg"));
                startActivity(intent);
                return true;
            } else if (item.getItemId()==1) {
                Intent intentcall=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+1800222212));
                startActivity(intentcall);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }
}
