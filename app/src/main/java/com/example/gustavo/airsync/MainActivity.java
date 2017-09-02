package com.example.gustavo.airsync;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    Button btn_Bluetooth;

    //Frente//
    Button F_allUp;
    Button E_allUp;
    ImageButton F_E_Up;
    Button F_E_Down;
    Button F_D_Up;
    Button F_D_Down;
    Button D_allUp;
    Button F_allDown;
    //Traz//
    Button T_allUp;
    Button E_allDown;
    Button T_E_Up;
    Button T_E_Down;
    Button T_D_Up;
    Button T_D_Down;
    Button D_allDown;
    Button T_allDown;
    //Tudo//
    Button allUp;
    Button allDown;


    BluetoothAdapter mBluetoothAdapter = null;
    BluetoothDevice mBluetoothDevice = null;
    BluetoothSocket mBluetoothSocket = null;
    private ConnectedThread mConnectedThread;
    private static final int SOLICITA_BLUETOOTH = 1;
    private static final int SOLICITA_CONEXAO = 2;
    boolean conexao = false;
    private static String MAC = null;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Bluetooth = (Button) findViewById(R.id.btn_Bluetooth);
        //Frente//
        F_allUp = (Button) findViewById(R.id.F_allUp);
        E_allUp = (Button) findViewById(R.id.E_allUp);
        F_E_Up = (ImageButton) findViewById(R.id.F_E_Up);
        F_E_Down = (Button) findViewById(R.id.F_E_Down);
        F_D_Up = (Button) findViewById(R.id.F_D_Up);
        F_D_Down = (Button) findViewById(R.id.F_D_Down);
        D_allUp = (Button) findViewById(R.id.D_allUp);
        F_allDown = (Button) findViewById(R.id.F_allDown);
        //Tras//
        T_allUp = (Button) findViewById(R.id.T_allUp);
        E_allDown = (Button) findViewById(R.id.E_allDown);
        T_E_Up = (Button) findViewById(R.id.T_E_Up);
        T_E_Down = (Button) findViewById(R.id.T_E_Down);
        T_D_Up = (Button) findViewById(R.id.T_D_Up);
        T_D_Down = (Button) findViewById(R.id.T_D_Down);
        D_allDown = (Button) findViewById(R.id.D_allDown);
        T_allDown = (Button) findViewById(R.id.T_allDown);
        //Tudo//
        allUp = (Button) findViewById(R.id.allUp);
        allDown = (Button) findViewById(R.id.allDown);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplication(), "Seu aparelho não tem bluetooth", Toast.LENGTH_LONG).show();
        } else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBlt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlt, SOLICITA_BLUETOOTH);
        }

        btn_Bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conexao) {
                    //DESCONECTAR
                    try {
                        mBluetoothSocket.close();
                        conexao = false;
                        btn_Bluetooth.setBackground(getResources().getDrawable(R.drawable.bluetooth_off));
                        Toast.makeText(getApplication(), "AirSync DESCONECTADO", Toast.LENGTH_LONG).show();
                    } catch (IOException erro) {
                    }
                } else {
                    //CONECTAR
                    Intent abreLista = new Intent(MainActivity.this, listaDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);
                }
            }
        });

/////// COMANDO FRENTE ////////
            F_allUp.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("1");
                        mConnectedThread.write("2");
                        F_allUp.setBackgroundResource(R.drawable.p_all_up);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        F_allUp.setBackgroundResource(R.drawable.n_all_up);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            F_E_Up.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("1");
                            F_E_Up.setBackgroundResource(R.drawable.p_up);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            F_E_Up.setBackgroundResource(R.drawable.n_up);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            F_D_Up.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("2");
                            F_D_Up.setBackgroundResource(R.drawable.p_up);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            F_D_Up.setBackgroundResource(R.drawable.n_up);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            F_E_Down.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("3");
                            F_E_Down.setBackgroundResource(R.drawable.p_down);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            F_E_Down.setBackgroundResource(R.drawable.n_down);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            F_D_Down.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("4");
                            F_D_Down.setBackgroundResource(R.drawable.p_down);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            F_D_Down.setBackgroundResource(R.drawable.n_down);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            F_allDown.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("3");
                        mConnectedThread.write("4");
                        F_allDown.setBackgroundResource(R.drawable.p_all_down);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        F_allDown.setBackgroundResource(R.drawable.n_all_down);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

