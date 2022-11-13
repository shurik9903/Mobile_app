package com.example.mydialer.module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydialer.data.Contact

class ContactViewModel : ViewModel() {
    var contactList = MutableLiveData<List<Contact>>()
}