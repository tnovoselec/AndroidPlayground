package com.tnovoselec.playground.bridge;


import rx.functions.Action1;
import rx.subjects.PublishSubject;

public enum HomeBridge {

  INSTANCE;

  private final PublishSubject<HomeData> homeSubject = PublishSubject.create();

  public void next(HomeData homeData) {
    homeSubject.onNext(homeData);
  }

  public void subscribe(Action1<HomeData> action1) {
    homeSubject.subscribe(action1);
  }

  public static class HomeData {

    public final int homeItemTop;

    public HomeData(int homeItemTop) {
      this.homeItemTop = homeItemTop;
    }
  }

}
