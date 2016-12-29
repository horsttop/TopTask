package top.horsttop.toptask.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.horsttop.toptask.util.CommonUtil;
import top.horsttop.toptask.util.GenUIUtil;

/**
 * Created by horsttop on 15/12/30.
 */
public abstract class BaseFragment<V extends MvpView,P extends Presenter> extends Fragment implements MvpView {
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

    protected View rootView;

    protected Presenter mPresenter;

    protected Activity mContext;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtainPresenter();
        if(null != mPresenter)
        mPresenter.attachView(obtainMvpView());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            mContext = getActivity();
            rootView = inflater.inflate(getContentViewId(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
            initViews();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new BaseActivity.InitViewException(e.getMessage());
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPresenter.detachView();
    }

    @Override
    public void showTipMessage(String tip) {
        CommonUtil.showToastTip(tip);
    }

    @Override
    public void autoProgress(boolean show, String msg) {
        if(show)
            GenUIUtil.showProgressDialog(getContext(),msg);
        else
            GenUIUtil.dismissProgressDialog();
    }
}
