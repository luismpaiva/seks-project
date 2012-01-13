package seks.basic.serialization;

import java.util.HashMap;

/**
 *
 * @author Paulo Figueiras
 */
public interface SerializationTools {
    public String Serialize(HashMap map) ;
    
    public HashMap Deserialize(String serialized) ;
}
