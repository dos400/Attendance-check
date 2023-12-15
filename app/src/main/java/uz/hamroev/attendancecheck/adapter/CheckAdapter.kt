package uz.hamroev.attendancecheck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.attendancecheck.databinding.ItemCheckBinding
import uz.hamroev.attendancecheck.room.entity.Check

class CheckAdapter(
    var list: List<Check>,
    var onNavClickListener: OnDataClickListener,
) : RecyclerView.Adapter<CheckAdapter.VhPredlog>() {


    inner class VhPredlog(var itemDataBinding: ItemCheckBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {


        fun onBind(check: Check, position: Int) {
            itemDataBinding.tvData.text = "${check.name} ${check.surname}"
            itemDataBinding.tvGroup.text = check.group

            itemDataBinding.checkbox.isChecked = check.has_student == "yes"

            itemDataBinding.main.setOnClickListener {
//               if (itemDataBinding.checkbox.isChecked) {
//                   onNavClickListener.onAdd(student, position)
//               } else {
//                   onNavClickListener.onDelete(student, position)
//               }

                itemDataBinding.checkbox.isChecked = !itemDataBinding.checkbox.isChecked


            }

            itemDataBinding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (itemDataBinding.checkbox.isChecked) {
                    onNavClickListener.onAdd(check, position)
                } else {
                    onNavClickListener.onDelete(check, position)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPredlog {
        return VhPredlog(ItemCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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