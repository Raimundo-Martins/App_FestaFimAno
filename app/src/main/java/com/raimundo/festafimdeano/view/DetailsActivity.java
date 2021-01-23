package com.raimundo.festafimdeano.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.raimundo.festafimdeano.R;
import com.raimundo.festafimdeano.constant.FimDeAnoConstants;
import com.raimundo.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHorder mViewHorder = new ViewHorder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHorder.checkParticipar = findViewById(R.id.check_participar);
        this.mViewHorder.checkParticipar.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participar){

            if (this.mViewHorder.checkParticipar.isChecked()){
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHorder.checkParticipar.setChecked(true);
            } else {
                this.mViewHorder.checkParticipar.setChecked(false);
            }
        }
    }

    private static class ViewHorder{
        CheckBox checkParticipar;
    }
}
