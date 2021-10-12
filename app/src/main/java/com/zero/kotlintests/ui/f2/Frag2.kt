package com.zero.kotlintests.ui.f2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.zero.kotlintests.CoroutineNetworkSimulator
import com.zero.kotlintests.databinding.Frag2FragmentBinding
import com.zero.kotlintests.operation.Operation1
import com.zero.kotlintests.operation.Operation2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Frag2 : Fragment() {

    companion object {
        fun newInstance() = Frag2()
    }

    private lateinit var viewModel: Frag2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val x = Frag2FragmentBinding.inflate(inflater)

        val args: Frag2Args by navArgs()

        lifecycleScope.launch(Dispatchers.IO) {
            CoroutineNetworkSimulator().simulateCallWithGenericCallback(
                this,
                8,
                5,
                if (args.operationType == 1) Operation1::class else Operation2::class
            ) { s1, s2, s3 ->
                x.textView2.text = s1
                x.textView3.text = s2
                x.textView4.text = s3
            }
        }

        return x.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Frag2ViewModel::class.java)
    }

}