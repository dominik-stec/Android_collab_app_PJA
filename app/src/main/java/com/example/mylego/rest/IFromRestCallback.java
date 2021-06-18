package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.rest.domain.PartsSingleSet;

public interface IFromRestCallback {

    void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value);
    void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value);
    void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value);
    void onGetOnePageResultSinglePartsFromRestSuccess(PartsSingleSet[] value);

}
