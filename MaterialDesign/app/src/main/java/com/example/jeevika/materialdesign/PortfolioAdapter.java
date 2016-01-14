package com.example.jeevika.materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.MyViewHolder>{

    private  LayoutInflater inflator;
    List<PortfolioData> data;
    Context context;
    public PortfolioAdapter(Context context,List<PortfolioData> data){
      inflator=  LayoutInflater.from(context);
        this.data=data;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflator.inflate(R.layout.customrow,parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    PortfolioData d=data.get(position);
        holder.tv1.setText(d.stock+"");
        String nos=d.nos+" ";

        if(d.stock.equalsIgnoreCase("total"))
        {
            nos="";
            holder.tv3.setText("Invested Amount : "+d.avgprice);
            holder.tv4.setText("Current Value : "+d.currentprice);

        }
        else{
            nos="No. Of Stocks : "+nos;

            holder.tv3.setText("Hold Price : "+d.avgprice);
            holder.tv4.setText("Current Price : "+d.currentprice);

        }
        holder.tv2.setText(nos);

        holder.tv5.setText(d.profit+"");
        if(d.profit>0) {
            holder.tv5.setTextColor(context.getResources().getColor(R.color.profit));
            holder.tv6.setTextColor(context.getResources().getColor(R.color.profit));
        }
        else
        {
            holder.tv5.setTextColor(context.getResources().getColor(R.color.loss));
            holder.tv6.setTextColor(context.getResources().getColor(R.color.loss));

        }
        holder.tv6.setText(d.profitp+"%");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2,tv3,tv4,tv5,tv6;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.portfoliotv1);
            tv2= (TextView) itemView.findViewById(R.id.portfoliotv2);
            tv3= (TextView) itemView.findViewById(R.id.portfoliotv3);
            tv4= (TextView) itemView.findViewById(R.id.portfoliotv4);
            tv5= (TextView) itemView.findViewById(R.id.portfoliotv5);
            tv6= (TextView) itemView.findViewById(R.id.portfoliotv6);
        }
    }
}
