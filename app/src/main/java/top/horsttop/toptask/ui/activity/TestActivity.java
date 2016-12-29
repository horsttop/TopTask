package top.horsttop.toptask.ui.activity;

import android.view.View;

import top.horsttop.toptask.R;
import top.horsttop.toptask.ui.base.BaseActivity;
import top.horsttop.toptask.ui.mvpview.TestMvpView;
import top.horsttop.toptask.ui.presenter.TestPresenter;

/**
 * Created by horsttop on 28/12/2016.
 */

public class TestActivity extends BaseActivity<TestMvpView,TestPresenter> implements TestMvpView {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected TestMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected TestPresenter obtainPresenter() {
        mPresenter = new TestPresenter();
        return (TestPresenter) mPresenter;
    }

    @Override
    public void onClick(View view) {

    }
}
