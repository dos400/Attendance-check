package uz.hamroev.attendancecheck.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.attendancecheck.R
import uz.hamroev.attendancecheck.adapter.DataAdapter
import uz.hamroev.attendancecheck.cache.Cache
import uz.hamroev.attendancecheck.databinding.FragmentCalendarBinding
import uz.hamroev.attendancecheck.room.AppDatabase
import uz.hamroev.attendancecheck.room.entity.Date
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.util.Calendar

class CalendarFragment : Fragment() {


    private lateinit var binding: FragmentCalendarBinding
    lateinit var dataAdapter: DataAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater)




        binding.menuButton.setOnClickListener {
            findNavController().popBackStack()
            //loadTimeData()
        }

        loadTimeData()

        binding.menuAddCalendar.setOnClickListener {
            showDatePickerDialog()
        }





        return binding.root
    }

    private fun loadTimeData() {

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
            dataAdapter = DataAdapter(requireContext(), allDates.reversed(), object : DataAdapter.OnDataClickListener {
                override fun onCLick(date: Date, position: Int) {
                    Cache.date = date.date
                    findNavController().navigate(R.id.showStudentsFragment)
                }

            })


            binding.rvData.adapter = dataAdapter
        }


    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Handle the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                //selectedDateButton.text = selectedDate

                AppDatabase.GET.getAppDatabase().dateDao().insertDate(Date(selectedDate))

                loadTimeData()
                MotionToast.createToast(
                    requireActivity(),
                    "Success",
                    "Data added successfully!",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), www.sanju.motiontoast.R.font.helvetica_regular)
                )

            },
            currentYear,
            currentMonth,
            currentDay
        )

        // Set a minimum date if needed
        // datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }


}