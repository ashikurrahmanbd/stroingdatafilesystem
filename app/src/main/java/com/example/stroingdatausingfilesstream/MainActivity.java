package com.example.stroingdatausingfilesstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextId);
        button = (Button) findViewById(R.id.saveNote);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = editText.getText().toString();

               if(text != null){

                   writeToFile(text);


               }else{

                   Toast.makeText(MainActivity.this, "Please Enter Some Data", Toast.LENGTH_SHORT).show();
               }

            }
        });

        readFromFile();


    }

    private void writeToFile(String text){

        try {
            FileOutputStream fileOutputStream = openFileOutput("dairy.txt", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "Data has successfully Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void readFromFile(){

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("dairy.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            StringBuffer stringBuffer = new StringBuffer();

            while((line = bufferedReader.readLine()) != null){

                stringBuffer.append(line+"\n");
            }
            editText.setText(stringBuffer.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
