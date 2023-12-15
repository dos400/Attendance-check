package uz.hamroev.attendancecheck.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.hamroev.attendancecheck.adapter.CheckAdapter
import uz.hamroev.attendancecheck.adapter.DataAdapter
import uz.hamroev.attendancecheck.cache.Cache
import uz.hamroev.attendancecheck.databinding.DialogDateBinding
import uz.hamroev.attendancecheck.databinding.FragmentCheckBinding
import uz.hamroev.attendancecheck.room.AppDatabase
import uz.hamroev.attendancecheck.room.entity.Check
import uz.hamroev.attendancecheck.room.entity.Date
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class CheckFragment : Fragment() {


    private lateinit var binding: FragmentCheckBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckBinding.inflate(layoutInflater)

        Cache.date = "dd/mm/yyyy"


        binding.menuButton.setOnClickListener {
            findNavController().popBackStack()
        }

        showToastForSelectDate()
        pickDate()

        binding.resultButton.setOnClickListener {

        }



        binding.selectDateLinear.setOnClickListener {
            pickDate()
        }





        return binding.root
    }

    private fun showToastForSelectDate() {
        if (Cache.date == "dd/mm/yyyy") {
            MotionToast.createToast(
                requireActivity(),
                "Error",
                "Please select date",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.SHORT_DURATION,
                ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
            )
        }

    }


    private fun pickDate() {
        val alertDialog = android.app.AlertDialog.Builder(binding.root.context)
        val dialog = alertDialog.create()
        val bindingLanguage =
            DialogDateBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setView(bindingLanguage.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)


        val allDates = AppDatabase.GET.getAppDatabase().dateDao().getAllDates()

        if (allDates.isEmpty()) {
            MotionToast.createToast(
                requireActivity(),
                "Error",
                "No Date yet!",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
            )
        } else {
            val dataAdapter = DataAdapter(requireContext(), allDates.reversed(), object : DataAdapter.OnDataClickListener {
                override fun onCLick(date: Date, position: Int) {
                    // show next page about student attendance in this date
                    // need open new fragment
                    Toast.makeText(requireContext(), "${date.date}", Toast.LENGTH_SHORT).show()
                    Cache.date = date.date
                    binding.tvDate.text = Cache.date
                    getDataStarted()
                    dialog.dismiss()
                }
            })
            bindingLanguage.rvDate.adapter = dataAdapter
        }

        dialog.show()


    }

    fun getDataStarted() {
        lifecycleScope.launch(Dispatchers.IO) {

            val allStudentsList = AppDatabase.GET.getAppDatabase().studentDao().getAllUsers()
            val allDatesList = AppDatabase.GET.getAppDatabase().dateDao().getAllDates()


            if (allStudentsList.isEmpty()) {

                MotionToast.createToast(
                    requireActivity(),
                    "Error",
                    "Did not add Students",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
                )


            } else {

                val allChecks1 = AppDatabase.GET.getAppDatabase().checkDao().getAllChecks()
                if (allChecks1.isEmpty()) {
                    for (student in allStudentsList) {
                        AppDatabase.GET.getAppDatabase().checkDao().insertCheck(Check(student.id, Cache.date!!, "no", student.name, student.surname, student.group))

                    }
                } else {
                    if (allChecks1.filter { Cache.date == it.date }.isEmpty()) {
                        for (student in allStudentsList) {
                            AppDatabase.GET.getAppDatabase().checkDao().insertCheck(Check(student.id, Cache.date!!, "no", student.name, student.surname, student.group))
                        }
                        //new students
                    } else {
                        //use old inserted students
                    }


                }

                loadAdapter()

            }


        }
    }

    private fun addStudentsToThisDate() {
        lifecycleScope.launch(Dispatchers.IO) {

            loadAdapter()


        }


    }


    private fun loadAdapter() {
        lifecycleScope.launch(Dispatchers.Main) {
            val allChecksList = AppDatabase.GET.getAppDatabase().checkDao().getAllChecksByDate(Cache.date!!)
            val checkAdapter = CheckAdapter(allChecksList.reversed(), object : CheckAdapter.OnDataClickListener {
                override fun onCLick(check: Check, position: Int) {

                }

                override fun onAdd(check: Check, position: Int) {
                    check.has_student = "yes"
                    AppDatabase.GET.getAppDatabase().checkDao().updateCheck(check)
                }

                override fun onDelete(check: Check, position: Int) {
                    check.has_student = "no"
                    AppDatabase.GET.getAppDatabase().checkDao().updateCheck(check)
                }
            })
            binding.rvCheck.adapter = checkAdapter
        }
    }


    private fun loadStudentData() {
//        databaseViewModel.studentDataList.observe(viewLifecycleOwner) { list ->
//            val checkAdapter = CheckAdapter(list.reversed(), object : CheckAdapter.OnDataClickListener {
//                override fun onCLick(data: Student, position: Int) {
//                    //dataList.add(Check(System.currentTimeMillis(), data.id, Cache.date_id.toLong(),  ))
//                }
//
//                override fun onAdd(data: Student, position: Int) {
//                    dataList.add(Check())
//                }
//
//                override fun onDelete(data: Student, position: Int) {
//                    val studentId = data.id
//                    val index = dataList.indexOfFirst { it.student_id == studentId }
//                    dataList.removeAt(index)
//                }
//
//            })
//            binding.rvCheck.adapter = checkAdapter
//        }

    }

}