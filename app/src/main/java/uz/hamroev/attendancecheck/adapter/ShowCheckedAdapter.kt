package uz.hamroev.attendancecheck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.attendancecheck.R
import uz.hamroev.attendancecheck.databinding.ItemShowStudentBinding
import uz.hamroev.attendancecheck.room.entity.Check

class ShowCheckedAdapter(
    var list: List<Check>,
) : RecyclerView.Adapter<ShowCheckedAdapter.VhPredlog>() {


    inner class VhPredlog(var itemDataBinding: ItemShowStudentBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {


        fun onBind(check: Check, position: Int) {
            itemDataBinding.tvData.text = "${check.name} ${check.surname}"
            itemDataBinding.tvGroup.text = check.group


            if (check.has_student == "yes") {
                itemDataBinding.ivCheck.setImageResource(R.drawable.baseline_circle_24)
            } else {
                itemDataBinding.ivCheck.setImageResource(R.drawable.baseline_circle_24_red)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPredlog {
        return VhPredlog(ItemShowStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VhPredlog, position: Int) {
        return holder.onBind(list[position], position)
    }

    interface OnDataClickListener {
        fun onCLick(check: Check, position: Int)

        fun onAdd(check: Check, position: Int)

        fun onDelete(check: Check, position: Int)

    }


}