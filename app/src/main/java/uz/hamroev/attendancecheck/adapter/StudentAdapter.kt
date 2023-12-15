package uz.hamroev.attendancecheck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.attendancecheck.databinding.ItemStudentBinding
import uz.hamroev.attendancecheck.room.entity.Date
import uz.hamroev.attendancecheck.room.entity.Student

class StudentAdapter(
    var context: Context,
    var list: List<Student>,
    var onNavClickListener: StudentAdapter.OnDataClickListener,
) : RecyclerView.Adapter<StudentAdapter.VhPredlog>() {


    inner class VhPredlog(var itemDataBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {


        fun onBind(student: Student, position: Int) {
            itemDataBinding.tvData.text = "${student.name} ${student.surname}"
            itemDataBinding.tvGroup.text = student.group

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPredlog {
        return VhPredlog(ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VhPredlog, position: Int) {
        return holder.onBind(list[position], position)
    }

    interface OnDataClickListener {
        fun onCLick(predlogEntity: Date, position: Int)

    }


}