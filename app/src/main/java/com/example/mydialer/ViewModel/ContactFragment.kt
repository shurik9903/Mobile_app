package com.example.mydialer.ViewModel

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.R
import com.example.mydialer.data.Contact
import com.example.mydialer.module.Adapter
import com.example.mydialer.module.getURLDataOk
import kotlin.concurrent.thread

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val textSearch: EditText? = activity?.findViewById(R.id.et_search)

        val recyclerView: RecyclerView? = view.findViewById(R.id.rView)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        val adapter = Adapter()
        recyclerView?.adapter = adapter

        var listContact = arrayListOf<Contact>()
        val findList = MutableLiveData<List<Contact>>()

        findList.observe(viewLifecycleOwner) { list ->
            println(adapter.itemCount)
            adapter.submitList(list)
        }

        textSearch?.doAfterTextChanged { search ->

            findList.value = listContact.filter { contact ->
                contact.name.contains(search.toString(), true) or
                        contact.phone.contains(search.toString(), true) or
                        contact.type.contains(search.toString(), true) or
                        search.toString().isEmpty()
            }


            val setSharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            setSharedPref?.let { shared ->
                with (shared.edit()) {
                    putString("SEARCH_FILTER", search.toString())
                    apply()
                }
            }

        }

        val okAdapter = fun(data: ArrayList<Contact>) {
            activity?.let {
                it.runOnUiThread() {
                    listContact = data
                    findList.value = data
                }
            }
        }

        getURLDataOk("https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download", okAdapter)

        return view
    }

}
