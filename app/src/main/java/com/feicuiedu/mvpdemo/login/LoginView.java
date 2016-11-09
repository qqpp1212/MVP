package com.feicuiedu.mvpdemo.login;

/**
 * Created by 123 on 2016/11/9.
 */

public interface LoginView {

    /**
     * 分析登录中会涉及到的视图变化
     * 1. 显示进度，告诉你正在登录
     * 2. 隐藏进度
     * 3. 显示信息：弹出吐司
     * 4. 跳转到主页面
     */
    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void navigationToMain();
}
