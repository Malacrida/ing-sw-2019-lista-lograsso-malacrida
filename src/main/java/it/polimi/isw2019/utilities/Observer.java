package it.polimi.isw2019.utilities;

import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;

public interface Observer<T>{
    public void update(T message);
}




