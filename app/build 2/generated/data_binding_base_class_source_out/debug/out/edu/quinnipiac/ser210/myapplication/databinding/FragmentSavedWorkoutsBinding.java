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

public final class FragmentSavedWorkoutsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ExtendedFloatingActionButton clearDatabaseButton;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final ExtendedFloatingActionButton savedBackToHome;

  @NonNull
  public final TextView savedWorkouts;

  private FragmentSavedWorkoutsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ExtendedFloatingActionButton clearDatabaseButton, @NonNull RecyclerView recyclerView,
      @NonNull ExtendedFloatingActionButton savedBackToHome, @NonNull TextView savedWorkouts) {
    this.rootView = rootView;
    this.clearDatabaseButton = clearDatabaseButton;
    this.recyclerView = recyclerView;
    this.savedBackToHome = savedBackToHome;
    this.savedWorkouts = savedWorkouts;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSavedWorkoutsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSavedWorkoutsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_saved_workouts, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSavedWorkoutsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.clearDatabaseButton;
      ExtendedFloatingActionButton clearDatabaseButton = ViewBindings.findChildViewById(rootView, id);
      if (clearDatabaseButton == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.savedBackToHome;
      ExtendedFloatingActionButton savedBackToHome = ViewBindings.findChildViewById(rootView, id);
      if (savedBackToHome == null) {
        break missingId;
      }

      id = R.id.savedWorkouts;
      TextView savedWorkouts = ViewBindings.findChildViewById(rootView, id);
      if (savedWorkouts == null) {
        break missingId;
      }

      return new FragmentSavedWorkoutsBinding((ConstraintLayout) rootView, clearDatabaseButton,
          recyclerView, savedBackToHome, savedWorkouts);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}