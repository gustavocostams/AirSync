#include "BluetoothSerial.h"
#include <ESP32Servo.h>

Servo servo_A_ps;
Servo servo_A_pc;
Servo servo_B_ps;
Servo servo_B_pc;
Servo servo_C_ps;
Servo servo_C_pc;
Servo servo_D_ps;
Servo servo_D_pc;
Servo servo_E_ps;
Servo servo_E_pc;
Servo servo_F_ps;
Servo servo_F_pc;
String device_name = "Clover-Nespresso";

// Check if Bluetooth is available
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

// Check Serial Port Profile
#if !defined(CONFIG_BT_SPP_ENABLED)
#error Serial Port Profile for Bluetooth is not available or not enabled. It is only available for the ESP32 chip.
#endif

//Modulo A primeiro da esquerda para a direita olha por traz do dispenser
//PS porta saida, libera o café para o cliente
//PC porta controle, trava o cafe, para a próxima liberação.
BluetoothSerial SerialBT;
int modulo_A_ps = 12;
int modulo_A_pc = 14;
int modulo_B_ps = 27;
int modulo_B_pc = 26;
int modulo_C_ps = 25;
int modulo_C_pc = 33;
int modulo_D_ps = 32;
int modulo_D_pc = 35;
int modulo_E_ps = 34;
int modulo_E_pc = 39;
int modulo_F_ps = 2;
int modulo_F_pc = 4;

char Comando;

void setup() {
  servo_A_ps.attach(modulo_A_ps, 500, 2400);
  servo_A_pc.attach(modulo_A_pc, 500, 2400);
  servo_B_ps.attach(modulo_B_ps, 500, 2400);
  servo_B_pc.attach(modulo_B_pc, 500, 2400);
  servo_C_ps.attach(modulo_C_ps, 500, 2400);
  servo_C_pc.attach(modulo_C_pc, 500, 2400);
  servo_D_ps.attach(modulo_D_ps, 500, 2400);
  servo_D_pc.attach(modulo_D_pc, 500, 2400);
  servo_E_ps.attach(modulo_E_ps, 500, 2400);
  servo_E_pc.attach(modulo_E_pc, 500, 2400);
  servo_F_ps.attach(modulo_F_ps, 500, 2400);
  servo_F_pc.attach(modulo_F_pc, 500, 2400);



  Serial.begin(115200);
  SerialBT.begin(device_name);
  Serial.printf("The device with name \"%s\" is started.\nNow you can pair it with Bluetooth!\n", device_name.c_str());
}

void loop() {
  RecebeComando();
  if (Serial.available()) {
    SerialBT.write(Serial.read());
  }
  if (SerialBT.available()) {
    Serial.write(SerialBT.read());
  }
  delay(20);
}

void RecebeComando() {
  Comando = SerialBT.read();
  if (Comando == 'A') {
    servo_A_ps.write(20);
    Serial.println("Liberando sabor A");
    delay(500);
    servo_A_ps.write(130);
    delay(500);
    servo_A_pc.write(20);
    delay(500);
    servo_A_pc.write(130);
  }

  if (Comando == 'B') {
    servo_B_ps.write(20);
    Serial.println("Liberando sabor B");
    delay(500);
    servo_B_ps.write(130);
    delay(500);
    servo_B_pc.write(20);
    delay(500);
    servo_B_pc.write(130);
  }

  if (Comando == 'C') {
    servo_C_ps.write(20);
    Serial.println("Liberando sabor C");
    delay(500);
    servo_C_ps.write(130);
    delay(500);
    servo_C_pc.write(20);
    delay(500);
    servo_C_pc.write(130);
  }

  if (Comando == 'D') {
    servo_D_ps.write(20);
    Serial.println("Liberando sabor D");
    delay(500);
    servo_D_ps.write(130);
    delay(500);
    servo_D_pc.write(20);
    delay(500);
    servo_D_pc.write(130);
  }

  if (Comando == 'E') {
    servo_E_ps.write(20);
    Serial.println("Liberando sabor E");
    delay(500);
    servo_E_ps.write(130);
    delay(500);
    servo_E_pc.write(20);
    delay(500);
    servo_E_pc.write(130);
  }

  if (Comando == 'F') {
    servo_F_ps.write(20);
    Serial.println("Liberando sabor F");
    delay(500);
    servo_F_ps.write(130);
    delay(500);
    servo_F_pc.write(20);
    delay(500);
    servo_F_pc.write(130);
  }

  if (Comando == 'Z') {
    Serial.println("Fazendo espurga...");

    Serial.println("espurga sabor A");
    servo_A_ps.write(20);
    delay(500);
    servo_A_ps.write(130);
    delay(500);
    servo_A_pc.write(20);
    delay(500);
    servo_A_pc.write(130);

    Serial.println("espurga sabor B");
    servo_B_ps.write(20);
    delay(500);
    servo_B_ps.write(130);
    delay(500);
    servo_B_pc.write(20);
    delay(500);
    servo_B_pc.write(130);

    Serial.println("espurga sabor C");
    servo_C_ps.write(20);
    delay(500);
    servo_C_ps.write(130);
    delay(500);
    servo_C_pc.write(20);
    delay(500);
    servo_C_pc.write(130);

    Serial.println("espurga sabor D");
    servo_D_ps.write(20);
    delay(500);
    servo_D_ps.write(130);
    delay(500);
    servo_D_pc.write(20);
    delay(500);
    servo_D_pc.write(130);

    Serial.println("espurga sabor E");
    servo_E_ps.write(20);
    delay(500);
    servo_E_ps.write(130);
    delay(500);
    servo_E_pc.write(20);
    delay(500);
    servo_E_pc.write(130);
    servo_E_ps.write(20);

    Serial.println("espurga sabor F");
    servo_F_ps.write(20);
    delay(500);
    servo_F_ps.write(130);
    delay(500);
    servo_F_pc.write(20);
    delay(500);
    servo_F_pc.write(130);
  }
}
