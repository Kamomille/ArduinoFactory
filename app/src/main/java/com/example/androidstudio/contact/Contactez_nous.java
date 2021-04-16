package com.example.androidstudio.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidstudio.R;
import com.example.androidstudio.pages.Page_Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contactez_nous extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);

        // Permet d'avoir une fleche retour en haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // champs de texte dans le formulaire de contact
        final EditText your_first_name  = (EditText) findViewById(R.id.your_first_name);
        final EditText your_name        = (EditText) findViewById(R.id.your_name);
        final EditText your_email       = (EditText) findViewById(R.id.your_email);
        final EditText your_subject     = (EditText) findViewById(R.id.your_subject);
        final EditText your_message     = (EditText) findViewById(R.id.your_message);
        Button email = (Button) findViewById(R.id.post_message);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Permet d'obtenir le texte tapé
                String first_name      = your_first_name.getText().toString();
                String name      = your_name.getText().toString();
                String email     = your_email.getText().toString();
                String subject   = your_subject.getText().toString();
                String message   = your_message.getText().toString();
                // Si le champs du prénom est est vide après avoir appuyé sur le bouton on lui indique
                if (TextUtils.isEmpty(first_name)){
                    your_first_name.setError("Entrer votre prénom");
                    your_first_name.requestFocus();
                    return;
                }
                // Si le champs du nom est est vide après avoir appuyé sur le bouton on lui indique
                if (TextUtils.isEmpty(name)){
                    your_name.setError("Entrer votre nom");
                    your_name.requestFocus();
                    return;
                }
                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    your_email.setError("Email invalide ");
                    return;
                }
                if (TextUtils.isEmpty(subject)){
                    your_subject.setError("Entrer votre objet");
                    your_subject.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(message)){
                    your_message.setError("Entrer votre Message");
                    your_message.requestFocus();
                    return;
                }
                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"arduinofactory@yahoo.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Prénom:"+first_name+'\n'+"Nom:"+name+'\n'+"Email :"+email+'\n'+"Objet:"+'\n'+subject+'\n'+"Message:"+'\n'+message);
                startActivity(Intent.createChooser(sendEmail, "Envoie de l'email..."));
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Permet de retourner à la page contact
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Page_Contact.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}

