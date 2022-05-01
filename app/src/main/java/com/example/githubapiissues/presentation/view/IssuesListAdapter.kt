package com.example.githubapiissues.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapiissues.common.OnItemClickListener
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import githubapiissues.R

class IssuesListAdapter (private val listener: OnItemClickListener<Any>? = null) :
    RecyclerView.Adapter<IssuesListAdapter.IssuesListViewHolder>() {

    private lateinit var context: Context
    private var issueList = mutableListOf<IssuesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_github_issues, parent, false)
        return IssuesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: IssuesListViewHolder, position: Int) {

        val issuesModelValue: IssuesModel = issueList[position]
        issuesModelValue.let {
            val user = it.user
            Glide.with(context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imgAvatar)

            if (it.title != null) {
                holder.txtTitle.visibility = View.VISIBLE
                holder.txtTitle.text = it.title
            } else {
                holder.txtTitle.visibility = View.GONE
            }


            if (it.user.login != null) {
                holder.txtUserName.visibility = View.VISIBLE
                holder.txtUserName.text = it.user.login
            } else {
                holder.txtUserName.visibility = View.GONE
            }

            if (it.body != null) {
                holder.txtDesc.visibility = View.VISIBLE
                if (it.body.length > 200) {
                    holder.txtDesc.text = it.body.substring(0, 200) +  context.getString(R.string.more)
                } else {
                    holder.txtDesc.text = it.body
                }
            } else {
                holder.txtDesc.visibility = View.GONE
            }

            if (it.getUpdatedDateAsString() != null) {
                holder.txtUpdatedDate.visibility = View.VISIBLE
                holder.txtUpdatedDate.text = context.getString(R.string.updated_at) + it.getUpdatedDateAsString()
            } else {
                holder.txtUpdatedDate.visibility = View.GONE
            }


        }
    }

    fun setData(dataPresent: List<IssuesModel>) {
        this.issueList = dataPresent.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    inner class IssuesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        val txtUserName: AppCompatTextView = itemView.findViewById(R.id.txtUserName)
        val txtTitle: AppCompatTextView = itemView.findViewById(R.id.txtTitle)
        val txtDesc: AppCompatTextView = itemView.findViewById(R.id.txtDesc)
        val txtUpdatedDate: AppCompatTextView = itemView.findViewById(R.id.txtUpdatedDate)
        private val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)

        init {
            mainLayout.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            listener?.onItemClick(adapterPosition, issueList[adapterPosition])
        }
    }

}