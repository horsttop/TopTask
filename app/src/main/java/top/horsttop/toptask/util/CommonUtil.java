package top.horsttop.toptask.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.horsttop.toptask.core.GenApplication;


/**
 * Created by horsttop on 12/12/2016.
 */

public class CommonUtil {

    /**
     * 两次退出
     */
    private static long currentTime = 0l;
    private static long lastTime = 0l;
    public static void exit() {
        currentTime = new Date().getTime();
        if (currentTime - lastTime < 2000) {
            GenApplication.clear();
        }
        lastTime = currentTime;
        showToastTip("再按一次退出");
    }


    private static Toast mToast;
    public static void showToastTip(String tip){
        if(TextUtils.isEmpty(tip))
            return;
        if(null == mToast){
            mToast = Toast.makeText(GenApplication.getApplication(),tip,Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tip);
        }
        mToast.show();

    }
    public static void startActivity(Activity activity, View view, Class clazz, Bundle bundle){
        Intent intent = new Intent(activity, clazz);
        if(null!=bundle)
            intent.putExtras(bundle);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
                        (int) view.getWidth() / 2, (int) view.getHeight() / 2, //拉伸开始的坐标
                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static void startActivityForResult(Activity activity,View view, Class clazz, Bundle bundle,int requestCode){
        Intent intent = new Intent(activity, clazz);
        if(null!=bundle)
            intent.putExtras(bundle);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
                        (int) view.getWidth() / 2, (int) view.getHeight() / 2, //拉伸开始的坐标
                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        ActivityCompat.startActivityForResult(activity, intent,requestCode, options.toBundle());
    }

    public static void startActivity(Activity activity, View holdView, Class clazz, Bundle bundle, String holdTag){

        Intent intent = new Intent(activity,clazz);
        if(null!=bundle)
            intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions  options = ActivityOptions.makeSceneTransitionAnimation(activity, holdView, holdTag);
            ActivityCompat.startActivity(activity, intent, options.toBundle());
        } else {
            startActivity(activity,holdView,clazz,bundle);
        }

    }



    /**
     * 验证手机号码
     * @param phone 要验证的手机号
     * @return 验证结果
     */
    public static boolean isPhoneNumber(String phone) {
        if(null==phone)
            return false;
        Pattern p = Pattern.compile("^[1][34578][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(phone);
        return m.matches();
    }

}
