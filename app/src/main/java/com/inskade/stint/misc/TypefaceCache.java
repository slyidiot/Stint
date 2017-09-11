package com.inskade.stint.misc;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypefaceCache {

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    public static Typeface get(AssetManager manager, String name) {
        synchronized (CACHE) {

            if (!CACHE.containsKey(name)) {
                Typeface t = Typeface.createFromAsset(manager, "fonts/" + name + ".otf");
                CACHE.put(name, t);
            }
            return CACHE.get(name);
        }
    }

}