package com.kfc.restorater.recyclers.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.factory.ViewModelFactory
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.ReviewApi
import com.kfc.restorater.repo.api.UserApi

class CommentFragment : Fragment() {

    private lateinit var commentViewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment_list, container, false)

        commentViewModel = ViewModelFactory.create(CommentViewModel::class.java)
        val reviewWebservice = RetrofitWebServiceFactory.build(ReviewApi::class.java)
        val userWebService = RetrofitWebServiceFactory.build(UserApi::class.java)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

                adapter = CommentRecyclerViewAdapter(
                    commentViewModel.loginRepository.userData.get()?.review_set ?: emptyList(),
                    commentViewModel.loginRepository,
                    commentViewModel.reviewRepository
                )

                userWebService.getUser(commentViewModel.loginRepository.user.get()?.userId ?: 0)
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe { user ->
                        Log.d("CommentFragment", "user: $user")
                        commentViewModel.loginRepository.userData.set(user)

                        if (!user.is_moderator) {
                            adapter = CommentRecyclerViewAdapter(
                                user.review_set,
                                commentViewModel.loginRepository,
                                commentViewModel.reviewRepository
                            )
                        } else {
                            reviewWebservice.getReviews()
                                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                                .subscribe { reviews ->
                                    Log.d("CommentFragment", "reviews: $reviews")
                                    adapter = CommentRecyclerViewAdapter(
                                        reviews,
                                        commentViewModel.loginRepository,
                                                commentViewModel.reviewRepository
                                    )
                                }
                        }

                    }

            }
        }
        return view
    }
}
