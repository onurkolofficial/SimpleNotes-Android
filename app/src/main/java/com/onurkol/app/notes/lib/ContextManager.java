package com.onurkol.app.notes.lib;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.lang.ref.WeakReference;

public class ContextManager {
    private static WeakReference<ContextManager> instance=null;
    private static WeakReference<Context> contextStatic,baseContextStatic;
    private static boolean base;

    private ContextManager(Context context){
        if(base)
            baseContextStatic=new WeakReference<>(context);
        contextStatic=new WeakReference<>(context);
    }

    public static synchronized void Build(Context context){
        base=false;
        instance=new WeakReference<>(new ContextManager(context));
    }
    public static synchronized void BuildBase(Context context){
        base=true;
        instance=new WeakReference<>(new ContextManager(context));
    }

    public static synchronized ContextManager getManager(){
        if(instance==null || instance.get()==null)
            instance=new WeakReference<>(new ContextManager(contextStatic.get()));
        return instance.get();
    }

    public static synchronized boolean isContext(){
        return (contextStatic != null && contextStatic.get() != null);
    }
    public Context getContext(){
        return contextStatic.get();
    }
    public Activity getContextActivity(){
        return ((Activity)contextStatic.get());
    }
    public Context getBaseContext(){
        return (baseContextStatic!=null ? baseContextStatic.get() : null);
    }
    public Activity getBaseContextActivity(){
        return (baseContextStatic!=null ? (Activity)baseContextStatic.get() : null);
    }
    public FragmentManager getBaseFragmentManager(){ return (baseContextStatic!=null ? ((FragmentActivity)baseContextStatic.get()).getSupportFragmentManager() : null); }
}
