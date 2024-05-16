import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.vinz.latihanrecyclerview1.R
import com.vinz.latihanrecyclerview1.data.DataTempat

class HorizontalAdapter(
    private val dataTempatList: List<DataTempat>
) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {


    private lateinit var onItemClickCallback: HorizontalAdapter.OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_horizontal, parent, false)
        return ViewHolder(view)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: MaterialTextView = itemView.findViewById(R.id.hotel_name)
        val nameLoc: MaterialTextView = itemView.findViewById(R.id.loc)
        val image: ShapeableImageView = itemView.findViewById(R.id.player_picture)
        val icon: ShapeableImageView = itemView.findViewById(R.id.imageView2)
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataTempatList[position]

        // Set data ke komponen-komponen UI dalam PlaceHorizontalViewHolder
        holder.name.text = data.name
        holder.nameLoc.text = data.nameLoc
        holder.image.setImageResource(data.image)
        holder.icon.setImageResource(data.icon)


        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { /* Aksi yang diinginkan ketika item diklik */ }
    }

    override fun getItemCount(): Int {
        return dataTempatList.size
    }

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: HorizontalAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: DataTempat)
    }
}