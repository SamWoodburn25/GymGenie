// Generated by view binder compiler. Do not edit!
package edu.quinnipiac.ser210.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import edu.quinnipiac.ser210.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAllWorkoutsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ExtendedFloatingActionButton FABSavedWorkoutsButton;

  @NonNull
  public final ExtendedFloatingActionButton FABback;

  @NonNull
  public final TextView muscleTextView;

  @NonNull
  public final RecyclerView recyclerView;

  private FragmentAllWorkoutsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ExtendedFloatingActionButton FABSavedWorkoutsButton,
      @NonNull ExtendedFloatingActionButton FABback, @NonNull TextView muscleTextView,
      @NonNull RecyclerView recyclerView) {
    this.rootView = rootView;
    this.FABSavedWorkoutsButton = FABSavedWorkoutsButton;
    this.FABback = FABback;
    this.muscleTextView = muscleTextView;
    this.recyclerView = recyclerView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAllWorkoutsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAllWorkoutsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_all_workouts, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAllWorkoutsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.FABSavedWorkoutsButton;
      ExtendedFloatingActionButton FABSavedWorkoutsButton = ViewBindings.findChildViewById(rootView, id);
      if (FABSavedWorkoutsButton == null) {
        break missingId;
      }

      id = R.id.FABback;
      ExtendedFloatingActionButton FABback = ViewBindings.findChildViewById(rootView, id);
      if (FABback == null) {
        break missingId;
      }

      id = R.id.muscleTextView;
      TextView muscleTextView = ViewBindings.findChildViewById(rootView, id);
      if (muscleTextView == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      return new FragmentAllWorkoutsBinding((ConstraintLayout) rootView, FABSavedWorkoutsButton,
          FABback, muscleTextView, recyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
