package mass.testandroidapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sweetlab.tech/images/parking_after_process.jpg"));
                startActivity(browserIntent);
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sweetlab.tech/images/parking.jpg"));
                startActivity(browserIntent);
            }
        });

        /*Ion.with(this.getApplicationContext())
                .load("http://www.navipedia.net/images/a/a9/Example.jpg")
                .withBitmap()
                .placeholder(R.drawable.Test)
                .intoImageView(imageView1);
        */

        new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute("http://sweetlab.tech/images/parking_after_process.jpg");
        new DownloadImageTask((ImageView) findViewById(R.id.imageView2)).execute("http://sweetlab.tech/images/parking.jpg");

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        updateSpinner(spinner);
    }

    private void updateSpinner(Spinner spinner)
    {
        //replace this list with dynmic one from DB
        ArrayList<String> list = new ArrayList<String>();
        list.add("A1");
        list.add("A2");
        list.add("A11");
        list.add("B3");
        list.add("A23");
        list.add("C12");

        //keep as is
        ArrayAdapter<String> listAdapter =new ArrayAdapter<String>(this.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(listAdapter);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}