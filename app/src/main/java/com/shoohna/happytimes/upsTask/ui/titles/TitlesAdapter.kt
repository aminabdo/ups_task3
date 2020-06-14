package com.shoohna.happytimes.upsTask.ui.titles

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shoohna.happytimes.R
import com.shoohna.happytimes.databinding.ItemTitleBinding
import com.shoohna.happytimes.pojo.ups.Result
import com.shoohna.happytimes.upsTask.DetailsActivity

class TitlesAdapter (val data: LiveData<List<Result>>, private val context: Context?) : RecyclerView.Adapter<TitlesAdapter.ViewHolder>() {

    var c = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ,c!!)
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.value!![position])

    }

    class ViewHolder(private var binding: ItemTitleBinding , c:Context ) :
        RecyclerView.ViewHolder(binding.root) {
        var context = c

        fun bind(item: Result) {

            binding.model = item
            binding.executePendingBindings()


            binding.iv.setOnClickListener {
                var i:Intent = Intent(context , DetailsActivity::class.java)
                i.putExtra("key" , item.abstract+"\n"+item.adx_keywords+"\n"+item.section)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }
            binding.tvAuther.setOnClickListener {
                var i:Intent = Intent(context , DetailsActivity::class.java)
                i.putExtra("key" , item.abstract+"\n"+item.adx_keywords+"\n"+item.section)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }

            binding.tvDesc.setOnClickListener {
                var i:Intent = Intent(context , DetailsActivity::class.java)
                i.putExtra("key" , item.abstract+"\n"+item.adx_keywords+"\n"+item.section)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }
            binding.tvTitle.setOnClickListener {
                var i:Intent = Intent(context , DetailsActivity::class.java)
                i.putExtra("key" , item.abstract+"\n"+item.adx_keywords+"\n"+item.section)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }
            binding.tvDate.setOnClickListener {
                var i:Intent = Intent(context , DetailsActivity::class.java)
                i.putExtra("key" , item.abstract+"\n"+item.adx_keywords+"\n"+item.section)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i)
            }



        }

    }
}
