package com.nithiann.thecircle.presentation.videopage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nithiann.thecircle.R
import com.nithiann.thecircle.domain.models.Message

class MessageAdapter(private val mList: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_messages, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ItemsViewModelMessages = mList[position]
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messageItem: TextView

        init {
            messageItem = itemView.findViewById(R.id.textView_msg_card)
        }
    }
}