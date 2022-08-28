package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.wordsapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private var isLinearLayoutManager = true
    private lateinit var recyclerView: RecyclerView
    private lateinit var letterId: String
    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        arguments?.let {
            letterId = it.getString(LETTER).toString()

        }
    }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentWordListBinding.inflate(inflater, container, false)
            return binding.root
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = WordAdapter(letterId, requireContext())


            recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Retrieve a binding object that allows you to refer to views by id name
//        // Names are converted from snake case to camel case.
//        // For example, a View with the id word_one is referenced as binding.wordOne
//        val binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Retrieve the LETTER from the Intent extras
//        // intent.extras.getString returns String? (String or null)
//        // so toString() guarantees that the value will be a String
//        val letterId = intent?.extras?.getString(DetailActivity.LETTER).toString()
//
//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = WordAdapter(letterId, this)
//
//        // Adds a [DividerItemDecoration] between items
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )
//
//        title = getString(R.string.detail_prefix) + " " + letterId
//    }
//}
    }
