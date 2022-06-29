package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nithiann.thecircle.R
import dagger.hilt.android.AndroidEntryPoint


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
        chat.messages?.messages?.forEach() { message ->
            println(message ?: "no message")
            textView.text = message.msg
            println("+++++++++++++12345 " + message.msg)
        }
        return inflater

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val chat = viewModel.state.value
        chat.messages?.messages?.forEach() { message ->
            println(message ?: "no message")
            textView.text = message.msg
            println("+++++++++++++12345 " + message.msg)
        }
        println("+++++++++++++-=-=-=- " + chat)
        textView.text = chat.toString()
    }
}