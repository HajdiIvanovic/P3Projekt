package ffos.hivanovicp3.redateljiapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RedateljiAdapter redateljiAdapter;
    private RESTTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.ontologijaRedatelji);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        redateljiAdapter = new RedateljiAdapter(this);
        recyclerView.setAdapter(redateljiAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.search);

        searchView.setQueryHint("Traži..");
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                asyncTask = new RESTTask();
                if(query.toLowerCase().equals("redatelj")){
                    asyncTask.execute(getString(R.string.REST_URL));
                }else{
                    asyncTask.execute( "https://oziz.ffos.hr/nastava20192020/hivanovic_19/ontologija/search/" + query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private class RESTTask extends AsyncTask<String, Void, List<OntologijaRedatelji>> {
        protected List<OntologijaRedatelji> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<OntologijaRedatelji> vrati = null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<OntologijaRedatelji>>() {
                }.getType();
                vrati = new Gson().fromJson(reader, listType);
                reader.close();
                streamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vrati;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<OntologijaRedatelji> podaci) {
            redateljiAdapter.setPodaci(podaci);
            redateljiAdapter.notifyDataSetChanged();
        }

    }


}