package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nithiann.thecircle.R
import com.nithiann.thecircle.databinding.MessageFragmentBinding

class MessageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<MessageFragmentBinding>(inflater,
            R.layout.message_fragment,container,false)
        return binding.root
    }
}