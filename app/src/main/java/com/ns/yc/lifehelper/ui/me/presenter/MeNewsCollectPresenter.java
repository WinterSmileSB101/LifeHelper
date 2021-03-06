package com.ns.yc.lifehelper.ui.me.presenter;

import com.ns.yc.lifehelper.base.BaseApplication;
import com.ns.yc.lifehelper.ui.me.contract.MeNewsCollectContract;
import com.ns.yc.lifehelper.ui.other.zhihu.model.db.RealmHelper;

import io.realm.Realm;
import rx.subscriptions.CompositeSubscription;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/9/14
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MeNewsCollectPresenter implements MeNewsCollectContract.Presenter {

    private MeNewsCollectContract.View mView;
    private CompositeSubscription mSubscriptions;
    private Realm realm;


    public MeNewsCollectPresenter(MeNewsCollectContract.View androidView) {
        this.mView = androidView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        initRealm();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void initRealm() {
        if(realm ==null){
            realm = BaseApplication.getInstance().getRealmHelper();
        }
    }

    @Override
    public void deleteLikeData(String id) {
        RealmHelper.deleteLikeBean(realm, id);
    }

    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        RealmHelper.changeLikeTime(realm,id,time,isPlus);
    }


    @Override
    public void getLikeData() {
        mView.showContent(RealmHelper.getLikeList(realm));
    }
}
