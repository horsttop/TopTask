package top.horsttop.toptask.ui.base;

/**
 * Presenter层的总父类接口
 * Created by horsttop on 15/12/30.
 */
public interface Presenter<G extends MvpView> {

    void attachView(G mvpView);

    void detachView();
}
