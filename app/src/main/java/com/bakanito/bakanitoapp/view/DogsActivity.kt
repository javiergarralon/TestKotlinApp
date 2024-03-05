package com.bakanito.bakanitoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bakanito.bakanitoapp.R
import com.bakanito.bakanitoapp.adapter.DogAdapter
import com.bakanito.bakanitoapp.api.APIService
import com.bakanito.bakanitoapp.databinding.ActivityDogsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityDogsBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.spinnerDogs.onItemSelectedListener = this
        initSpinner()
        initRecyclerView()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.dog_breeds,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner.
            binding.spinnerDogs.adapter = adapter
        }
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = GridLayoutManager(this, 2)
        binding.rvDogs.adapter = adapter

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        if (!parent.getItemAtPosition(pos).toString().isNullOrEmpty()) {
            //Toast.makeText(this, parent.getItemAtPosition(pos).toString().lowercase().replace(' ','-'), Toast.LENGTH_SHORT).show()
            searchByName(parent.getItemAtPosition(pos).toString().lowercase().replace(' ','/'))
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback.
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val dogs = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val images = dogs?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }
}