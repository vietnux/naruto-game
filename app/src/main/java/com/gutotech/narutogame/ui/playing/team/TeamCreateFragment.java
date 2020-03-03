package com.gutotech.narutogame.ui.playing.team;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gutotech.narutogame.R;
import com.gutotech.narutogame.databinding.FragmentTeamCreateBinding;
import com.gutotech.narutogame.ui.SectionFragment;
import com.gutotech.narutogame.ui.WarningDialog;
import com.gutotech.narutogame.utils.FragmentUtil;

public class TeamCreateFragment extends Fragment implements SectionFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTeamCreateBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_team_create, container, false);

        TeamCreateViewModel viewModel = ViewModelProviders.of(this)
                .get(TeamCreateViewModel.class);

        binding.setViewModel(viewModel);

        binding.msgLayout.titleTextView.setText(R.string.be_a_team_leader_title);
        binding.msgLayout.descriptionTextView.setText(R.string.be_a_team_leader_description);

        viewModel.getShowWarningDialogEvent().observe(this, this::showDialog);

        FragmentUtil.setSectionTitle(getActivity(), R.string.section_create_a_team);
        return binding.getRoot();
    }

    private void showDialog(@StringRes int resId) {
        WarningDialog dialogFragment = new WarningDialog();
        dialogFragment.setWarning(getString(resId));
        dialogFragment.show(getFragmentManager(), "WarningDialog");
    }

    @Override
    public int getDescription() {
        return R.string.being_a_leader;
    }
}
