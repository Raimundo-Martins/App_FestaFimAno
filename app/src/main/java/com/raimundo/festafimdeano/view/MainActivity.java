package com.raimundo.festafimdeano.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raimundo.festafimdeano.R;
import com.raimundo.festafimdeano.constant.FimDeAnoConstants;
import com.raimundo.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDiasRestantes = findViewById(R.id.text_dias_restantes);
        this.mViewHolder.buttonConfirmar = findViewById(R.id.button_confirmar);

        this.mViewHolder.buttonConfirmar.setOnClickListener(this);

        this.mViewHolder.textToday.setText(dateFormat.format(Calendar.getInstance().getTime()));
        String diasRestante = String.format("%s %s", String.valueOf(this.getDiaRestante()), getString(R.string.dias));
        this.mViewHolder.textDiasRestantes.setText(diasRestante);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verificaPresence();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirmar){

            String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    private void verificaPresence(){
        String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);

        if (presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirmar.setText(getString(R.string.sim));
        } else if (presence.equals(FimDeAnoConstants.CONFIRMATION_NO)){
            this.mViewHolder.buttonConfirmar.setText(getString(R.string.nao));
        } else {
            this.mViewHolder.buttonConfirmar.setText(getString(R.string.nao_confirmado));
        }
    }

    private int getDiaRestante(){
        Calendar calendarDia = Calendar.getInstance();
        int dia = calendarDia.get(Calendar.DAY_OF_YEAR);

        Calendar calendarDiaMax = Calendar.getInstance();
        int diaMax = calendarDiaMax.getActualMaximum(Calendar.DAY_OF_YEAR);

        return (diaMax - dia);
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDiasRestantes;
        Button buttonConfirmar;
    }
}
