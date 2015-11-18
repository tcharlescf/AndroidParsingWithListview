package com.example.tae.listviewexample;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sairamkrishna on 4/11/2015.
 */

public class HandleXML {

    private String urlString = null;

    private User user;
    private List<User> restaurant;

    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public HandleXML(String url) {
        this.urlString = url;
    }

    public List<User> getRestaurant() {
        return restaurant;
    }

    public List<User> fetchXML() {
        restaurant = new ArrayList<User>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { // This thread is begun here
                try {
                    URL url = new URL(urlString); // Store the url address in url
                    // Make a connection to the internet
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000); // milliseconds
                    conn.setConnectTimeout(15000);  // milliseconds
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect(); // Connect to the internet

                    InputStream stream = conn.getInputStream();

                    // This is important to parse
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    // Make the object of XmlPullParser
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    // Call the parseXMLAndStoreIt method
//                    parseXMLAndStoreIt(myparser);

                    int event;
                    String text = null;

                    try {
                        /* Event of Integer type sores the myParser.getEventType() method's result */
                        event = myparser.getEventType();

                        /* The event is not end of the document */
                        while (event != XmlPullParser.END_DOCUMENT) {
                            /* Get a name each column? */
                            String name = myparser.getName();

                            switch (event) {
                                /* The START_TAG is started like <something> */
                                case XmlPullParser.START_TAG:
                                    if (name.equals("item")) {
                                        user = new User();
                                    }
                                    break;

                                case XmlPullParser.TEXT:
                                    text = myparser.getText();
                                    break;

                                /* The END_TAG is started like </something> */
                                case XmlPullParser.END_TAG:
                                    if (name.equals("item")) {
                                        restaurant.add(user);
                                    } else if (name.equals("addss1")) {
                                        user.address(text);
                                    } else if (name.equals("ceonm")) {
                                        user.ceoName(text);
                                    } else if (name.equals("marketnm")) {
                                        user.resName(text);
                                    }
                                    break;
                            }
                            /* Move to next the event */
                            event = myparser.next();
                        }
                        parsingComplete = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        return restaurant;
    }
}