package edu.curtin.foodapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FooterViewModel extends ViewModel {
    public MutableLiveData<String> title;

    public FooterViewModel() {
        title = new MutableLiveData<String>();
        title.setValue("Featured");
    }

    public String getTitle() { return title.getValue(); }

    public void setTitle(String title) { this.title.setValue(title); }
}