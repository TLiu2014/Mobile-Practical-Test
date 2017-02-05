package com.tliu.jsonplaceholder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private JSONObject jsonObject;
    private String jsonStr ;
    private int size;
    private String url = "http://jsonplaceholder.typicode.com/photos/";

    public ImageAdapter(Context c, int size) {
        mContext = c;
        this.size = size;
        Log.d("Adapter size", ""+size);
    }

    public int getCount() {
        return 20;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("ImageAdapter", "getView start.");
        ImageView imageView;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4, 4);
        }
        else {
            imageView = (ImageView) convertView;
        }

        try {

            InputStream inputStream = this.mContext.openFileInput("jph_"+(position+1)+".json");
            size++;
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                jsonStr = stringBuilder.toString();
                Log.d("ReadFile", ">>>  "+jsonStr);
            }

            jsonObject = new JSONObject(jsonStr);
            Log.d("jsonStr", jsonStr);
            Log.d("jsonObject",jsonObject.toString());
            Log.d("Image url", jsonObject.getString("thumbnailUrl"));
            Picasso.with(mContext).load(jsonObject.getString("thumbnailUrl").replace("placehold" +
                    ".it/150/", "placeholdit.imgix" +
                    ".net/~text?txtsize=14&txt=150×150&w=150&h=150&bg=")).placeholder(R.drawable
                    .lena).into
                    (imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        Log.d("ImageAdapter", "getView done.");
        return imageView;
    }
}
