package com.tahaalangar.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchScreen : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var userDao: UserDao
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)
        // Initialize views
        searchView = findViewById(R.id.searchView)
        searchRecyclerView = findViewById(R.id.searchRecyclerView)

        // Initialize DAO
        userDao = AppDatabase.getDatabase(this).userDao()

        // Set up RecyclerView
        adapter = SearchAdapter()
        searchRecyclerView.layoutManager = LinearLayoutManager(this)
        searchRecyclerView.adapter = adapter
        // Set up SearchView
        // Set up SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchContacts(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchContacts(it) }
                return true
            }
        })
    }

    private fun searchContacts(query: String) {
        val searchQuery = "%$query%"
        userDao.searchContacts(searchQuery).observe(this) { contacts ->
            adapter.updateData(contacts)
        }
    }
}