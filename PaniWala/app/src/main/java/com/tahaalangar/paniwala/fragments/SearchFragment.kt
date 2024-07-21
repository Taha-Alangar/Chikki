    package com.tahaalangar.paniwala.fragments

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.appcompat.widget.SearchView
    import androidx.recyclerview.widget.LinearLayoutManager

    import com.tahaalangar.paniwala.adapter.ProductsAdapter
    import com.tahaalangar.paniwala.databinding.FragmentSearchBinding
    import com.tahaalangar.paniwala.pojo.Products


    class SearchFragment : Fragment() {
        private lateinit var binding: FragmentSearchBinding
        private var products: List<Products> = emptyList()

        private lateinit var adapter: ProductsAdapter
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            binding=FragmentSearchBinding.inflate(inflater,container,false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            // Initialize RecyclerView and Adapter
// Initialize RecyclerView and Adapter
            adapter = ProductsAdapter(requireContext(), products, parentFragmentManager)
            binding.searchRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = adapter
            }


            // Configure SearchView
           binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
               override fun onQueryTextSubmit(query: String?): Boolean {
                   return false
               }

               override fun onQueryTextChange(newText: String?): Boolean {
                   newText?.let { adapter.filter(it) }
                   return true
               }
           })

        }

    }