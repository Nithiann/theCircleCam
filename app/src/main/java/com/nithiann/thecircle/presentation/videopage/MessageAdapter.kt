package com.nithiann.thecircle.presentation.videopage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nithiann.thecircle.R
import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.models.MessageList

class MessageAdapter(private val mList: MessageList?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var messageItem: TextView;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_messages, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ItemsViewModelMessages = mList!!.messages[position]
        messageItem.text = ItemsViewModelMessages.msg
    }

    override fun getItemCount(): Int {
        return mList?.messages?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            messageItem = itemView.findViewById(R.id.textView_msg_card)
        }
    }

}