package com.example.eventexpandableview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventExpandableAdapter(val parentList: MutableList<ParentEvent>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.PARENT) {
            val rowView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_for_header_row, parent, false)
            HeaderViewHolder(rowView)
        } else {
            val rowView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_for_child_row, parent, false)
            ChildViewHolder(rowView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataList = parentList[position]

        if (dataList.type == Constants.PARENT) {
            holder as HeaderViewHolder
            holder.apply {
                txtNotificationHeader?.text = dataList.notificationHeader
                txtNotificationSubZone?.text = dataList.notificationSubZoneName
                txtNotificationDate?.text = dataList.notificationTimestamp?.let {
                    DateUtils.getDateString(
                        it
                    )
                }
                txtNotificationTime?.text = dataList.notificationTimestamp?.let {
                    DateUtils.getTimeString(
                        it
                    )
                }
                linParent?.setBackgroundColor(Color.parseColor(dataList.background))
                if (dataList.childList.isEmpty()) {
                    imgBtnExpand?.visibility = View.GONE
                    txtBadge?.visibility = View.GONE
                    linParent?.setOnClickListener(null)
                } else {
                    imgBtnExpand?.visibility = View.VISIBLE
                    txtBadge?.visibility = View.VISIBLE
                    txtBadge?.text = (dataList.childList.size + 1).toString()
                    if (dataList.isExpanded) {
                        imgBtnExpand?.setImageResource(R.mipmap.ic_arrrow_down_round)
                    } else {
                        imgBtnExpand?.setImageResource(R.mipmap.ic_arrrow_right_round)
                    }
                    linParent?.setOnClickListener {
                        expandOrCollapseParentItem(dataList, position)
                    }
                }
            }

        } else {
            holder as ChildViewHolder

            holder.apply {
                txtNotificationHeader?.text = dataList.notificationHeader
                txtNotificationSubZone?.text = dataList.notificationSubZoneName
                txtNotificationDate?.text = dataList.notificationTimestamp?.let {
                    DateUtils.getDateString(
                        it
                    )
                }
                txtNotificationTime?.text = dataList.notificationTimestamp?.let {
                    DateUtils.getTimeString(
                        it
                    )
                }
                linChild?.setBackgroundColor(Color.parseColor(dataList.background))
            }
        }

    }

    private fun expandOrCollapseParentItem(parentEvent: ParentEvent, position: Int) {
        if (parentEvent.isExpanded) {
            collapseParentRow(position)
        } else {
            expandParentRow(position)
        }
    }

    private fun expandParentRow(position: Int) {
        val currentEventRow = parentList[position]
        val events = currentEventRow.childList
        parentList[position].isExpanded = true
        var nextPosition = position
        if (currentEventRow.type == Constants.PARENT) {

            events.forEach { event ->
                val parentModel = ParentEvent(
                    event.notificationId,
                    event.notificationHeader, event.notificationSubZoneName,
                    event.notificationTimestamp,
                    Constants.CHILD, background = currentEventRow.background
                )
                parentList.add(++nextPosition, parentModel)
            }
            notifyDataSetChanged()
            mRecyclerView.scrollToPosition(nextPosition)
        }
    }

    private fun collapseParentRow(position: Int) {
        val currentEventRow = parentList[position]
        val events = currentEventRow.childList
        parentList[position].isExpanded = false
        if (parentList[position].type == Constants.PARENT) {
            events.forEach { _ ->
                parentList.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return parentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return parentList[position].type
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    class HeaderViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val txtNotificationHeader = row.findViewById(R.id.txtNotificationHeader) as TextView?
        val txtNotificationSubZone = row.findViewById(R.id.txtNotificationSubZone) as TextView?
        val txtNotificationDate = row.findViewById(R.id.txtNotificationDate) as TextView?
        val txtNotificationTime = row.findViewById(R.id.txtNotificationTime) as TextView?
        val imgBtnExpand = row.findViewById(R.id.imgBtnExpand) as ImageView?
        val linParent = row.findViewById(R.id.linParent) as LinearLayout?
        val txtBadge = row.findViewById(R.id.txtBadge) as TextView?
    }

    class ChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val txtNotificationHeader = row.findViewById(R.id.txtNotificationHeader) as TextView?
        val txtNotificationSubZone = row.findViewById(R.id.txtNotificationSubZone) as TextView?
        val txtNotificationDate = row.findViewById(R.id.txtNotificationDate) as TextView?
        val txtNotificationTime = row.findViewById(R.id.txtNotificationTime) as TextView?
        val linChild = row.findViewById(R.id.linChild) as LinearLayout?

    }

}