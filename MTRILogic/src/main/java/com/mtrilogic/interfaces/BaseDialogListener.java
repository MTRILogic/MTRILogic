package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Model;

@SuppressWarnings("unused")
public interface BaseDialogListener<M extends Model> extends OnTaskCompleteListener<M>, OnMakeToastListener{
}
