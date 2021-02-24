package tj.iskandar.roomdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DetailsActivity extends BottomSheetDialogFragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_details, container, false);
        String name = getArguments().getString("name");//String text
        String url = getArguments().getString("url");//String text
        String startDate = getArguments().getString("startDate");//String text
        String endDate = getArguments().getString("endDate");//String text
        String objType = getArguments().getString("objType");//String text
        String loginRequired = getArguments().getString("loginRequired");//String text

        TextView named = view.findViewById(R.id.name);
        TextView urld = view.findViewById(R.id.url);
        TextView startDated = view.findViewById(R.id.startDate);
        TextView endDated = view.findViewById(R.id.endDate);
        TextView objTyped = view.findViewById(R.id.objType);
        TextView loginRequiredd = view.findViewById(R.id.loginRequired);

        named.setText(name);
        urld.setText(url);
        startDated.setText(startDate);
        endDated.setText(endDate);
        objTyped.setText(objType);
        loginRequiredd.setText(loginRequired);

        return view;
    }
}