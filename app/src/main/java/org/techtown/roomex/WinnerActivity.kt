package org.techtown.roomex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.roomex.databinding.ActivityMainBinding
import org.techtown.roomex.viewmodel.UserViewModel

class WinnerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userViewModel : UserViewModel
    private val adapter: MyAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        userViewModel = ViewModelProvider(this, UserViewModel.Factory(application)).get(UserViewModel::class.java)

        // 아이템을 가로로 하나씩 보여줌
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        // 어댑터 연결
        binding.recyclerView.adapter = adapter

        val cnt=adapter.itemCount
        val idx=(Math.random()*cnt).toInt()
        userViewModel.getUser(adapter.getId(idx)).observe(this, {
            adapter.setData(it)
        })
        setContentView(R.layout.activity_winner)
    }
}