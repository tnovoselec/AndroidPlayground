package com.tnovoselec.playground.bridge;


import rx.functions.Action1;
import rx.subjects.PublishSubject;

public enum ForumBridge {

  INSTANCE;

  private PublishSubject<ForumData> forumSubject = PublishSubject.create();

  public void next(ForumData forumData) {
    forumSubject.onNext(forumData);
  }

  public void subscribe(Action1<ForumData> onNext) {
    forumSubject.subscribe(onNext);
  }

  public static class ForumData {

    public final int titleTop;
    public final int titleLeft;

    public ForumData(int titleLeft, int titleTop) {
      this.titleLeft = titleLeft;
      this.titleTop = titleTop;
    }
  }

}
