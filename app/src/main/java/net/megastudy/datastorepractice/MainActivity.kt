package net.megastudy.datastorepractice

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.megastudy.datastorepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataStore : DataStoreUtil

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataStore = DataStoreUtil(this)

        binding.btSave.setOnClickListener {
            CoroutineScope(IO).launch {
                dataStore.SaveId(
                    binding.editId.text.toString().trim()
                )
            }
        }

        lifecycle.coroutineScope.launchWhenCreated {
            dataStore.getId().collect {
                binding.tvGetId.text = it
                binding.editId.hint = it
            }
        }

    }//onCreate
}