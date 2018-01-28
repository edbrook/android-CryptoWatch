package uk.co.dekoorb.android.cryptowatch.ui.list

import android.app.SearchManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.detail.CurrencyDetailActivity

class CurrencyListActivity : AppCompatActivity(),
        CurrencyListFragment.Companion.CurrencyListFragmentActions {
    
    private var mListFragment: CurrencyListFragment? = null
    private var mSearchView: SearchView? = null
    private var mViewModel: CurrencyListViewModel? = null

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val name = intent.getStringExtra(SearchManager.QUERY)
            mListFragment?.setFilter(name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        mViewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)

        if (savedInstanceState == null) {
            mListFragment = CurrencyListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.currency_list_frame, mListFragment, CurrencyListFragment.TAG)
                    .commit()
        } else {
            mListFragment = supportFragmentManager
                    .findFragmentById(R.id.currency_list_frame) as CurrencyListFragment
        }

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.currency_list, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu?.findItem(R.id.action_search)
        mSearchView = searchMenuItem?.actionView as SearchView
        mSearchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        mSearchView?.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mListFragment?.setFilter(newText ?: "")
                return false
            }
        })

        val filter = mViewModel?.getFilter()
        if (!TextUtils.isEmpty(filter)) {
            searchMenuItem.expandActionView()
            mSearchView?.setQuery(filter, false)
            mSearchView?.clearFocus()
        }

        return true
    }

    override fun onCurrencyItemClick(currency: Currency) {
        val intent = CurrencyDetailActivity.getIntent(this, currency.id)
        startActivity(intent)
    }

    override fun onCurrencyItemLongClick(currency: Currency): Boolean {
        return false
    }
}
