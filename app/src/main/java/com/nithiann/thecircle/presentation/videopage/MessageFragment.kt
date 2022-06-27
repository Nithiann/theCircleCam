package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.runtime.State
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.nithiann.thecircle.R
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception


@AndroidEntryPoint
class MessageFragment: Fragment() {
    private lateinit var viewModel: VideoPageViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = inflater!!.inflate(R.layout.message_fragment, container, false)
        textView = inflater.findViewById(R.id.messageText)
        viewModel = ViewModelProvider(activity!!).get(VideoPageViewModel::class.java)
        val chat = viewModel.state.value
        chat.messages?.forEach { message ->
            println(message ?: "no message")
        }
        return inflater

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}