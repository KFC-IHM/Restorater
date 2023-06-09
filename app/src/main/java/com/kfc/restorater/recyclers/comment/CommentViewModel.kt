package com.kfc.restorater.recyclers.comment

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.ReviewRepository

class CommentViewModel(val loginRepository: LoginRepository, val reviewRepository: ReviewRepository): BaseObservable() {}