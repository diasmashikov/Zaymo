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
import kotlinx.android.synthetic.main.item_current_borrowings.view.*
import kotlinx.android.synthetic.main.item_offer_lendings.view.*

class CurrentBorrowingsAdapter : RecyclerView.Adapter<CurrentBorrowingsAdapter.CurrentBorrowingsViewHolder>() {
    inner class CurrentBorrowingsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object: DiffUtil.ItemCallback<Credit>() {
        override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentBorrowingsViewHolder {
        return CurrentBorrowingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_current_borrowings,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CurrentBorrowingsViewHolder, position: Int) {
        val credit = differ.currentList[position]
        holder.itemView.apply {


            btnPayDebt.apply {
                setOnClickListener {
                    onBtnClickListener?.let { it(credit) }
                }
            }

        }
    }

    private var onBtnClickListener: ((Credit) -> Unit)? = null

    fun setOnBtnClickListener(listener: (Credit) -> Unit) {
        onBtnClickListener = listener
    }




}