package com.vivekvishwanath.ah_sprint2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.puppy_list_item.view.*

class PuppyListAdapter(val puppyList: MutableList<Puppy>): RecyclerView.Adapter<PuppyListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.puppy_list_item, parent, false) as View)
    }

    override fun getItemCount(): Int {
        return puppyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(puppyList[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val puppyImageView = view.puppy_image_view
        val puppyNameView = view.puppy_name_view
        val puppyItemParent = view.puppy_item_parent

        fun bindModel(puppy: Puppy) {
            puppyImageView.setImageResource(puppy.imageId)
            puppyNameView.text = puppy.breed
            if (puppy.isFavorite)
                puppyItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            else
                puppyItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
        }
    }

}