package com.example.zaymo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zaymo.R
import com.example.zaymo.models.Credit

class CurrentLendingsAdapter : RecyclerView.Adapter<CurrentLendingsAdapter.CurrentLendingsViewHolder>() {
    inner class CurrentLendingsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object: DiffUtil.ItemCallback<Credit>() {
        override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentLendingsViewHolder {
        return CurrentLendingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_current_lendings,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CurrentLendingsViewHolder, position: Int) {
        val credit = differ.currentList[position]
        holder.itemView.apply {


            setOnClickListener {
                onItemClickListener?.let { it(credit) }
            }

        }
    }

    private var onItemClickListener: ((Credit) -> Unit)? = null

    fun setOnItemClickListener(listener: (Credit) -> Unit) {
        onItemClickListener = listener
    }




}