package top.horsttop.toptask.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.horsttop.toptask.core.GenApplication;
import top.horsttop.toptask.model.constant.Constants;
import top.horsttop.toptask.util.CommonUtil;
import top.horsttop.toptask.util.GenUIUtil;

/**
 * Created by horsttop on 15/12/30.
 */
public abstract class BaseActivity<V extends MvpView,P extends Presenter> extends AppCompatActivity implements MvpView ,View.OnClickListener{
    /**
     * 引入布局文件
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 界面初始化操作
     */
    protected abstract void initViews();

    /**
     * 获取MvpView 实现MvpView的子类接口,方法体中 return this 即可
     * @return
     */
    protected abstract V obtainMvpView();

    /**
     * 获取Presenter 引入Presenter,在方法体中给mPresenter赋值
     * @return
     */
    protected abstract P obtainPresenter();

    /**
     * 界面唤醒时需要的操作
     */
    protected void resumeViews() {
    }
    private Unbinder unbinder;
    protected Presenter mPresenter;
    protected InputMethodManager mImm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setContentView(getContentViewId());
            unbinder = ButterKnife.bind(this);
            GenApplication.pushActivity(this);
            mImm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            obtainPresenter();
            if(null != mPresenter)
            mPresenter.attachView(obtainMvpView());
            initViews();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(Constants.TAG,this.toString());
            throw new InitViewException( e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(mImm!=null){
            View view = getCurrentFocus();
            if(null!=view)
            mImm.hideSoftInputFromInputMethod(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        super.onBackPressed();

        //overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

    @Override
    public void showTipMessage(String tip) {
        CommonUtil.showToastTip(tip);
    }

    @Override
    public void autoProgress(boolean show, String msg) {
        if(show)
            GenUIUtil.showProgressDialog(this,msg);
        else
            GenUIUtil.dismissProgressDialog();
    }

    @Override
    protected void onDestroy() {
        GenUIUtil.dropProgressDialog();
        unbinder.unbind();
        GenApplication.popActivity(this);
        if(mPresenter!=null)
        mPresenter.detachView();
        super.onDestroy();
    }

    public static class InitViewException extends RuntimeException {
        public InitViewException(String msg) {
            super(msg);
        }
    }

}
