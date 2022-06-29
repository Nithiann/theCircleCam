package com.nithiann.thecircle.presentation.videopage

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nithiann.thecircle.R
import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.models.MessageList
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MessageFragment: Fragment() {
    private lateinit var viewModel: VideoPageViewModel
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MessageAdapter.ViewHolder>? = null

    // TODO: make sure data is filled with messages
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = inflater!!.inflate(R.layout.message_fragment, container, false)
        textView = inflater.findViewById(R.id.messageText)
        recyclerView = inflater.findViewById(R.id.recycler_view)
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
        viewModel.state.value
    }


    override fun onStart() {
        super.onStart()

        val chat = viewModel.state.value
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            println("-=-=-=-=-=-=-=-=-=-=-=-=-=- " + chat.messages)
            adapter = MessageAdapter(chat.messages)
            adapter?.notifyDataSetChanged()
        }
    }
}