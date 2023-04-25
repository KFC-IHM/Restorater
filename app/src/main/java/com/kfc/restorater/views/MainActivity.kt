package com.journaldev.androidmvvmbasics.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.kfc.restorater.R
import com.kfc.restorater.viewmodels.LoginViewModel
import com.kfc.restorater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.viewModel = LoginViewModel()
        activityMainBinding.executePendingBindings()
    }

    companion object {
        @BindingAdapter("toastMessage")
        fun runMe(view: View, message: String?) {
            if (message != null) Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
        }
    }
}