/////// COMANDO TRAZ ///////
            T_allUp.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("5");
                        mConnectedThread.write("6");
                        T_allUp.setBackgroundResource(R.drawable.p_all_up);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        T_allUp.setBackgroundResource(R.drawable.n_all_up);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            T_E_Up.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("5");
                            T_E_Up.setBackgroundResource(R.drawable.p_up);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            T_E_Up.setBackgroundResource(R.drawable.n_up);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            T_D_Up.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("6");
                            T_D_Up.setBackgroundResource(R.drawable.p_up);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            T_D_Up.setBackgroundResource(R.drawable.n_up);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            T_E_Down.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("7");
                            T_E_Down.setBackgroundResource(R.drawable.p_down);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            T_E_Down.setBackgroundResource(R.drawable.n_down);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            T_D_Down.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                        if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                            mConnectedThread.write("8");
                            T_D_Down.setBackgroundResource(R.drawable.p_down);
                        } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                            mConnectedThread.write("0");
                            T_D_Down.setBackgroundResource(R.drawable.n_down);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            T_allDown.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("7");
                        mConnectedThread.write("8");
                        T_allDown.setBackgroundResource(R.drawable.p_all_down);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        T_allDown.setBackgroundResource(R.drawable.n_all_down);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

/////// Tudo Esquerda //////
            E_allUp.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("1");
                        mConnectedThread.write("5");
                        E_allUp.setBackgroundResource(R.drawable.p_all_left_up);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        E_allUp.setBackgroundResource(R.drawable.n_all_left_up);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            E_allDown.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("3");
                        mConnectedThread.write("7");
                        E_allDown.setBackgroundResource(R.drawable.p_all_left_down);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        E_allDown.setBackgroundResource(R.drawable.n_all_left_down);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

////// Tudo Direita //////
            D_allUp.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("2");
                        mConnectedThread.write("6");
                        D_allUp.setBackgroundResource(R.drawable.p_all_left_up);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        D_allUp.setBackgroundResource(R.drawable.n_all_left_up);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            D_allDown.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent event) {
                    if(conexao == true) {
                    if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                        mConnectedThread.write("4");
                        mConnectedThread.write("8");
                        D_allDown.setBackgroundResource(R.drawable.p_all_left_down);
                    } else if ((event.getAction() == MotionEvent.ACTION_UP)) {
                        mConnectedThread.write("0");
                        D_allDown.setBackgroundResource(R.drawable.n_all_left_down);
                    }
                    }else{
                        Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

//////// COMANDO TOTAL //////////
        allUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexao == true) {
                    mConnectedThread.write("a");

                }else{
                    Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                }
            }
        });
        allDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexao == true) {
                    mConnectedThread.write("b");
                }else{
                    Toast.makeText(MainActivity.this, "SEM CONEXÃO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case SOLICITA_BLUETOOTH:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplication(), "Bluetooth ativado!!!", Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
                break;
            case SOLICITA_CONEXAO:
                if (resultCode == Activity.RESULT_OK) {
                    MAC = data.getExtras().getString(listaDispositivos.ENDERECO_MEC);
                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(MAC);
                    try {
                        mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BTMODULEUUID);
                        mBluetoothSocket.connect();
                        conexao = true;
                        btn_Bluetooth.setBackground(getResources().getDrawable(R.drawable.bluetooth_on));
                        Toast.makeText(getApplication(), "AirSync CONECTADO", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getApplication(), "Falha ao conectar", Toast.LENGTH_LONG).show();
                        conexao = false;
                    }
                    mConnectedThread = new ConnectedThread(mBluetoothSocket);
                    mConnectedThread.start();

                } else {
             }
        }
    }

    private class ConnectedThread extends Thread {
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            OutputStream tmpOut = null;
            try {
                tmpOut = socket.getOutputStream();

            } catch (IOException e) {

            }
            mmOutStream = tmpOut;
        }

        public void write(String input) {

            byte[] msgBuffer = input.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException erro) {
                Toast.makeText(getApplication(), "Sem Conexão com AirSync", Toast.LENGTH_LONG).show();
            }
        }
    }
}
