package com.kfc.restorater.recyclers.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.factory.ViewModelFactory

class CommentFragment : Fragment() {

    private lateinit var commentViewModel: CommentViewModel

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

                // When the user data is updated, update the adapter (reviews)
                commentViewModel.loginRepository.userData.addOnPropertyChangedCallback(object :
                    androidx.databinding.Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(
                        sender: androidx.databinding.Observable?,
                        propertyId: Int
                    ) {
                        commentViewModel.loginRepository.userData.get()?.let {
                            adapter = CommentRecyclerViewAdapter(it.review_set)
                        }
                    }
                })
            }
        }

        return view
    }
}