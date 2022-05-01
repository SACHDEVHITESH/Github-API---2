package com.example.githubapiissues.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import githubapiissues.R

class IssuesListAdapter : RecyclerView.Adapter<IssuesListAdapter.IssuesListViewHolder>() {

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

            holder.txtTitle.text = it.title
            holder.txtUserName.text = it.user.login
            if (it.body != null && it.body.length > 200) {
                holder.txtDesc.text = it.body.substring(0, 200) +  context.getString(R.string.more)
            } else {
                holder.txtDesc.text = it.body
            }
            holder.txtUpdatedDate.text = context.getString(R.string.updated_at) + it.getUpdatedDateAsString()
        }
    }

    fun setData(dataPresent: List<IssuesModel>) {
        this.issueList = dataPresent.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    inner class IssuesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        val txtUserName: AppCompatTextView = itemView.findViewById(R.id.txtUserName)
        val txtTitle: AppCompatTextView = itemView.findViewById(R.id.txtTitle)
        val txtDesc: AppCompatTextView = itemView.findViewById(R.id.txtDesc)
        val txtUpdatedDate: AppCompatTextView = itemView.findViewById(R.id.txtUpdatedDate)
    }

}