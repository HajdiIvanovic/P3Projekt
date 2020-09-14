package ffos.hivanovicp3.redateljiapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


//Za zakomentiratu Filter metodu treba vratiti "implements Filterable"
public class RedateljiAdapter extends RecyclerView.Adapter<RedateljiAdapter.Red>  {

    private List<OntologijaRedatelji> podaci;
    private List<OntologijaRedatelji> noviPodaci;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public RedateljiAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        noviPodaci = new ArrayList<>(podaci);
        View view = mInflater.inflate(R.layout.red_u_listi, roditelj, false);
        return new Red(view);
    }


    @Override
    public void onBindViewHolder(Red red, int position) {
        OntologijaRedatelji r = podaci.get(position);

        red.redatelj.setText(r.getRedatelj());
        red.najpoznatijiRadovi.setText(r.getNajpoznatijiRadovi());
        red.opisZanimanja.setText(r.getOpisZanimanja());
        red.rdfOpis.setText(r.getRdfOpis());

    }

    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView redatelj;
        private TextView najpoznatijiRadovi;
        private TextView opisZanimanja;
        private TextView rdfOpis;

        Red(View itemView) {
            super(itemView);
            redatelj = itemView.findViewById(R.id.redatelj);
            najpoznatijiRadovi = itemView.findViewById(R.id.radovi);
            opisZanimanja = itemView.findViewById(R.id.zanimanje);
            rdfOpis = itemView.findViewById(R.id.rdfOpis);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    public OntologijaRedatelji getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<OntologijaRedatelji> itemList) {
        this.podaci = itemList;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


};



