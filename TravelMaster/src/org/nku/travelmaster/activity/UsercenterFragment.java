package org.nku.travelmaster.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.nku.travelmaster.internet.WebAccessUtils;
import org.nku.travelmaster.po.Users;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class UsercenterFragment extends Fragment {
	//定义视图组件
	private ImageView userAvatar;
	private TextView txtUserName;
	private TextView txtGender;
	private TextView txtRegisterDate;
	private TextView txtEmail;
	private TextView txtMark;
	
	private List<Map<String, ?>> lstButton;
	private ListView buttonViews;
	private Users user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent=getActivity().getIntent();
		user=(Users)intent.getSerializableExtra("user");
		if (user==null)
		{
			Intent intentLogin = new Intent();
			intentLogin.setClass(getActivity(), LoginActivity.class);
			startActivity(intentLogin);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view=inflater.inflate(R.layout.usercenter_layout, container,false);
		
		this.buttonViews = (ListView) view.findViewById(R.id.lstButtons);
		this.lstButton = AddButton();
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), this.lstButton,
				R.layout.lst_button_usercenter, new String[] { "txtBtnName" },
				new int[] { R.id.txtBtnName });
		this.buttonViews.setAdapter(adapter);
		this.buttonViews.setOnItemClickListener(new ItemOcl());
		
		// 初始化师徒组件
		userAvatar = (ImageView) view.findViewById(R.id.userAvatar);
		txtUserName = (TextView) view.findViewById(R.id.txtUserName);
		txtGender = (TextView) view.findViewById(R.id.txtGender);
		txtRegisterDate = (TextView) view.findViewById(R.id.txtRegisterDate);
		txtEmail = (TextView) view.findViewById(R.id.txtEmail);
		txtMark = (TextView) view.findViewById(R.id.txtMark);

		if(user!=null)
			ShowUserInfo();
		return view;
	}
	
	private void ShowUserInfo() {
		new GetAvatarTask().execute(user.getUavatar());
		
		txtUserName.setText(user.getUname());
		txtGender.setText(user.getUsex());
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		txtRegisterDate.setText(dateformat.format(user.getRegdate()));
		txtEmail.setText(user.getUemail());
		txtMark.setText(user.getUmark());
		
	}
	
	private class GetAvatarTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Bitmap bitmap = WebAccessUtils.DownloadImage("upload/avatar/"
					+ user.getUavatar());
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			userAvatar.setImageBitmap(result);
		}
		
	}
	
	private List<Map<String, ?>> AddButton() {
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		// 步骤4-2：创建一个列表中选项对象并实例化
		Map<String, Object> btn01 = new HashMap<String, Object>();
		btn01.put("mid", 1);
		btn01.put("txtBtnName", "我的旅程");

		Map<String, Object> btn02 = new HashMap<String, Object>();
		btn02.put("mid", 2);
		btn02.put("txtBtnName", "我的收藏");
		
		Map<String, Object> btn03 = new HashMap<String, Object>();
		btn03.put("mid", 3);
		btn03.put("txtBtnName", "我的帖子");
		
		Map<String, Object> btn04 = new HashMap<String, Object>();
		btn04.put("mid", 4);
		btn04.put("txtBtnName", "我的关注");
		
		lst.add(btn01);
		lst.add(btn02);
		lst.add(btn03);
		lst.add(btn04);
		
		return lst;
	}
	
	private class ItemOcl implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long arg3) {
			// TODO Auto-generated method stub
			// 步骤6-1：使用该方法的position参数获取选中的选项对象并赋值到Map集合中
			Map<String, ?> selectedItem = lstButton.get(position);
			int id=(Integer) selectedItem.get("mid");
			
			Intent intent = new Intent();
			switch (id) {
			case 1:
				intent.setClass(getActivity(), MytravellistActivity.class);
				break;
			case 2:
				intent.setClass(getActivity(), MycollectionActivity.class);
				break;
			case 3:
				intent.setClass(getActivity(), MypostActivity.class);
				break;
			case 4:
				intent.setClass(getActivity(), MyfriendActivity.class);
				break;
			default:
				break;
			}
			startActivity(intent);
		}
		
	}
}
