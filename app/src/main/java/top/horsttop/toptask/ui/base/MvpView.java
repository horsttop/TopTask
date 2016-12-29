package top.horsttop.toptask.ui.base;

/**
 * View层的总父类接口
 * Created by horsttop on 15/12/30.
 */
public interface MvpView {
    void showTipMessage(String tip);

    /**
     * true 显示进度条,false 关闭进度条
     * @param show
     * @param msg
     */
    void autoProgress(boolean show, String msg);

}
