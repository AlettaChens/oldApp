package com.zencloud.wordchen.eldermanagement.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.gizwits.energy.android.lib.base.BaseFragmentActivity;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.fragment.FirstFragment;
import com.zencloud.wordchen.eldermanagement.fragment.PersonalFragment;
import com.zencloud.wordchen.eldermanagement.fragment.ShoppingCarFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("enter2")!=null&&getIntent().getStringExtra("enter2").equals("shop")){
            showFragment(R.id.fl_exhibition_view, new ShoppingCarFragment());
        }else {
            showFragment(R.id.fl_exhibition_view, new FirstFragment());
        }
    }

    @Event(value = R.id.rg_tab, type = RadioGroup.OnCheckedChangeListener.class)
    private void OnMianClick(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_firstpage: {
                showFragment(R.id.fl_exhibition_view, new FirstFragment());
                break;
            }
            case R.id.rb_shoppingcarpage: {
                showFragment(R.id.fl_exhibition_view, new ShoppingCarFragment());
                break;
            }
            case R.id.rb_personalpage: {
                showFragment(R.id.fl_exhibition_view, new PersonalFragment());
                break;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
