package com.borombo.sandboxapp.retrofit.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitActionAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitPostsAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitTodosAdapter;
import com.borombo.sandboxapp.retrofit.adapters.RetrofitUsersAdpater;
import com.borombo.sandboxapp.retrofit.model.JSPPost;
import com.borombo.sandboxapp.retrofit.model.JSPTodo;
import com.borombo.sandboxapp.retrofit.model.JSPUser;
import com.borombo.sandboxapp.retrofit.services.JSONPlaceholderService;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitGetActionResultActivity extends CommonActivity {

    @BindView(R.id.actionResultRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_get_action_result);

        setUpActionBar(getString(R.string.retrofit));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrofit init
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONPlaceholderService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Get the service which will be used
        JSONPlaceholderService service = retrofit.create(JSONPlaceholderService.class);

        String action = getIntent().getStringExtra(RetrofitActionAdapter.EXTRA_NAME);

        switch (action){
            case "/GET : Posts":
                // Retrieve the list of posts
                Call<List<JSPPost>> posts = service.listPosts();
                posts.enqueue(new Callback<List<JSPPost>>() {
                    @Override
                    public void onResponse(Call<List<JSPPost>> call, Response<List<JSPPost>> response) {
                        // Fill the list with the datas
                        recyclerView.setAdapter(new RetrofitPostsAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPPost>> call, Throwable t) {
                        // TODO Implement code in case of failure
                    }
                });
                break;

            case "/GET : Todos":
                // Retrieve the list of todos
                Call<List<JSPTodo>> todos = service.listTodos();
                todos.enqueue(new Callback<List<JSPTodo>>() {
                    @Override
                    public void onResponse(Call<List<JSPTodo>> call, Response<List<JSPTodo>> response) {
                        // Fill the list with the datas
                        recyclerView.setAdapter(new RetrofitTodosAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPTodo>> call, Throwable t) {
                        // TODO Implement code in case of failure
                    }
                });
                break;

            case "/GET : Users":
                // Retrieve the list of users
                Call<List<JSPUser>> users = service.listUsers();
                users.enqueue(new Callback<List<JSPUser>>() {
                    @Override
                    public void onResponse(Call<List<JSPUser>> call, Response<List<JSPUser>> response) {
                        // Fill the list with the datas
                        recyclerView.setAdapter(new RetrofitUsersAdpater(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<JSPUser>> call, Throwable t) {
                        // TODO Implement code in case of failure
                    }
                });
                break;
            default:
                // TODO Implement code in case of the action is not recognized
                break;
        }

    }
}
