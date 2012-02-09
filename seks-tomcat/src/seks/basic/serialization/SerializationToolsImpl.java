/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 *
 * @author Paulo Figueiras
 */
public class SerializationToolsImpl implements SerializationTools {
	
	public SerializationToolsImpl() {}

    @Override
    public String Serialize(HashMap<?,?> map) {
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        XMLEncoder encoder = new XMLEncoder(out) ;
        encoder.writeObject(map) ;
        encoder.flush() ;
        return  out.toString() ;
    }

    @Override
    public HashMap<?,?> Deserialize(String serialized) {
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(serialized.getBytes())) ;
        HashMap<?,?> map = (HashMap<?,?>) decoder.readObject() ;
        return map ;
    }
    
}
