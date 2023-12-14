package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.attendancecheck.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {


    private lateinit var binding: FragmentCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckBinding.inflate(layoutInflater)




        return binding.root
    }


}