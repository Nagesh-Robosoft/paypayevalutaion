package com.test.newsapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.newsapp.utils.Utils
import com.test.pokemongo.R
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private var isHideToolbarView = false

    private var mUrl: String? = null
    private var mImg: String? = null
    private var mTitle: String? = null
    private var mDate: String? = null
    private var mSource: String? = null
    private var mAuthor: String? = null
    private var mDescription: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_news_detail)

        val intent = intent
        mUrl = intent.getStringExtra("url")
        mImg = intent.getStringExtra("img")
        mTitle = intent.getStringExtra("title")
        mDate = intent.getStringExtra("date")
        mSource = intent.getStringExtra("source")
        mAuthor = intent.getStringExtra("author")
        mDescription = intent.getStringExtra("description")

        val requestOptions = RequestOptions()
        requestOptions.error(Utils.randomDrawableColor)
        Glide.with(this)
            .load(mImg)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivNewsImage)
        ivNewsHeader.text = mTitle
        if(mDescription?.trim()?.isNotEmpty() == true){
            tvNewsDescription.text = mDescription?.trim()
            tvNewsDescription.visibility = View.VISIBLE
        }else{
            tvNewsDescription.visibility = View.GONE
        }

        //initWebView(mUrl)
    }

/*    private fun initWebView(url: String?) {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url.orEmpty())
    }*/

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

/*    override fun onOffsetChanged(
        appBarLayout: AppBarLayout,
        verticalOffset: Int
    ) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage =
            Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()
        if (percentage == 1f && isHideToolbarView) {
            date_behavior!!.visibility = View.GONE
            title_appbar.visibility = View.VISIBLE
            isHideToolbarView = !isHideToolbarView
        } else if (percentage < 1f && !isHideToolbarView) {
            date_behavior!!.visibility = View.VISIBLE
            title_appbar.visibility = View.GONE
            isHideToolbarView = !isHideToolbarView
        }
    }*/

    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }*/
    /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
         val id = item.itemId
         if (id == R.id.view_web) {
             val i = Intent(Intent.ACTION_VIEW)
             i.data = Uri.parse(mUrl)
             startActivity(i)
             return true
         } else if (id == R.id.share) {
             try {
                 val i = Intent(Intent.ACTION_SEND)
                 i.type = "text/plan"
                 i.putExtra(Intent.EXTRA_SUBJECT, mSource)
                 val body =
                     "$mTitle\n$mUrl\nShare from the News App\n"
                 i.putExtra(Intent.EXTRA_TEXT, body)
                 startActivity(Intent.createChooser(i, "Share with :"))
             } catch (e: Exception) {
                 Toast.makeText(this, "Hmm.. Sorry, \nCannot be share", Toast.LENGTH_SHORT).show()
             }
         }
         return super.onOptionsItemSelected(item)
     }*/
}
