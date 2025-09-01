package com.ArduinoFactory.androidstudio.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ArduinoFactory.androidstudio.R;

import java.util.regex.Pattern;

public class Contactez_nous extends AppCompatActivity {

    @SuppressLint("IntentReset")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        EditText your_first_name = findViewById(R.id.your_first_name);
        EditText your_name = findViewById(R.id.your_name);
        EditText your_email = findViewById(R.id.your_email);
        EditText your_subject = findViewById(R.id.your_subject);
        EditText your_message = findViewById(R.id.your_message);
        Button emailButton = findViewById(R.id.post_message);

        emailButton.setOnClickListener(v -> {
            String first_name = your_first_name.getText().toString().trim();
            String name = your_name.getText().toString().trim();
            String email = your_email.getText().toString().trim();
            String subject = your_subject.getText().toString().trim();
            String message = your_message.getText().toString().trim();

            if (TextUtils.isEmpty(first_name)) {
                your_first_name.setError(getString(R.string.hint_prenom));
                your_first_name.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(name)) {
                your_name.setError(getString(R.string.hint_nom));
                your_name.requestFocus();
                return;
            }

            if (!isValidEmail(email)) {
                your_email.setError(getString(R.string.hint_email));
                your_email.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(subject)) {
                your_subject.setError(getString(R.string.hint_objet));
                your_subject.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(message)) {
                your_message.setError(getString(R.string.hint_message));
                your_message.requestFocus();
                return;
            }

            Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
            sendEmail.setData(Uri.parse("mailto:arduinofactory@yahoo.com")); // 👈 bon format
            sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
            sendEmail.putExtra(Intent.EXTRA_TEXT,
                    getString(R.string.email_prenom_label) + " " + first_name + '\n' +
                            getString(R.string.email_nom_label) + " " + name + '\n' +
                            getString(R.string.email_email_label) + " " + email + '\n' +
                            getString(R.string.email_subject_label) + " " + subject + '\n' +
                            getString(R.string.email_message_label) + " " + message
            );

        // Vérifie qu'une app de messagerie est dispo
            if (sendEmail.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(sendEmail, getString(R.string.send_email_chooser_title)));
            }

            startActivity(Intent.createChooser(sendEmail, getString(R.string.send_email_chooser_title)));
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(EMAIL_PATTERN, email);
    }
}
