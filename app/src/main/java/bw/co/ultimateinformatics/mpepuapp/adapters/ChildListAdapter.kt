package bw.co.ultimateinformatics.mpepuapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import bw.co.ultimateinformatics.mpepuapp.R
import bw.co.ultimateinformatics.mpepuapp.models.ChildModel

private class ChildListAdapter(context: Context) : RecyclerView.Adapter<ChildListAdapter.ChildViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mChildren: List<ChildModel>? = null //cached copy of all children


    internal inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val childNameTV: TextView

        init {
            childNameTV = itemView.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChildViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false)

        return ChildViewHolder(itemView)
    }

    override fun onBindViewHolder(childViewHolder: ChildViewHolder, position: Int) {
        if (mChildren != null) {
            val current = mChildren!![position]
            childViewHolder.childNameTV.text = current.firstName + " " + current.lastName
            Log.d("Child Adapter", current.firstName + " " + current.lastName)
        } else {
            childViewHolder.childNameTV.text = "No Child"

        }


    }

    fun setChildren(children: List<ChildModel>) {
        mChildren = children
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (mChildren != null)
            mChildren!!.size
        else
            0
    }
}
