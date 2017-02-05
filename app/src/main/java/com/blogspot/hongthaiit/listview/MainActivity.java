package com.blogspot.hongthaiit.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {

	private ListView listView;
	private List<Model> modelLinkedList=new LinkedList<>() ;
	private static final String Root_url="http://api.androidhive.info";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.list);
		insertUser();

	}

	private void insertUser() {

		final RestAdapter[] adapter = {new RestAdapter.Builder().setEndpoint(Root_url).build()};

		Apiservice api = adapter[0].create(Apiservice.class);
		         api.Mymeth(new Callback<JsonArray>() {
					 @Override
					 public void success(JsonArray jsonElements, Response response) {
						 for (int i = 0; i < jsonElements.size(); i++) {
							  JsonObject jsonObject1= jsonElements.get(i).getAsJsonObject();
							           String tittle=   jsonObject1.get("title").getAsString();
							           String image=   jsonObject1.get("image").getAsString();
							 Model model=new Model();
							model.setName(tittle);
						      model.setGender(image);
							  modelLinkedList.add(model);


						 }
						 CustomAdapter adapter=new CustomAdapter(getApplicationContext(),modelLinkedList);
					                    listView.setAdapter(adapter);

					 }

					 @Override
					 public void failure(RetrofitError error) {


						 Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
					 }
				 });






	}



}
