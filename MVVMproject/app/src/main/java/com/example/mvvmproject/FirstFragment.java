package com.example.mvvmproject;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmproject.databinding.FragmentFirstBinding;

import java.util.Collections;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private UserRepository userRepository;
    private UserAdapter adapter;
    private UserViewModel userViewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        userRepository = new UserRepository(getActivity().getApplication());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View view = binding.getRoot();



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);

        adapter = new UserAdapter(Collections.emptyList());
        binding.recyclerView.setAdapter(adapter);
        updateUserList();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                userViewModel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                adapter.removeItem(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerView);

        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                //edit
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//                //userViewModel.update(user);

//                Bundle bundle = new Bundle();
//                bundle.putString("username", user.getName_surname());
//                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });


        return binding.getRoot();

    }

    private void updateUserList() {
        userRepository.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() > 0) {
                    if (adapter == null) {
                        adapter = new UserAdapter(Collections.emptyList());
                        binding.recyclerView.setAdapter(adapter);
                    } else adapter.setUsers(users);
                }
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        userViewModel.getAllLogedUsers().observe(this, new Observer<List<LogInUser>>() {
            @Override
            public void onChanged(List<LogInUser> logInUsers) {
                binding.logOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logOut(getActivity().getApplication());
                    }
                });
            }
        });


    }

    public void logOut(Application application) {
        userViewModel.delete(userViewModel.getAllLogedUsers().getValue().get(0));
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_LogIn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}