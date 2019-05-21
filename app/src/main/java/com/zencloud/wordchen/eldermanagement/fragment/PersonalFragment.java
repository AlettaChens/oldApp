package com.zencloud.wordchen.eldermanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.energy.android.lib.base.BaseFragment;
import com.zencloud.wordchen.eldermanagement.R;
import com.zencloud.wordchen.eldermanagement.activity.OrderListActivity;
import com.zencloud.wordchen.eldermanagement.activity.PersonInfoActivity;
import com.zencloud.wordchen.eldermanagement.activity.SettingsActivity;
import com.zencloud.wordchen.eldermanagement.common.OldSp;
import com.zencloud.wordchen.eldermanagement.utils.GlideX;
import com.zencloud.wordchen.eldermanagement.utils.MessageUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import de.hdodenhof.circleimageview.CircleImageView;

@ContentView(R.layout.fragment_personalpage)
public class PersonalFragment extends BaseFragment {

	@ViewInject(R.id.iv_to_settings)
	private ImageView iv_to_settings;
	@ViewInject(R.id.iv_to_person_message)
	private ImageView iv_to_person_message;
	@ViewInject(R.id.user_pic)
	private CircleImageView user_pic;
	@ViewInject(R.id.user_name)
	private TextView user_name;
	@ViewInject(R.id.phone)
	private TextView phone;
	@ViewInject(R.id.img_unfinished)
	private ImageView img_unfinished;
	@ViewInject(R.id.img_finished)
	private ImageView img_finished;
	@ViewInject(R.id.user_address)
	private TextView user_address;
	private OldSp oldSp;


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		oldSp=new OldSp(getContext());
	}


	@Override
	public void onResume() {
		super.onResume();
		if(!TextUtils.isEmpty(oldSp.getNickName())){
			user_name.setText(oldSp.getNickName());
		}
		if(!TextUtils.isEmpty(oldSp.getPhone())){
			phone.setText(oldSp.getPhone());
		}
		if(!TextUtils.isEmpty(oldSp.getAddress())){
			user_address.setText(oldSp.getAddress());
		}
		GlideX.getInstance().loadCircleImage(getContext(), oldSp.getImage_url(), user_pic);
	}



	@Event(value = {R.id.iv_to_settings, R.id.iv_to_person_message, R.id.img_unfinished, R.id.img_finished})
	private void OnPersonClicked(View view) {
		Intent intent;
		switch (view.getId()) {
			case R.id.iv_to_person_message:
				intent = new Intent(getActivity(), PersonInfoActivity.class);
				startActivity(intent);
				break;
			case R.id.iv_to_settings:
				intent = new Intent(getActivity(), SettingsActivity.class);
				startActivity(intent);
				break;

			case R.id.img_unfinished:
				intent = new Intent(getActivity(), OrderListActivity.class);
				intent.putExtra("title", "未完成");
				startActivity(intent);
				break;

			case R.id.img_finished:
				intent = new Intent(getActivity(), OrderListActivity.class);
				intent.putExtra("title", "完成");
				startActivity(intent);
				break;
		}
	}
}
