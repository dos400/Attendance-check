package uz.hamroev.attendancecheck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.attendancecheck.R
import uz.hamroev.attendancecheck.databinding.FragmentAddStudentBinding
import uz.hamroev.attendancecheck.room.AppDatabase
import uz.hamroev.attendancecheck.room.entity.Student
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class AddStudentFragment : Fragment() {


    private lateinit var binding: FragmentAddStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddStudentBinding.inflate(layoutInflater)


        binding.saveButton.setOnClickListener {
            saveStudentData()
        }
        binding.menuButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.menuAllStudents.setOnClickListener {
            findNavController().navigate(R.id.studentListFragment)
        }



        return binding.root
    }

    private fun saveStudentData() {
        binding.apply {
            if (etName.text.toString().trim().isNotEmpty() &&
                etSurname.text.toString().trim().isNotEmpty() &&
                etGroup.text.toString().trim().isNotEmpty()
            ) {
                saveDataToDatabase()
            } else {
                MotionToast.createToast(
                    requireActivity(),
                    "Error",
                    "Please fill in all fields",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
                )
            }
        }
    }

    private fun saveDataToDatabase() {
        // save data to database
        val name = binding.etName.text.toString().trim()
        val surname = binding.etSurname.text.toString().trim()
        val group = binding.etGroup.text.toString().trim()

        val student = Student(name = name, surname = surname, group = group)
        AppDatabase.GET.getAppDatabase().studentDao().insertUser(student)



        MotionToast.createToast(
            requireActivity(),
            "Success",
            "Student added successfully!",
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
        )

        clearAllFields()

    }



    private fun clearAllFields() {
        binding.apply {
            etName.setText("")
            etSurname.setText("")
            etGroup.setText("")
        }
    }


}