package com.ArduinoFactory.androidstudio.outils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Locale;

public class Outils_reconnaissance_composants extends AppCompatActivity {

    private static final String TAG = "IA_Classifier";

    private String[] classes; // Désormais récupéré depuis strings.xml
    private ImageView imageView;
    private TextView result;
    private LinearLayout layout_bar;
    private final int imageSize = 64;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_ia);


        // Récupération du tableau depuis strings.xml
        classes = getResources().getStringArray(R.array.classes_array);

        ImageButton camera = findViewById(R.id.button_photo);
        ImageButton gallery = findViewById(R.id.button_gallerie);
        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);
        layout_bar = findViewById(R.id.layout_bar);

        camera.setOnClickListener(view -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 3);
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        });

        gallery.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 1);
        });
    }

    public void classifyImage(Bitmap image) {
        try {
            Model model = Model.newInstance(getApplicationContext());

            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(
                    new int[]{1, imageSize, imageSize, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF));
                    byteBuffer.putFloat(((val >> 8) & 0xFF));
                    byteBuffer.putFloat((val & 0xFF));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] confidences = outputFeature0.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            float sumConfidence = 0;

            for (float confidence : confidences) {
                if (confidence > 0) sumConfidence += confidence;
            }

            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }

            result.setText(classes[maxPos]);

            float[][] int1 = new float[confidences.length][2];
            for (int i = 0; i < confidences.length; i++) {
                int1[i][0] = i;
                int1[i][1] = confidences[i] > 0 ? (confidences[i] * 100) / sumConfidence : 0;
            }

            Arrays.sort(int1, (b, a) -> Float.compare(a[1], b[1]));

            layout_bar.removeAllViews();

            for (int i = 0; i < confidences.length; i++) {
                if (int1[i][1] > 0) {
                    View progressBarView = getLayoutInflater().inflate(R.layout.ia_progress_bar, layout_bar, false);
                    ProgressBar progressBar = progressBarView.findViewById(R.id.progress_bar);
                    TextView name_component = progressBarView.findViewById(R.id.textView_name_component);
                    TextView percentage = progressBarView.findViewById(R.id.textView_percentage);

                    progressBar.setProgress((int) int1[i][1]);
                    name_component.setText(classes[(int) int1[i][0]]);
                    percentage.setText(String.format(Locale.US, "%.1f%%", int1[i][1]));
                    layout_bar.addView(progressBarView);
                }
            }

            model.close();

        } catch (IOException e) {
            Log.e(TAG, "Erreur pendant la classification d'image", e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Bitmap image = null;

            if (requestCode == 3) { // Camera
                image = data.getParcelableExtra("data", Bitmap.class);

            } else if (requestCode == 1) { // Galerie
                Uri dat = data.getData();
                if (dat != null) {
                    try (InputStream inputStream = getContentResolver().openInputStream(dat)) {
                        image = BitmapFactory.decodeStream(inputStream);
                    } catch (IOException e) {
                        Log.e(TAG, "Erreur lors de la récupération de l'image depuis la galerie", e);
                    }
                }
            }

            if (image != null) {
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
    }
}
