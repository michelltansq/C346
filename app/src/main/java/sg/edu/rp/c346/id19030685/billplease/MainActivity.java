package sg.edu.rp.c346.id19030685.billplease;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button btnSplit, btnReset;
    ToggleButton tbtnSvs, tbtnGst;
    EditText etAmt, etPax, etDiscount;
    TextView tvTotal, tvEach;

    private double calTotal(){

        double amt = Double.parseDouble(etAmt.getText().toString().trim());
        if(tbtnSvs.isChecked() && tbtnGst.isChecked()){
            amt *= 1.17;
        }
        else if(tbtnSvs.isChecked() && !tbtnGst.isChecked()){
            amt *= 1.1;
        }
        else if(!tbtnSvs.isChecked() && tbtnGst.isChecked()){
            amt *= 1.07;
        }
        if(etDiscount.getText().toString().trim().length() > 0){
            double discount = Double.parseDouble(etDiscount.getText().toString().trim());
            amt = amt * (1-discount/100.0);
        }
        return amt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tbtnSvs = findViewById(R.id.toggleButtonSVS);
        tbtnGst = findViewById(R.id.toggleButtonGST);
        etAmt = findViewById(R.id.edInputAmount);
        etPax = findViewById(R.id.edInputPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        tvTotal = findViewById(R.id.textViewTotal);
        tvEach = findViewById(R.id.textViewEachPays);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmt.getText().toString().trim().length() == 0){
                    return;
                }
                if(etPax.getText().toString().trim().length() == 0){
                    return;
                }
                double total = calTotal();
                int pax = Integer.parseInt(etPax.getText().toString().trim());
                tvTotal.setText("Total: " + String.format("%.2f", total));
                tvEach.setText("Each pays: $" + String.format("%.2f", total/pax));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
                etPax.setText("");
                etDiscount.setText("");
            }
        });
    }
}
