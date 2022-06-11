package com.example.projetlicence.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetlicence.Adapter.MessagesAdapter;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class MessageClientFragment extends Fragment {

    private  final List<Users>messageLists=new ArrayList<>();
    private String mobile;
    private String email;
    private String name;
    private String profile_pic;
    private String lastMessage="";
    private int unseeMessages=0;
    private String chatkey="";
    private String currentUserID="";
    private Boolean datset=false;

    private RecyclerView recyclerView_messages;
    private MessagesAdapter messagesAdapter;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://preventfoodwaste-75851-default-rtdb.europe-west1.firebasedatabase.app/");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_message_client, container, false);

        final CircleImageView userProfilePic=root.findViewById(R.id.userProfilePic);
        recyclerView_messages=root.findViewById(R.id.MessagesRecycleView);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("infoUser",MODE_PRIVATE);
        currentUserID=FirebaseAuth.getInstance().getCurrentUser().getUid();

        email=sharedPreferences.getString("email","error");
        name=sharedPreferences.getString("fullname","error");
        profile_pic=sharedPreferences.getString("profileimage", "https://firebasestorage.googleapis.com/v0/b/preventfoodwaste-75851.appspot.com/o/image%20seller%2FXgQ5ZRn1C0aSQiNwNnGVnadMcPP2?alt=media&token=3d5e2fbb-5c42-4dd8-ad6b-8eecef6743ed");

        recyclerView_messages.setHasFixedSize(true);
        recyclerView_messages.setLayoutManager(new LinearLayoutManager(getContext()));

        messagesAdapter=new MessagesAdapter(messageLists,getContext());

        recyclerView_messages.setAdapter(messagesAdapter);

        ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                final String pic_profil=snapshot.child("users").child(currentUserID).child("profileimage").getValue(String.class);

                if(!profile_pic.isEmpty()){
                    Picasso.get().load(pic_profil).placeholder(R.drawable.profile_picture).into(userProfilePic);
                }


                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                progressDialog.dismiss();

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                messageLists.clear();
                unseeMessages=0;
                lastMessage="";

                for(DataSnapshot dataSnapshot: snapshot.child("users").getChildren()){
                    final String getIdUser=dataSnapshot.getKey();


                    datset=false;
                    if(!getIdUser.equals(currentUserID)){

                        Users user = dataSnapshot.getValue(Users.class);
                        messageLists.add(user);


                        messagesAdapter.updateData(messageLists);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return root;
    }
}