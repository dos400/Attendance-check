package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.attendancecheck.databinding.FragmentAddStudentBinding

class AddStudentFragment : Fragment() {


    private lateinit var binding: FragmentAddStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddStudentBinding.inflate(layoutInflater)





        return binding.root
    }


}