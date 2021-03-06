package com.ns.yc.lifehelper.ui.me.presenter;

import com.ns.yc.lifehelper.base.BaseApplication;
import com.ns.yc.lifehelper.ui.me.contract.MeGanKCollectContract;
import com.ns.yc.lifehelper.ui.other.gank.bean.GanKFavorite;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.subscriptions.CompositeSubscription;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/9/14
 * 描    述：干货集中营
 * 修订历史：
 * ================================================
 */
public class MeGanKCollectPresenter implements MeGanKCollectContract.Presenter {

    private MeGanKCollectContract.View mView;
    private CompositeSubscription mSubscriptions;
    private int mPage = 0;
    private Realm realm;
    private RealmResults<GanKFavorite> ganKFavorites;

    public MeGanKCollectPresenter(MeGanKCollectContract.View androidView) {
        this.mView = androidView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        initRealm();
    }

    private void initRealm() {
        if(realm==null){
            realm = BaseApplication.getInstance().getRealmHelper();
        }
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    /**
     * 获取收藏数据
     */
    @Override
    public void getCollectData(boolean isRefresh) {
        if (isRefresh) {
            mPage = 0;
        } else {
            mPage += 1;
        }
        initRealm();
        if(realm!=null && realm.where(GanKFavorite.class).findAll()!=null){
            ganKFavorites = realm.where(GanKFavorite.class).findAll();
        }else {
            return;
        }
        List<GanKFavorite> list = new ArrayList<>();
        for(int a=0 ; a<ganKFavorites.size() ; a++){
            GanKFavorite favorite = new GanKFavorite();
            favorite.setTitle(ganKFavorites.get(a).getTitle());
            favorite.setAuthor(ganKFavorites.get(a).getAuthor());
            favorite.setCreatetime(ganKFavorites.get(a).getCreatetime());
            favorite.setData(ganKFavorites.get(a).getData());
            favorite.setGankID(ganKFavorites.get(a).getGankID());
            favorite.setType(ganKFavorites.get(a).getType());
            favorite.setUrl(ganKFavorites.get(a).getUrl());
            list.add(favorite);
        }
        if(list.size()>0){
            mView.setFavoriteItems(list);
        }else {
            mView.setEmpty();
        }
    }
}
