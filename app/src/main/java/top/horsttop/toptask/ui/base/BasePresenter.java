package top.horsttop.toptask.ui.base;


import rx.subscriptions.CompositeSubscription;

/**
 * Presenter层的基础实现类
 * Created by horsttop on 15/12/30.
 */
public class BasePresenter<G extends MvpView> implements Presenter<G> {
    private G mMvpView;
    protected CompositeSubscription mCompositeSubscription;
    public BasePresenter(){

    }

    @Override
    public void attachView(G mvpView) {
        if(null == mCompositeSubscription){
            mCompositeSubscription = new CompositeSubscription();
        }
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        if(null != mCompositeSubscription){
            mCompositeSubscription.unsubscribe();
        }
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public G getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
