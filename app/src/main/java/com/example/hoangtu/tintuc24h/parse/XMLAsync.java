package com.example.hoangtu.tintuc24h.parse;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.example.hoangtu.tintuc24h.model.Item;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLAsync extends AsyncTask<String, Void, ArrayList<Item>> {
    public static final int WHAT_DATA = 1;
    private Handler handler;

    public XMLAsync(Handler handler) {
        this.handler = handler;
    }
    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            String link = strings[0];
            parser.parse(link,handler);
            return handler.getArrItem();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        Message message = new Message();
        message.what = WHAT_DATA;
        message.obj = items;
        handler.sendMessage(message);
    }
}
