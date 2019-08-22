package com.tokopedia.testproject.problems.news.view

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tokopedia.testproject.R
import com.tokopedia.testproject.problems.news.common.LoadingAlert
import com.tokopedia.testproject.problems.news.model.Article
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter
import kotlinx.android.synthetic.main.activity_news.*
import java.util.*
import kotlin.Comparator
import com.viewpagerindicator.CirclePageIndicator
import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask
import android.support.v4.view.ViewPager
import android.view.View
import android.support.v7.widget.SearchView
import com.tokopedia.testproject.problems.news.common.ImageAdapter
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.SearchManager
import android.R.menu
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


class NewsActivity : AppCompatActivity(), com.tokopedia.testproject.problems.news.presenter.NewsPresenter.View {


    private var newsPresenter: NewsPresenter? = null
    private var newsAdapter: NewsAdapter? = null
    private var loading: Dialog? = null
    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        loading = LoadingAlert.progressDialog(this, this)

        loading?.show()
        newsAdapter = NewsAdapter(null)
        newsPresenter = NewsPresenter(this)

        recyclerView.setAdapter(newsAdapter)
        newsPresenter!!.getHeadlines("bbc-news")
        newsPresenter!!.getEverything("android")
        //setHasOptionsMenu(true);

    }

    override fun onSuccessGetNews(articleList: List<Article>) {

        val StringAscComparator = object : Comparator<Article> {

            override fun compare(app2: Article, app1: Article): Int {

                return app1.publishedAt!!.compareTo(app2.publishedAt!!, ignoreCase = true)
            }
        }
        //Collections.sort(articleList, StringAscComparator);
        newsAdapter!!.setArticleList(articleList)
        newsAdapter!!.notifyDataSetChanged()
        loading?.dismiss()
    }

    override fun onSuccessGetHeadlineNews(articleList: List<Article>?) {
        loading?.dismiss()
        mPager = findViewById(R.id.pager) as ViewPager
        mPager?.setAdapter(ImageAdapter(this, articleList as ArrayList<Article>?))

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = getResources().getDisplayMetrics().density

        //Set circle indicator radius
        indicator.radius = 5 * density

        NUM_PAGES = 5

        val handler = Handler()
        val Update = Runnable {
            if (currentPage === NUM_PAGES) {
                currentPage = 0
            }
            mPager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 5000, 5000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    override fun onErrorGetNews(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        newsPresenter!!.unsubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView?
        }
        if (searchView != null ) {
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()))

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    newsAdapter?.filter(query)
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_search ->
                // Not implemented here
                return false
            else -> {
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }


}




