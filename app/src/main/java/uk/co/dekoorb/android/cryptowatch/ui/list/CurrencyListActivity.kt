package uk.co.dekoorb.android.cryptowatch.ui.list

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.detail.CurrencyDetailActivity

class CurrencyListActivity : AppCompatActivity(),
        CurrencyListFragment.Companion.CurrencyListFragmentActions {
    
    private var mListFragment: CurrencyListFragment? = null

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val name = intent.getStringExtra(SearchManager.QUERY)
            mListFragment?.setFilter(name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        if (savedInstanceState == null) {
            mListFragment = CurrencyListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.currency_list_frame, mListFragment, CurrencyListFragment.TAG)
                    .commit()
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
        val searchView: SearchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    mListFragment?.setFilter("")
                }
                return false
            }
        })

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
