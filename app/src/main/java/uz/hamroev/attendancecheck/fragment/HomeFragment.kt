package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.attendancecheck.R
import uz.hamroev.attendancecheck.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)



        binding.layoutAddStudent.setOnClickListener {
            findNavController().navigate(R.id.addStudentFragment)
        }

        binding.includeCourse.main.setOnClickListener {
            findNavController().navigate(R.id.checkFragment)
        }

        binding.includeCourse2.main.setOnClickListener {
            findNavController().navigate(R.id.checkFragment)
        }

        binding.menuCalendar.setOnClickListener {
            findNavController().navigate(R.id.calendarFragment)
        }






        return binding.root
    }


}