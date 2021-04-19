package com.onurkol.app.notes.tools;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

// Set/Get Activity Context

public class ContextTool {
    private static WeakReference<Activity> mActivity;
    private static WeakReference<Context> mContext;

    public static void setActivity(Activity activity){
        mActivity=new WeakReference<Activity>(activity);
    }
    public static void setContext(Context context){
        mContext=new WeakReference<Context>(context);
    }
    public static Activity getActivity(){
        return mActivity.get();
    }
    public static Context getContext(){
        return mContext.get();
    }

}
