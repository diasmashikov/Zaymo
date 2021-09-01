package com.example.zaymo.adapters


import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zaymo.R
import com.example.zaymo.models.Credit
import kotlinx.android.synthetic.main.fragment_lending.view.*
import kotlinx.android.synthetic.main.item_offer_lendings.view.*


class OfferLendingsAdapter : RecyclerView.Adapter<OfferLendingsAdapter.OfferLendingsViewHolder>() {

    private var isSaved = false

    inner class OfferLendingsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object: DiffUtil.ItemCallback<Credit>() {
        override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferLendingsViewHolder {
        return OfferLendingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_offer_lendings,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: OfferLendingsViewHolder, position: Int) {
        val credit = differ.currentList[position]
        holder.itemView.apply {




            btnLend.apply {
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