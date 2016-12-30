package top.horsttop.toptask.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import top.horsttop.toptask.R;


/**
 * Created by horsttop on 16/12/2016.
 */

public class GenUIUtil {

    /**
     * 等待框
     */
    public static ProgressDialog sProgressDialog;
    public static void showProgressDialog(Context context, String msg){
        if(null == sProgressDialog){
            sProgressDialog = new ProgressDialog(context, R.style.WaitingProgressDialog);
            sProgressDialog.setCancelable(false);
        }
        if(!TextUtils.isEmpty(msg))
            sProgressDialog.setMessage(msg);
        else
            sProgressDialog.setMessage("请等待...");
        sProgressDialog.show();
    }

    public static void dismissProgressDialog(){
        if(null!=sProgressDialog&&sProgressDialog.isShowing()){
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
    }

    public static void dropProgressDialog(){
        if(null!=sProgressDialog){
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
        if(null!=sAlertDialog){
            sAlertDialog.dismiss();
            sAlertDialog = null;
        }
    }

    public static AlertDialog sAlertDialog;

    public static void showTipDialog(Context context,String tip){
        if(null == sAlertDialog)
        sAlertDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.tip)
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(tip)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.app_name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();
        sAlertDialog.show();
    }

    public static void showTipDialog(Context context,String tip,DialogInterface.OnClickListener confirmClickListener){
        if(null == sAlertDialog)
            sAlertDialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.tip)
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage(tip)
                    .setPositiveButton(R.string.confirm, confirmClickListener)
                    .create();
        sAlertDialog.show();
    }

    public static void dismissTipDialog(){
        if(null!=sAlertDialog&&sAlertDialog.isShowing()){
            sAlertDialog.dismiss();
            sAlertDialog = null;
        }
    }

    public static void closeSoftInputWindow(InputMethodManager imm, Activity activity){
        if (activity.getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
