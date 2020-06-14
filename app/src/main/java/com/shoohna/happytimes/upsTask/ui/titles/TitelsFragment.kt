package com.shoohna.happytimes.upsTask.ui.titles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.shoohna.happytimes.R
import com.shoohna.happytimes.databinding.ActivityMostPopularBinding
import com.shoohna.happytimes.databinding.FragmentTestBinding
import com.shoohna.happytimes.pojo.ups.Result
//import com.shoohna.happytimes.ui.home.ui.notification.NotificationRecyclerViewAdapter
//import com.shoohna.happytimes.ui.home.ui.notification.NotificationRecyclerViewAdapter
import org.koin.android.ext.android.inject
import java.lang.Exception


class TitelsFragment : Fragment() {


    lateinit var binding: FragmentTestBinding

    private val titelsViewModel: TitelsViewModel by inject()   // 1

    var data= MutableLiveData<List<Result>>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentTestBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
//        val model = ViewModelProvider(this).get(NotificationViewModel::class.java)

        binding.vm = titelsViewModel
        titelsViewModel.getTitles(requireActivity()).observe(viewLifecycleOwner, Observer {results ->
            data.value=results

            binding.titlesRecyclerViewId.adapter = TitlesAdapter(data, context?.applicationContext)
            Toast.makeText(context , "toast 212 "+data.value!!.size,Toast.LENGTH_LONG).show()

        })


        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}
