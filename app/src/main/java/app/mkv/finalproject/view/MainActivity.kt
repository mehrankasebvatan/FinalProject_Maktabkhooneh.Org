package app.mkv.finalproject.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.mkv.finalproject.adapter.TodoAdapter
import app.mkv.finalproject.databinding.ActivityMainBinding
import app.mkv.finalproject.viewmodel.TodoViewModel


// منطق این برنامه این طوریه که اگه کامپلیت بود چک باس چکد میشه و یه خط روش میندازه
// وقتی روی چک باکس کلیک میکنه هم همین اتفاق میفته یعنی اگه چکد باشه آنچکد میکنه و خطش رو برمیداره
// اگه منطق دیگه ای باید پیاده بشه بگید اصلاحش کنم

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        event()
    }

    private fun event(){
        binding.apply {
            refresh.setOnRefreshListener {
                getData()
                refresh.isRefreshing = false
            }
        }
    }


    private fun getData() {

        binding.rvTodos.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.getTodos(this)
        todoViewModel.observeLiveData().observe(this) {

            val adapter = TodoAdapter(it)
            binding.apply {
                rvTodos.layoutManager = LinearLayoutManager(this@MainActivity)
                rvTodos.setItemViewCacheSize(100)
                rvTodos.adapter = adapter
                binding.rvTodos.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }

        }

    }


}