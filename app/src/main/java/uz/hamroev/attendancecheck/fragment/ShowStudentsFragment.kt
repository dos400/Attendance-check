package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.attendancecheck.adapter.ShowCheckedAdapter
import uz.hamroev.attendancecheck.cache.Cache
import uz.hamroev.attendancecheck.databinding.FragmentShowStudentsBinding
import uz.hamroev.attendancecheck.room.AppDatabase

class ShowStudentsFragment : Fragment() {


    private lateinit var binding: FragmentShowStudentsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShowStudentsBinding.inflate(layoutInflater)

        binding.menuButton.setOnClickListener {
            findNavController().popBackStack()
        }


        val allChecksByDate = AppDatabase.GET.getAppDatabase().checkDao().getAllChecksByDate(Cache.date!!)
        val showCheckAdapter = ShowCheckedAdapter(allChecksByDate)
        binding.rvCheck.adapter = showCheckAdapter



        return binding.root
    }


}