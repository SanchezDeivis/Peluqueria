package com.example.peluqueria.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.peluqueria.fragments.Fragment1;
import com.example.peluqueria.fragments.Paginaadmin1;
import com.example.peluqueria.fragments.Paginaadmin2;

public class AdaptadorPaginas extends FragmentPagerAdapter {
    int numeroPaginas;

    public AdaptadorPaginas(@NonNull FragmentManager fm, int numPaginas) {
        super(fm, numPaginas);
        this.numeroPaginas = numPaginas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Paginaadmin1();
            case 1:
                return new Paginaadmin2();
            case 2:
                return new Fragment1();
            default:
                return null;
        }


    }


    @Override
    public int getCount() {
        return numeroPaginas;
    }


}