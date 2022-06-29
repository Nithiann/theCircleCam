package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.nithiann.thecircle.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MessageFragment: Fragment() {
    private lateinit var viewModel: VideoPageViewModel
    private lateinit var textView: TextView

    // TODO: make sure data is filled with messages
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.value
            }
        }
    }
}