package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.attendancecheck.adapter.StudentAdapter
import uz.hamroev.attendancecheck.databinding.FragmentStudentListBinding
import uz.hamroev.attendancecheck.room.AppDatabase
import uz.hamroev.attendancecheck.room.entity.Date
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentStudentListBinding.inflate(layoutInflater)

        // show students list


        binding.menuButton.setOnClickListener {
            findNavController().popBackStack()
        }

        loadAllStudents()



        return binding.root
    }

    private fun loadAllStudents() {

        val allUsers = AppDatabase.GET.getAppDatabase().studentDao().getAllUsers()

        if (allUsers.isEmpty()) {
            MotionToast.createToast(
                requireActivity(),
                "Error",
                "No Students yet!",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
            )
        } else {

            val studentAdapter = StudentAdapter(requireContext(), allUsers.reversed(), object : StudentAdapter.OnDataClickListener {
                override fun onCLick(predlogEntity: Date, position: Int) {

                }
            })

            binding.rvData.adapter = studentAdapter
        }

    }


}