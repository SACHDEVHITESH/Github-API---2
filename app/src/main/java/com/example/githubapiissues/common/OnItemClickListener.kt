package com.example.githubapiissues.common

interface OnItemClickListener<T> {
    fun onItemClick(position: Int, item: T)
}