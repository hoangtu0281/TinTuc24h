package com.example.hoangtu.tintuc24h.parse;

import android.os.AsyncTask;
import android.os.Handler;

import com.example.hoangtu.tintuc24h.model.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.zip.Deflater;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLHandler extends DefaultHandler {
    private ArrayList<Item> arrItem = new ArrayList<>();
    private Item item;
    private final String TAG_ITEM = "item";
    private final String TAG_TITLE = "title";
    private final String TAG_DESC = "description";
    private final String TAG_DATE = "pubDate";
    private final String TAG_LINK = "link";
    private StringBuilder value;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals(TAG_ITEM)){
            item = new Item();
        }
        value = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (item == null){
            return;
        }
        switch (qName){
            case TAG_TITLE:
                item.setTitle(value.toString());
                break;
            case TAG_DESC:
                String src = "src=\"";
                int index =value.indexOf(src);
                String desc = value.substring(index+src.length());
                index = desc.indexOf("\"");
                String img = desc.substring(0,index);
                if (!img.contains("http")){
                    String origin="original=\"";
                    int indexOrigin = desc.indexOf(origin);
                    img = desc.substring(indexOrigin + origin.length(),desc.indexOf("></a>"))
                            .replace("\"", "").trim();
                }
                String br = "</br>";
                index = desc.lastIndexOf(br);
                desc = desc.substring(index+br.length());
                item.setImg(img);
                item.setDesc(desc);
                break;
            case TAG_LINK:
                item.setLink(value.toString());
                break;
            case TAG_DATE:
                item.setPubDate(value.toString());
                break;
            case TAG_ITEM:
                arrItem.add(item);
                break;
        }
    }
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }
}
