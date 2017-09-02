package com.example.gustavo.airsync;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by Gustavo on 10/02/2017.
 */

public class listaDispositivos extends ListActivity {

    private BluetoothAdapter mBluetoothAdapter2 =  null;
    static String ENDERECO_MEC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        mBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> dispositivosPareados = mBluetoothAdapter2.getBondedDevices();

        if(dispositivosPareados.size()>0){
            for(BluetoothDevice dispositivos : dispositivosPareados){
                String nameBt = dispositivos.getName();
                String macBt = dispositivos.getAddress();
                ArrayBluetooth.add(nameBt + "\n" + macBt);
            }

        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView)v).getText().toString();
        String enderecoMac = informacaoGeral.substring(informacaoGeral.length() - 17);

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MEC, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();

    }
}
