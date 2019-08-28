package com.mustafayusef.wakely.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.ui.main.Adapters.MainScreenAdapter
import kotlinx.android.synthetic.main.main_screen_fragment.*

class MainScreen : Fragment(),MainScreenAdapter.OnNoteLisener {


    companion object {
        fun newInstance() = MainScreen()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
        ListMain?.layoutManager = LinearLayoutManager(context)
        ListMain?.adapter = MainScreenAdapter(context!!, this)


    }
    override fun onNoteClick(position: Int) {

    }
}
