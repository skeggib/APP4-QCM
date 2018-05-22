package com.app4qcm.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XML_Tools {

	/**
	 * Encode en fichier XML
	 * @param object
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
    public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
    	XStream xstream = new XStream(new DomDriver());
        try {
        	//Fichier modifie
            FileOutputStream fout = new FileOutputStream(fileName);
            //conversion XML
            xstream.toXML(object, fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Decode un fichier XML et cree une instance de l'objet retourne
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        
        XStream xstream = new XStream( new DomDriver());
        try {
        		//fichier lu
                FileInputStream fin= new FileInputStream(fileName);
                object =  xstream.fromXML(fin);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }       
        return object;
    }
    
    /**
     * 
     * @param object
     * @return serialisation de l'instance dans un String
     */
    public static String encodeToString(Object object) {
    	XStream xstream = new XStream(new DomDriver());
		//conversion XML
		return xstream.toXML(object);
    }
    
    /**
     * 
     * @param encodage
     * @return instance de l'objet deserialisee a partir du string
     */
    public static Object decodeFromString(String encodage)  {
        Object object = null;       
        XStream xstream = new XStream( new DomDriver());
        //Deserialisation du string XML
        object =  xstream.fromXML(encodage);
        return object;
    }
}
