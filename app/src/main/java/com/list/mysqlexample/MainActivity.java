package com.list.mysqlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String[]> data=new ArrayList<>();
    ListView mylist;
    TextView result;
    Button getDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        getDataBtn=findViewById(R.id.getdatabtn);
        mylist=findViewById(R.id.mylist);


        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getData getData = new getData();
                getData.execute("");
            }
        });

    }
    private  class getData extends AsyncTask<String,String,String>
    {



        @Override
        protected void onPreExecute() {

            result.setText("Fetching Data....");
        }

        @Override
        protected String doInBackground(String... strings)
        {
            String Message="";

            String  HOst = "192.168.0.103";
            String  Port = "3306";
            String  DBName = "mydatabase";
            String  User = "root";
            String  Pass = "";
            try
            {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection Con = DriverManager.getConnection("jdbc:mysql://" + HOst + ":" + Port + "/" + DBName + "", User, Pass);
                Statement St;
                String SQl = "Select * from  Fruits";
                St = Con.createStatement();
                ResultSet resultSet = St.executeQuery(SQl);
                while (resultSet.next())
                {
                    String name=resultSet.getString("name");
                    String price=resultSet.getString("price");
                    data.add(new String[]{name,price});
                }


                Message="Sucess";
            }
            catch (Exception e)
            {
                Message=e.getMessage();
                e.printStackTrace();
            }
            return Message;

        }
        @Override
        protected void onPostExecute(String s)
        {

            result.setText("");
            if(s.equals("Sucess"))
            {
                mylist.setAdapter(new ItemAdapter(MainActivity.this,data));
            }
            else
            {
                result.setText(s);
            }

        }
    }

}
