package com.proprog.applicationtest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.proprog.applicationtest.data.model.Item
import com.proprog.applicationtest.databinding.StackListItemBinding

class StackPagingAdapter(private val context: Context) :
    PagingDataAdapter<Item, StackPagingAdapter.ViewHolder>(DataDifferntiator) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object DataDifferntiator : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.answerId == newItem.answerId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    private lateinit var stackListItemBinding: StackListItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        stackListItemBinding = StackListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val view = stackListItemBinding.root
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        stackListItemBinding.tvUserId.text = item?.answerId.toString()

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(stackListItemBinding.root)
            .load(item?.owner?.profileImage)
            .placeholder(circularProgressDrawable)
            .into(stackListItemBinding.ivUserProfile)
    }

}