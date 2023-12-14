package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.attendancecheck.databinding.FragmentShowStudentsBinding

class ShowStudentsFragment : Fragment() {


    private lateinit var binding: FragmentShowStudentsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShowStudentsBinding.inflate(layoutInflater)




        return binding.root
    }


}