package com.example.thiha.popcorn_kotlin.moviedetail.mvp

import android.util.Log
import com.jorpaspr.movies.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter(private val model: MovieDetailModel, private val view: MovieDetailView): BasePresenter() {

    companion object {
        private val LOG = this::class.java.simpleName
    }

    private var bookmarked: Boolean = model.userMovie.bookmarked

    override fun onCreate() {
        view.showBookmarked(bookmarked)
        view.showUserMovie(model.userMovie)

        val disposable: Disposable = view.clickBookmarkButtonObservable
                .flatMap { onClickBookmark().subscribeOn(Schedulers.io()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    bookmarked = !bookmarked
                    view.showBookmarked(bookmarked)
                    view.enableBookmarkButton(true)
                    if (bookmarked) {
                        Log.d(LOG, "Bookmark Added")
                    } else {
                        Log.d(LOG, "Bookmark removed")
                    }
                }, {
                    view.enableBookmarkButton(true)
                    Log.d(LOG, "Set Bookmark failed")
                })

        addDisposable(disposable)
    }

    private fun onClickBookmark(): Observable<Any> {
        view.enableBookmarkButton(false)

        return if (bookmarked) {
            Log.d(LOG, "Removing Bookmark ...")
            model.unBookmarkMovie()
        } else {
            Log.d(LOG, "Adding Bookmark ...")
            model.bookmarkMovie()
        }
    }
}