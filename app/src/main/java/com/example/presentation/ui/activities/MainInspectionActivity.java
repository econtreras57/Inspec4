package com.example.presentation.ui.activities;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domain.model.Inspection;
import com.example.inspec3.R;
import com.example.presentation.view.InspectionView;

import java.util.ArrayList;
import java.util.List;

import static com.example.inspec3.R.string.edita_inspecci_n;
import static com.example.inspec3.R.string.nueva_inspecci_n;
import static com.example.presentation.utils.Constants.EXTRA_MESSAGE;
import static com.example.presentation.utils.Constants.SWITCH_BUTTON_OFF;
import static com.example.presentation.utils.Constants.SWITCH_BUTTON_ON;

/**
 * Datos registro Inspección
 * 2020-02 ECV
 */

public class MainInspectionActivity
        extends BaseActivity
        implements InspectionView,
        AdapterView.OnItemSelectedListener {

    Inspection inspection;
    TextView tv_titDocActivity;

    Spinner spinnerProject;
    Spinner spinnerContractor;
    Spinner spinnerLocType;
    Spinner spinnerSite;

    Button button1;
    Button button2;

    EditText responsible;

    TextView tv_status;
    TextView tv_statusDate;
    TextView tv_subStatus;

    TextView tv_inspector;
    ImageView ic_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inspection);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // subtítulo del toolbar luego, al final de esta rutina

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get the Intent that started this activity and extract incoming data

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoInspection");
        this.inspection = (Inspection) bundle.getSerializable("objetoInspection");

        String message = intent.getStringExtra(EXTRA_MESSAGE);
        int position = Integer.parseInt(message);   // posición del arreglo (o -1 si nuevo)

        // Set and load fields on screen (and states of button-groups)
        spinnerProjectLoad();
        spinnerContractorLoad();
        spinnerLocTypeLoad();
        spinnerSitesLoad();


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        if (inspection.getPlan() == null) inspection.setPlan("");
        if (inspection.getPlan().equalsIgnoreCase("Planned"))
            switchButton(button1, SWITCH_BUTTON_ON);
        if (inspection.getPlan().equalsIgnoreCase("Not Planned"))
            switchButton(button2, SWITCH_BUTTON_ON);


        responsible = findViewById(R.id.et_reponsible);
        if (inspection.getResponsible() == null) inspection.setResponsible("");
        responsible.setText(inspection.getResponsible());

        tv_status = findViewById(R.id.tv_status);
        if (inspection.getStatus() == null) inspection.setStatus("");
        tv_status.setText(inspection.getStatus());
        if (!inspection.getStatus().equalsIgnoreCase("Abierto"))
            tv_status.setBackground(this.getDrawable(R.drawable.textview_border_gray));

        tv_statusDate = findViewById(R.id.tv_statusDate);
        if (inspection.getStatusDate() == null) inspection.setStatusDate("");
        tv_statusDate.setText(inspection.getStatusDate());

        tv_subStatus = findViewById(R.id.tv_subStatus);
        if (inspection.getSubStatus() == null) inspection.setSubStatus("");
        tv_subStatus.setText(inspection.getSubStatus());
        if (inspection.getStatus().equalsIgnoreCase("Abierto"))
            tv_subStatus.setTypeface(null, tv_subStatus.getTypeface().BOLD);

        tv_inspector = findViewById(R.id.tv_inspector);
        tv_inspector.setText("Usuario conectado ahora");
        ic_user = findViewById(R.id.ic_user);

        tv_titDocActivity = findViewById(R.id.tv_titDocActivity);

        String title_add = getString(nueva_inspecci_n);
        String title_upd = getString(edita_inspecci_n, message);
        if (position == -1) {
            this.getSupportActionBar().setSubtitle(title_add);
            tv_titDocActivity.setText(title_add);
            View header = findViewById(R.id.constraintLayoutHeader);
            header.setVisibility(View.GONE);

            tv_status.setVisibility(View.INVISIBLE);
            tv_statusDate.setVisibility(View.INVISIBLE);
            tv_subStatus.setVisibility(View.INVISIBLE);
            tv_inspector.setVisibility(View.INVISIBLE);
            ic_user.setVisibility(View.INVISIBLE);

        } else {
            this.getSupportActionBar().setSubtitle("");
            tv_titDocActivity.setText(title_upd);
//            findViewById(R.id.constraintLayoutHeader).setVisibility(View.VISIBLE);
            View header = findViewById(R.id.constraintLayoutHeader);
            header.setVisibility(View.VISIBLE);

            tv_status.setVisibility(View.VISIBLE);
            tv_statusDate.setVisibility(View.VISIBLE);
            tv_subStatus.setVisibility(View.VISIBLE);
            tv_inspector.setVisibility(View.VISIBLE);
            ic_user.setVisibility(View.VISIBLE);
        }

    }

    public void spinnerProjectLoad() {
//        Spinner spinnerProject = (Spinner) findViewById(R.id.spinner_project);
        spinnerProject = (Spinner) findViewById(R.id.spinner_project);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterProject = ArrayAdapter.createFromResource(this,
                R.array.projects_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerProject.setAdapter(adapterProject);

        spinnerProject.setSelection(adapterProject.getPosition(inspection.getProject()));
        spinnerProject.setOnItemSelectedListener(this);
    }

    public void spinnerContractorLoad() {
//        Spinner spinnerContractor = (Spinner) findViewById(R.id.spinner_contractor);
        spinnerContractor = (Spinner) findViewById(R.id.spinner_contractor);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterContractor = ArrayAdapter.createFromResource(this,
                R.array.contractors_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterContractor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerContractor.setAdapter(adapterContractor);

        spinnerContractor.setSelection(adapterContractor.getPosition(inspection.getContractor()));
        spinnerContractor.setOnItemSelectedListener(this);
    }

    public void spinnerLocTypeLoad() {
//        Spinner spinnerLocType = (Spinner) findViewById(R.id.spinner_locType);
        spinnerLocType = (Spinner) findViewById(R.id.spinner_locType);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterLocType = ArrayAdapter.createFromResource(this,
                R.array.locType_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterLocType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerLocType.setAdapter(adapterLocType);

        spinnerLocType.setSelection(adapterLocType.getPosition(inspection.getLocType()));
        spinnerLocType.setOnItemSelectedListener(this);
    }

    public void spinnerSitesLoad() {
//        Spinner spinnerSite = (Spinner) findViewById(R.id.spinner_site);
        spinnerSite = (Spinner) findViewById(R.id.spinner_site);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSite = ArrayAdapter.createFromResource(this,
                R.array.sites_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterSite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerSite.setAdapter(adapterSite);

        spinnerSite.setSelection(adapterSite.getPosition(inspection.getSite()));
        spinnerSite.setOnItemSelectedListener(this);
    }

    @Override
    public void inspectionCreated(Inspection inspection) {

    }

    @Override
    public void inspectionCreatedList(List<Inspection> inspectionList) {

    }

    @Override
    public void inspectionUpdated(Inspection inspection) {

    }

    @Override
    public void inspectionDeleted(Inspection inspection) {

    }

    @Override
    public void inspectionsListLoaded(ArrayList<Inspection> inspections) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    public void OnButtonG1Checked(View view) {

        switchButton(button1, SWITCH_BUTTON_OFF);
        switchButton(button2, SWITCH_BUTTON_OFF);

        switch (view.getId()) {
            case R.id.button1:
                switchButton(button1, SWITCH_BUTTON_ON);
                break;
            case R.id.button2:
                switchButton(button2, SWITCH_BUTTON_ON);
                break;

        }
    }

    public void switchButton(Button button, Boolean on_off) {
        if (on_off == SWITCH_BUTTON_ON) {

            // Switch the button on
//            button.setBackground(this.getDrawable(R.drawable.textview_border_black));
//            button.setTypeface(null, button.getTypeface().BOLD);
            button.setTypeface(null, Typeface.BOLD);
            button.setTextColor(Color.BLACK);

        } else {

            // Switch the button off
//            button.setBackground(this.getDrawable(R.drawable.textview_border_gray));
//            button.setTypeface(null, button.getTypeface().NORMAL);
            button.setTypeface(null, Typeface.NORMAL);
            button.setTextColor(Color.GRAY);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        String sTest = (String) parent.getTag();
        String itemAtPosition = (String) parent.getItemAtPosition(position);

        Context context = getApplicationContext();
        CharSequence text = "Click SPINNER button: \n" + sTest + "\n" + itemAtPosition;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        // Another interface callback

    }

    public void onButtonSaveClicked(View v) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

} // fin clase
