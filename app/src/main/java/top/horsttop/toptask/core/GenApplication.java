package top.horsttop.toptask.core;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by horsttop on 28/12/2016.
 */

public class GenApplication extends Application {

    private static Application sApplication;

    private static Stack<Activity> sActivityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

    /**
     * Activity 压栈
     *
     * @param activity Activity
     */
    public static void pushActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * 出栈
     * @param activity
     */
    public static void popActivity(Activity activity){
        if(sActivityStack.contains(activity))
            sActivityStack.remove(activity);
    }

    /**
     * 退出应用时销毁所有的Activity
     */
    public static void clear() {
        try {
            for (Activity activity : sActivityStack) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.exit(0);
        }
    }
}
