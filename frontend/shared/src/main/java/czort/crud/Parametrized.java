package czort.crud;

import com.googlecode.gentyref.TypeToken;

public abstract class Parametrized<MODEL, CREATE, UPDATE> {
    TypeToken<MODEL> modelType = new TypeToken<MODEL>() {};
    TypeToken<CREATE> createType = new TypeToken<CREATE>() {};
    TypeToken<UPDATE> updateType = new TypeToken<UPDATE>() {};

    public Class<MODEL> getModelType()  {
       return null;
    }
}
