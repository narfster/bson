package com.narfster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.bson.*;
import org.bson.codecs.RawBsonDocumentCodec;

//Java code for serialization and deserialization 
//of a Java object 
import java.io.*;

//
//{
//	  "utc": 1572865519,
//	  "vid": 1234,
//	  "ser": 9999999,
//	  "type": 1,
//	  "c1":12000,
//	  "c2":12000,
//	  "c3":12000,
//	  "c4":12000,
//	  "c5":12000,
//	  "c6":12000,
//	  "c7":12000,
//	  "c8":12000,
//	  "c9":12000,
//	  "c10":12000,
//	  "c11":12000,
//	  "c12":12000,
//	  "c13":12000,
//	  "c14":12000,
//	  "c15":12000,
//	  "c16":12000,
//	  "c17":12000,
//	  "c18":12000,
//	  "c19":12000,
//	  "c20":12000,
//	  "c21":12000,
//	  "c22":12000,
//	  "c23":12000,
//	  "c24":12000
//	}
class BmsData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int utc;
	public int vid;
	public int ser;
	public int type;
	public int c1;
	public int c2;
	public int c3;
	public int c4;
	public int c5;
	public int c6;
	public int c7;
	public int c8;
	public int c9;
	public int c10;
	public int c11;
	public int c12;
	public int c13;
	public int c14;
	public int c15;
	public int c16;
	public int c17;
	public int c18;
	public int c19;
	public int c20;
	public int c21;
	public int c22;
	public int c23;
	public int c24;

	// Default constructor
	public BmsData() {
		this.utc = 123423434;
		this.vid = 999999;
		this.ser = 12343311;
		this.type = 1;
		this.c1 = 12000;
		this.c2 = 12000;
		this.c3 = 12000;
		this.c4 = 12000;
		this.c5 = 12000;
		this.c6 = 12000;
		this.c7 = 12000;
		this.c8 = 12000;
		this.c9 = 12000;
		this.c10 = 12000;
		this.c11 = 12000;
		this.c12 = 12000;
		this.c13 = 12000;
		this.c14 = 12000;
		this.c15 = 12000;
		this.c16 = 12000;
		this.c17 = 12000;
		this.c18 = 12000;
		this.c19 = 12000;
		this.c20 = 12000;
		this.c21 = 12000;
		this.c22 = 12000;
		this.c23 = 12000;
		this.c24 = 12000;
	}

}

class bson {

	public static byte[] Serialization(String json) {

		// String json = new String("{
		// \"float\":\"ABCDEF\",\"vid\":1234,\"123456\":123456}");
		// Convert json string to raw Bson document
		RawBsonDocument rawBsonDoc = RawBsonDocument.parse(json);

		// get wrapper buffer
		ByteBuf b = rawBsonDoc.getByteBuffer();

		// get buffer from within wrapper
		return b.array();

	}

	public static String Deserialization(byte[] arr){

		RawBsonDocument rawdo = new RawBsonDocument(arr);
		return rawdo.toJson();
	}

	public static void Serialization() {
		String filename = "file.ser";

		if (true) {
			// Serialization
			try {

				// Create FILE object to write
				FileOutputStream file = new FileOutputStream(filename);

				// Create data object to serialize
				BmsData bms = new BmsData();

				// convert to json string
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(bms);

				// Convert json string to raw Bson document
				RawBsonDocument raw = RawBsonDocument.parse(json);

				// get wrapper buffer
				ByteBuf b = raw.getByteBuffer();

				// get buffer from within wrapper
				b.array();

				// write to file
				file.write(b.array());

				// close file
				file.close();

				System.out.println("Object has been serialized");

			}

			catch (IOException ex) {
				System.out.println("IOException is caught");
			}
		}
	}

	public static void Deserialization() {
		String filename = "file.ser";
		try {
			if (true) {
				// Reading the object from a file
				FileInputStream file = new FileInputStream(filename);
				byte[] x = new byte[1000];
				file.read(x);
				file.close();

				RawBsonDocument rawdo = new RawBsonDocument(x);

				System.out.println("Object has been deserialized ");
				System.out.println("toString " + rawdo.toString());
			}
		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

	}

	public static void main(String[] args) {

		bson.Serialization();

		bson.Deserialization();

		String beforeJson = "{\"float\":\"ABCDEF\",\"vid\":1234,\"123456\":123456}";
		byte[] arr = bson.Serialization(beforeJson);
		
		String afterJson = bson.Deserialization(arr);
		
		//remove white spaces
		afterJson = afterJson.replaceAll("\\s+", "");
		
		System.out.println("beforeJson " + beforeJson);
		System.out.println("afterJson " + afterJson);
		
		if(beforeJson.compareTo(afterJson) == 0) {
			System.out.println("json are equal");
		}

	}
}
