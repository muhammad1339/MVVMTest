package com.proprog.applicationtest.ui.main.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.proprog.applicationtest.R
import com.proprog.applicationtest.data.model.Item
import com.proprog.applicationtest.databinding.StackListItemBinding
import com.squareup.picasso.Picasso

class StackAnswersAdapter(private val context: Context) :
    RecyclerView.Adapter<StackAnswersAdapter.ViewHolder>() {
    private var stackListAdapter: MutableList<Item> = ArrayList()
    private val TAG = "StackAnswersAdapter"
    fun updateList(stackList: List<Item>) {
        stackListAdapter.clear()
        stackListAdapter.addAll(stackList)
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
        val item = stackListAdapter[position]
        stackListItemBinding.tvUserId.text = item.owner.displayName

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(stackListItemBinding.root)
            .load(item.owner.profileImage)
            .placeholder(circularProgressDrawable)
            .into(stackListItemBinding.ivUserProfile)
    }

    override fun getItemCount(): Int = stackListAdapter.size
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}