// Stanley_InClass07
// MainFragment.java
// Ken Stanley

package edu.uncc.inclass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uncc.inclass07.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private MessagesAdapter adapter;

    private final ArrayList<Message> messages = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new MessagesAdapter(requireActivity(), R.layout.message_list_item, messages);

        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener((parent, v, position, id) -> getParentFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailFragment.newInstance(messages.get(position)))
                .addToBackStack(null)
                .commit());
        binding.buttonAdd.setOnClickListener(view1 -> {
            EditText messageText = binding.editTextMessage;

            if (messageText.length() == 0) {
                Toast.makeText(getActivity(), "You must provide a message first.", Toast.LENGTH_LONG).show();
                return;
            }

            messages.add(new Message(messageText.getText().toString()));
            adapter.notifyDataSetChanged();
            binding.editTextMessage.setText("");
        });
    }

    public static class MessagesAdapter extends ArrayAdapter<Message> {
        public MessagesAdapter(@NonNull Context context, int resource, List<Message> object) {
            super(context, resource, object);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_list_item, parent, false);
            }

            Message message = getItem(position);

            TextView textViewMessageText = convertView.findViewById(R.id.textViewMessageText);
            TextView textViewMessageDate = convertView.findViewById(R.id.textViewMessageDate);

            textViewMessageText.setText(message.message);
            textViewMessageDate.setText(message.toFormat());

            return convertView;
        }
    }
}