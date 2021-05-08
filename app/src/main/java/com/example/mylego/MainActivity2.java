//package com.example.mylego;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.mylego.content_providers.MyContentProvider;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_main2);
//
//        onClickAddDetails();
//        onClickShowDetails();
//    }
//
////    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
////        return true;
////    }
//    public void onClickAddDetails() {
//
////        MyContentProvider cp = new MyContentProvider();
////        cp.onCreate();
//
//
//
//
//        // class to add values in the database
//        ContentValues values = new ContentValues();
//
//        values.put(MyContentProvider.id, "222");
//
//        // fetching text from user
//        values.put(MyContentProvider.name, "TEST");
//
//        /////////////////////////////////////////
//        values.put(MyContentProvider.test_name, "TEST_2");
//
//        // inserting into database through content URI
//        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
//
//        // displaying a toast message
//        //Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
//    }
//
//    public void onClickShowDetails() {
//        // inserting complete table details in this text field
//        //TextView resultView= (TextView) findViewById(R.id.res);
//
//        // creating a cursor object of the
//        // content URI
//        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);
//
//        // iteration of the cursor
//        // to print whole table
//        if(cursor.moveToFirst()) {
//            StringBuilder strBuild=new StringBuilder();
//            while (!cursor.isAfterLast()) {
//                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")) + "  " + cursor.getString(cursor.getColumnIndex("name_test")));
//                cursor.moveToNext();
//            }
//            Log.i("FROM CONTENT PROVIDER", strBuild.toString());
//            //resultView.setText(strBuild);
//        }
//        else {
//            Log.i("FROM CONTENT PROVIDER", "No Records Found");
//            //resultView.setText("No Records Found");
//        }
//    }
//}
