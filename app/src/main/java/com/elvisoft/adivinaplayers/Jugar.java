package com.elvisoft.adivinaplayers;

import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jugar extends AppCompatActivity {
    TextView puntos,contador,vidas,txtcorrecto,txtincorrecto;
    ImageView imagen;
    EditText txtedit;
    Button btnconfirmar;
    String[] nombre_pers={"quick silver","black widow","carnage","daredevil","deadpool","destroyer","doom","falcon","galactus","gambit","ghost rider","groot","iron man","magneto","mysterio","punisher","red hulk","she hulk","spider man","storm","thanos","thor","ultron","venom","vision","wolverine","apocalypse"};
    String[] nombre_pers2={"quicksilver","blackwidow","carnage","daredevil","dead pool","destroyer","doom","falcon","galactus","gambit","ghostrider","groot","ironman","magneto","mysterio","punisher","red hulk","she hulk","spiderman","storm","thanos","thor","ultron","venom","vision","wolverine","apocalypse"};
    String[] imagen_pers={"img1","img2","img3","img4","img5","img6","img7","img8","img9","img10","img11","img12","img13","img14","img15","img16","img17","img18","img19","img20","img21","img22","img23","img24","img25","img26","img27"};
    int intpunto=0;

    ArrayList<String> nombresArr = new ArrayList<String>(Arrays.asList(nombre_pers));
    ArrayList<String> nombresArr2 = new ArrayList<String>(Arrays.asList(nombre_pers2));
    ArrayList<String> imagenArr =new ArrayList<String>(Arrays.asList(imagen_pers));

    int intvidas=3;
    int numerogenerado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        txtcorrecto=(TextView)findViewById(R.id.txtcorrecto);
        txtincorrecto=(TextView)findViewById(R.id.txtincorrecto);

        puntos=(TextView)findViewById(R.id.Puntos);
        vidas=(TextView)findViewById(R.id.vidas);
        contador=(TextView)findViewById(R.id.Cuenta);
        imagen=(ImageView)findViewById(R.id.imagen);
        txtedit=(EditText)findViewById(R.id.txtedit);
        btnconfirmar=(Button)findViewById(R.id.btnconfirmar);

        //buscamos numero aleatorio
        Random rand = new Random();
        int randInt=rand.nextInt(nombresArr.size());
        establecer_imagen(randInt);
        numerogenerado=randInt;
        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoconfirmar= txtedit.getText().toString().toLowerCase();
                if(textoconfirmar.equals(nombre_pers[numerogenerado])|textoconfirmar.equals(nombre_pers2[numerogenerado])){
                    txtcorrecto.setVisibility(View.VISIBLE);
                    intpunto=intpunto+1;
                    puntos.setText("Puntos: "+ intpunto);
                    esperar1();
                }
                else {
                    txtincorrecto.setVisibility(View.VISIBLE);
                    intvidas=intvidas-1;
                    vidas.setText("Vidas: "+ intvidas);
                    esperar2();
                }
                if(intvidas==0){
                    finish();
                }
            }
        });
    }
    void esperar2() {
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                txtincorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el personaje");
            }
        }.start();
    }
    void esperar1(){
        new CountDownTimer(4000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(""+(millisUntilFinished/1000 +1));
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);

                //borramos esa imagen para no tenerla en cuenta en un proximo juego
                nombresArr.remove(numerogenerado);
                nombresArr2.remove(numerogenerado);
                imagenArr.remove(numerogenerado);
                contador.setText("");
                //generamos un nuevo numero aleatorio
                GenerarImagenaletoria();

                txtcorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el personaje");
            }
        }.start();
    }
    void establecer_imagen(int numero){
        if (imagen_pers[numero]!=null){
            int id = getResources().getIdentifier(imagen_pers[numero],"mipmap",getPackageName());
            imagen.setImageResource(id);
        }
    }
    public void GenerarImagenaletoria(){
        Random rand = new Random();
        int randInt=rand.nextInt(nombresArr.size());
        establecer_imagen(randInt);
        numerogenerado=randInt;
    }
}
