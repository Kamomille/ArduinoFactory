package com.ArduinoFactory.androidstudio.outils;

import static com.google.common.primitives.Floats.min;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.ml.Model;



public class Outils_reconnaissance_composants extends AppCompatActivity {

    String[] classes = {"7 segments", "Capteur d'humidité", "Ecran Lcd", "Led", "Résistance", "Servo Moteur"};
    ImageButton camera, gallery, button_aide;
    ImageView imageView;
    TextView result;
    LinearLayout layout_bar;
    int imageSize = 64;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_ia);

        camera = findViewById(R.id.button_photo);
        gallery = findViewById(R.id.button_gallerie);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        layout_bar = (LinearLayout) findViewById(R.id.layout_bar);

        //button_aide.setOnClickListener(new View.OnClickListener(){
        //    @Override public void onClick(View v){ openActivtity_helpIA(); } } );

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 3);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                        }
                    }
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
            }
        });
    }

    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, imageSize, imageSize, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer
            int pixel = 0;
            for(int i = 0; i < imageSize; i ++){
                for(int j = 0; j < imageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result ------------------------------------------------
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();

            // Find the index of the class with the biggest confidence -----------------------------

            int maxPos = 0;
            float maxConfidence = 0;
            float sumConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > 0){
                    sumConfidence += confidences[i];
                }
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            result.setText(classes[maxPos]);

            // Sort by confidence ----------------------------------------------------------------

            float[][] int1 = new float[confidences.length][2];

            for (int i = 0; i < confidences.length; i++) {
                int1[i][0] = i;
                if (confidences[i] > 0) {
                    int1[i][1] = (confidences[i]*100)/ sumConfidence;
                }
                else{
                    int1[i][1] = 0;
                }
            }
            Arrays.sort(int1, (b, a) -> Float.compare(a[1], b[1])); // for descending order


            // ProgressBar Display ----------------------------------------------------------------

            layout_bar.removeAllViews();

            for(int i = 0; i < confidences.length; i++){
                if (int1[i][1] > 0){
                    View progressBarView = getLayoutInflater().inflate(R.layout.ia_progress_bar,null, false);
                    ProgressBar progressBar = progressBarView.findViewById(R.id.progress_bar);
                    TextView name_component = progressBarView.findViewById(R.id.textView_name_component);
                    TextView percentage = progressBarView.findViewById(R.id.textView_percentage);

                    progressBar.setProgress((int) int1[i][1]);
                    name_component.setText(classes[(int) int1[i][0]]);
                    percentage.setText(String.format("%.1f%%", int1[i][1]));
                    layout_bar.addView(progressBarView);
                }
            }

            // Releases model resources if no longer used ------------------------------------------
            model.close();

        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 3){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }else{
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    public void openActivtity_helpIA(){
        Intent intent = new Intent(this, Outils_ia_page_aide.class);
        startActivity(intent);
    }

     */


}




