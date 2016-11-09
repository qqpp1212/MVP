package com.feicuiedu.mvpdemo.register;

/**
 * Created by 123 on 2016/11/9.
 */

public interface RegisterView {
    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void navigationToMain();
}
