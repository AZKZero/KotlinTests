package com.zero.kotlintests.ui.f1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zero.kotlintests.databinding.Frag1FragmentBinding
import kotlinx.android.synthetic.main.frag1_fragment.view.*

class Frag1 : Fragment() {

    companion object {
        fun newInstance() = Frag1()
    }

    private lateinit var viewModel: Frag1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val x = Frag1FragmentBinding.inflate(inflater)

        x.frameLayout.materialButton.setOnClickListener {
            navigate(1)
        }

        x.frameLayout.materialButton2.setOnClickListener {
            navigate(2)
        }

        return x.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Frag1ViewModel::class.java)
    }

    private fun navigate(int: Int) {
        findNavController().navigate(
            Frag1Directions.actionFrag1ToFrag2().apply { operationType = int })
    }
}