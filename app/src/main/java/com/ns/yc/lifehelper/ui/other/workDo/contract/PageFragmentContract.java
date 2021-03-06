package com.ns.yc.lifehelper.ui.other.workDo.contract;


import android.app.Activity;

import com.ns.yc.lifehelper.base.BasePresenter;
import com.ns.yc.lifehelper.base.BaseView;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/9/14
 * 描    述：干货集中营首页桥梁
 * 修订历史：
 * ================================================
 */
public interface PageFragmentContract {

    //View(activity/fragment)继承，需要实现的方法
    interface View extends BaseView {

    }

    //Presenter控制器
    interface Presenter extends BasePresenter {
        void bindView(Activity activity);
    }


}
