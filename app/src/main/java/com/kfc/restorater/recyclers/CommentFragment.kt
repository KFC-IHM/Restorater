package com.kfc.restorater.recyclers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.model.review.Review
import com.kfc.restorater.factory.ViewModelFactory

class CommentFragment : Fragment() {

    private lateinit var commentViewModel: CommentViewModel

    private var displayedReviews: List<Review> = listOf(
        Review(1, "Good", "Good", 5, 1, 1)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment_list, container, false)

        commentViewModel = ViewModelFactory.create(CommentViewModel::class.java)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                val reviews = commentViewModel.loginRepository.userData.get()?.review_set

                if (reviews != null) {
                    displayedReviews = reviews
                }

                adapter = CommentRecyclerViewAdapter(displayedReviews)
            }
        }

        return view
    }
}