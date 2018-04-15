package com.pcun.b1.a1b_pcun_ma;

public class FilterResidue {
    //
    public enum Filter {Pilas, Medicamentos, Llantas};

    // Current
    private Filter mFilter = Filter.Pilas;

    public Filter getFilter() {
        return mFilter;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
    }
}
