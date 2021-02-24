package tj.iskandar.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

//    private static String FETCHURL = "https://api.androidhive.info/json/shimmer/menu.php";
    private static String URL = "https://guidebook.com/service/v2/upcomingGuides/";
    List<Repo> recipes;
    private RecyclerView recyclerview;
    private List<Repo> arrayList;
    private CustomRecyclerview adapter;
    private ProgressBar pb;
    Repo fields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                //
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                //
            }
        }
        };

//Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        recyclerview = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        adapter = new CustomRecyclerview(this, arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(adapter);
        fetchfromServer();

//        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && arrayList != null) {
//            fetchfromServer();
//        } else {
//
//
//            fetchfromRoom();
//        }


    }




//    private void fetchfromRoom() {
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                List<Recipe> recipeList = DatabaseClient.getInstance(MainActivity.this).getAppDatabase().recipeDao().getAll();
//                arrayList.clear();
//                for (Recipe recipe: recipeList) {
//                    Repo repo = new Repo(recipe.get,
//                            recipe.getName(),
//                            recipe.getDescription(),
//                            recipe.getPrice(),
//                            recipe.getThumbnail(),
//                            recipe.getChef(),
//                            recipe.getTimestamp());
//                    arrayList.add(repo);
//                }
//                // refreshing recycler view
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
//        thread.start();
//
//
//    }

    private void fetchfromServer() {
        pb.setVisibility(View.VISIBLE);

//        JsonArrayRequest request = new JsonArrayRequest(FETCHURL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response == null) {
//                            pb.setVisibility(View.GONE);
//                            Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//                        recipes = new Gson().fromJson(response.toString(), new TypeToken<List<Repo>>() {
//                        }.getType());
//
//                        // adding data to cart list
//                        arrayList.clear();
//                        arrayList.addAll(recipes);
//
//
//                        // refreshing recycler view
//                        adapter.notifyDataSetChanged();
//
//                        pb.setVisibility(View.GONE);
//
//                        saveTask();
//
//
//                    }
//                }
        final StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {

                        JSONObject content = data.getJSONObject(i);

                        String name = content.getString("name");
                        String url = content.getString("url");
                        String icon = content.getString("icon");
                        String endDate = content.getString("endDate");
                        String startDate = content.getString("startDate");
                        String objType = content.getString("objType");
                        String loginRequired = content.getString("loginRequired");
                        Log.e("name1", name);


                        fields = new Repo();
                        fields.setName(name);
                        fields.setEndDate(endDate);
                        fields.setIcon(icon);
                        fields.setStartDate(startDate);
                        fields.setUrl(url);
                        fields.setObjType(objType);
                        fields.setLoginRequired(loginRequired);


                        arrayList.add(fields);
                    }

                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
//                    saveTask();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                pb.setVisibility(View.GONE);
                Log.e("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        request.setShouldCache(false);

        requestQueue.add(request);
    }


    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                for (int i = 0; i < recipes.size(); i++) {
                    Recipe recipe= new  Recipe();
                    recipe.setName(recipes.get(i).getName());
                    recipe.setDescription(recipes.get(i).getEndDate());
                    recipe.setPrice(recipes.get(i).getStartDate());
                    recipe.setThumbnail(recipes.get(i).getIcon());
                    recipe.setChef(recipes.get(i).getObjType());
                    recipe.setTimestamp(recipes.get(i).getUrl());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().recipeDao().insert(recipe);
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

}
